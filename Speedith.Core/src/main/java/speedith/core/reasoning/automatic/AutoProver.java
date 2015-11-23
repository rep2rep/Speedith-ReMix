package speedith.core.reasoning.automatic;

import speedith.core.lang.*;
import speedith.core.reasoning.*;
import speedith.core.reasoning.args.SubDiagramIndexArg;
import speedith.core.reasoning.automatic.wrappers.CompoundSpiderDiagramWrapper;
import speedith.core.reasoning.automatic.wrappers.PrimarySpiderDiagramWrapper;
import speedith.core.reasoning.automatic.wrappers.SpiderDiagramWrapper;
import speedith.core.reasoning.rules.*;
import speedith.core.reasoning.rules.util.AutomaticUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

/**
 * Generates proofs for given subgoals.
 *
 * @author Sven Linker [s.linker@brighton.ac.uk]
 * Created by sl542 on 11/11/15.
 *
 */
public class AutoProver {

    /**
     * Creates a proof without possibilities to set the wanted
     * heuristics.
     * @param initialGoals the Euler Diagram to prove. Has to be an implication,
     *                     where both the premise and the conclusion are purely conjunctive
     *                     diagrams.
     *
     * @return a proof of the goals
     */
    public static Proof generateProof(Goals initialGoals) throws AutomaticProofException {
       // currently only Spider Diagrams which have an imolication as their major operator
        // and where the assumption and conclusion are conjunctive diagrams
        // can be proved
        if (!isImplicationOfConjunctions(initialGoals.getGoalAt(0))) {
            throw new AutomaticProofException("The current goal is not an implication of conjunctions.");
        }

        // workaround as long as Speedith doesn't support several subgoals at once
        int subGoalToProve = 0;

        // introduce all zones that are only implicit in the
        // data structure. I.e. present_zones returns the zone set in
        // the usual sense of spider diagrams
        Proof init = new ProofTrace(AutomaticUtils.normalize(initialGoals));
        AppliedRules appliedRules = new AppliedRules();

        // wrap the diagram to identify the occurrences of the subdiagrams
        SpiderDiagramWrapper wrapped = wrapDiagram(initialGoals.getGoalAt(subGoalToProve),0);

        Proof result = null;
        try {
            result = prove(init, subGoalToProve, appliedRules);
        } catch (RuleApplicationException e) {
            AutomaticProofException exc = new AutomaticProofException("Unable to prove current goal because of an illegal rule application");
            exc.initCause(e);
            throw exc;
        }
        if (result == null || !result.isFinished()) {
            throw  new AutomaticProofException("Unable to prove current goal");
        }
        return result;
    }

    /*
     * Prints all subdiagrams of the given SpiderDiagramWrapper to the consolde including
     * their occurrences in the SpiderDiagramWrapper.
     */
    private static void printSubDiagramIndices(SpiderDiagramWrapper current) {
        if (current instanceof PrimarySpiderDiagramWrapper) {
            System.out.println( current.getOccurrenceIndex() + ":  "+current.getDiagram());
        } else {
            CompoundSpiderDiagramWrapper cs = (CompoundSpiderDiagramWrapper) current;
            System.out.println( current.getOccurrenceIndex() + ":  "+current.getDiagram());
            for (SpiderDiagramWrapper sub: cs.getOperands()) {
                printSubDiagramIndices(sub);
            }

        }

    }

    /*
     * Creates a SpiderDiagramWrapper for the given SpiderDiagram sd to reliably
     * refer to the different occurrences of diagrams in sd
     */
    private static SpiderDiagramWrapper wrapDiagram (SpiderDiagram sd, final int occurrenceIndex) {
        if (sd instanceof PrimarySpiderDiagram) {
            return new PrimarySpiderDiagramWrapper(sd, occurrenceIndex);
        }
        if (sd instanceof CompoundSpiderDiagram) {
            int newIndex = occurrenceIndex+1;
            ArrayList<SpiderDiagramWrapper> operands = new ArrayList<SpiderDiagramWrapper>();
            for(SpiderDiagram op: ((CompoundSpiderDiagram) sd).getOperands()) {
                SpiderDiagramWrapper opWrap = wrapDiagram(op, newIndex);
                operands.add(opWrap);
                newIndex+= opWrap.getDiagram().getSubDiagramCount();
            }
            return new CompoundSpiderDiagramWrapper(sd, occurrenceIndex, operands);

        }
        return null;
    }

    /*
     * Recursively create and apply all rule applications for the given subgoal  in the current
     * state of the given Proof p. The rules already applied to subdiagrams within
     * the current set of goals are saved in appliedRules
     */
    private static Proof prove(Proof p, int subgoalindex, AppliedRules appliedRules) throws RuleApplicationException {
        p = tryToFinish(p, subgoalindex);
        if (p.isFinished()) {
            return p;
        }
        Goals currentGoals = p.getLastGoals();
        // get all names of contours present in the goals. This bounds the
        // possible proof rule applications, since contours not in this set
        // never have to be copied or introduced.
        Collection<String> contours = new HashSet<String>();
        for (SpiderDiagram sd : currentGoals.getGoals()) {
            contours.addAll( AutomaticUtils.collectContours(sd));
        }
        SpiderDiagramWrapper target = wrapDiagram(currentGoals.getGoalAt(subgoalindex), 0);
        Collection<PossibleRuleApplication> applications = AutomaticUtils.createAllPossibleRuleApplications(target, contours, appliedRules);
        for (PossibleRuleApplication ruleApp : applications) {
            boolean hasbeenApplied = applyRule(ruleApp, p, subgoalindex, appliedRules);
            if (hasbeenApplied) {
                p = prove(p, subgoalindex, appliedRules);
                if (p.isFinished()) {
                    return p;
                }
                //FIXME: addition of copy contour creates some endless(?) recursion. Stackoverflow
                p.undoStep();
            }
        }

        return p;
    }

    /*
     * Tries to finish the given subgoal by an application of ImplicationTautology
     */
    private static Proof tryToFinish(Proof p, int subgoalindex) {
        ImplicationTautology tautology = new ImplicationTautology();
        Goals goalsAt = p.getLastGoals();
        SubDiagramIndexArg index = new SubDiagramIndexArg(subgoalindex,0);
        try {
            p.applyRule(tautology,index);
        }catch (TransformationException e) {
            //throw e;
        } catch (RuleApplicationException e) {
            e.printStackTrace();
        }
        return p;
    }

    private static boolean applyRule(PossibleRuleApplication ruleApp, Proof p, int subgoalindex, AppliedRules appliedRules) throws RuleApplicationException {
        if (ruleApp instanceof PossibleIntroduceContourApplication) {
            PossibleIntroduceContourApplication intro = (PossibleIntroduceContourApplication) ruleApp;
            String contour = intro.getContour();
            SpiderDiagramWrapper target = ruleApp.getTarget();
            if (!appliedRules.getIntroducedContours(target).contains(contour)) {
                p.applyRule(intro.getRule(), intro.getArg(subgoalindex));
                appliedRules.addIntroContour(target, contour);
                return true;
            }
        } else if (ruleApp instanceof PossibleConjunctionElimination) {
            PossibleConjunctionElimination pconjE = (PossibleConjunctionElimination) ruleApp;
            SpiderDiagramWrapper target = ruleApp.getTarget();
            p.applyRule(pconjE.getRule(), pconjE.getArg(subgoalindex));
            return true;
        } else if (ruleApp instanceof PossibleRemoveContourApplication) {
            PossibleRemoveContourApplication remove = (PossibleRemoveContourApplication) ruleApp;
            String contour = remove.getContour();
            SpiderDiagramWrapper target = ruleApp.getTarget();
            if (!appliedRules.getRemovedContours(target).contains(contour)) {
                p.applyRule(remove.getRule(), remove.getArg(subgoalindex));
                appliedRules.addRemoveContour(target, contour);
                return true;
            }
        } else if (ruleApp instanceof PossibleRemoveShadingApplication) {
            PossibleRemoveShadingApplication shading = (PossibleRemoveShadingApplication) ruleApp;
            Zone zone= shading.getZone();
            SpiderDiagramWrapper target = ruleApp.getTarget();
            if (!appliedRules.getRemovedShading(target).contains(zone)) {
                p.applyRule(shading.getRule(), shading.getArg(subgoalindex));
                appliedRules.addRemovedShading(target, zone);
                return true;
            }
        }  else if (ruleApp instanceof PossibleCopyContourApplication) {
            PossibleCopyContourApplication copy = (PossibleCopyContourApplication) ruleApp;
            String contour = copy.getContour();
            SpiderDiagramWrapper target = ruleApp.getTarget();
            if (!appliedRules.getCopiedContours(target).contains(contour)) {
                try {

                    p.applyRule(copy.getRule(), copy.getArg(subgoalindex));
                } catch (TransformationException e) {
                    e.printStackTrace();
                }
                appliedRules.addCopiedContour(target, contour);
                return true;
            }
        }
        return false;

    }

    private static boolean isImplicationOfConjunctions(SpiderDiagram goal) {
        if (goal instanceof CompoundSpiderDiagram) {
            CompoundSpiderDiagram csd = (CompoundSpiderDiagram) goal;
            if (csd.getOperator().equals(Operator.Implication)) {
                SpiderDiagram premise = csd.getOperand(0);
                SpiderDiagram conclusion = csd.getOperand(1);
                if (AutomaticUtils.isConjunctive(premise) && AutomaticUtils.isConjunctive(conclusion)) {
                    return true;
                }
            }
        }
        return false;
    }



}
