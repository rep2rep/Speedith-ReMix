/*
 * File name: Formula.java
 *    Author: Matej Urbas [matej.urbas@gmail.com]
 * 
 *  Copyright © 2012 Matej Urbas
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package diabelli.logic;

import diabelli.components.GoalProvidingReasoner;
import java.util.*;
import org.netbeans.api.annotations.common.NonNull;
import org.openide.util.NbBundle;

/**
 * Represents a general formula. It can be a diagrammatic, sentential, or both
 * at the same time (if the {@link GoalProvidingReasoner reasoner} provides more
 * than one representation of this formula). This class can thus carry many
 * representations, or formats, of the same formula. For example, a formula can
 * be represented with many strings (using syntaxes of many theorem provers),
 * with term trees (abstract syntax trees), or similar. However, there is always
 * {@link Formula#getMainRepresentation() one main representation}. Other
 * representations should be equivalent to it, or at least have the proper
 * logical relation to it (i.e., if this formula is {@link Goal#getPremises() a
 * premise}, then all other representations of it must be logically entailed by
 * the {@link Formula#getMainRepresentation() main representation}; on the other
 * hand, when the formula is {@link Goal#getConclusion() a conclusion}, then the
 * other direction of entailment must hold; the representations can be logically
 * equivalent, i.e., mutually entailed, which is always allowed).
 *
 * <p><span style="font-weight:bold">Note</span>: a formula may have only one
 * representation in a particular {@link FormulaFormatDescriptor format}.</p>
 *
 * @author Matej Urbas [matej.urbas@gmail.com]
 */
@NbBundle.Messages({
    "Formula_null_main_representation=The formula must have a main representation."
})
public class Formula {

    // <editor-fold defaultstate="collapsed" desc="Fields">
    private final FormulaRepresentation<?> mainRepresentation;
    private final HashMap<String, FormulaRepresentation<?>> representations;
    // </editor-fold>
    private final FormulaRole role;

    //<editor-fold defaultstate="collapsed" desc="Constructors">
    /**
     * Creates a formula with the given list of different representations.
     *
     * @param mainRepresentation the main representation of this formula.
     * <p>Other representations must be either entailed by this representation
     * (if this formula acts as a premise) or they must entail the main
     * representation (if this formula acts as a conclusion).</p>
     * @param otherRepresentations this list of representations must contain at
     * least one element. The first element of the list will become the {@link
     * Formula#getMainRepresentation() main representation}.
     * @param role  the role of this formula in a {@link Goal}.
     */
    @NbBundle.Messages({
        "F_role_null=A role must be provided for this formula."
    })
    public Formula(@NonNull FormulaRepresentation<?> mainRepresentation, @NonNull FormulaRole role, Collection<FormulaRepresentation<?>> otherRepresentations) {
        if (mainRepresentation == null) {
            throw new IllegalArgumentException(Bundle.Formula_null_main_representation());
        }
        if (role == null) {
            throw new IllegalArgumentException(Bundle.F_role_null());
        }
        this.mainRepresentation = mainRepresentation;
        this.representations = new HashMap<String, FormulaRepresentation<?>>();
        this.representations.put(mainRepresentation.getFormat().getFormatName(), mainRepresentation);
        if (otherRepresentations != null) {
            for (FormulaRepresentation<?> formulaRepresentation : otherRepresentations) {
                this.representations.put(formulaRepresentation.getFormat().getFormatName(), formulaRepresentation);
            }
        }
        this.role = role;
    }

    /**
     * Creates a formula with the given list of different representations.
     *
     * @param mainRepresentation the main representation of this formula.
     * <p>Other representations must be either entailed by this representation
     * (if this formula acts as a premise) or they must entail the main
     * representation (if this formula acts as a conclusion).</p>
     * @param otherRepresentations this list of representations must contain at
     * least one element. The first element of the list will become the {@link
     * Formula#getMainRepresentation() main representation}.
     * @param role  the role of this formula in a {@link Goal}.
     */
    public Formula(FormulaRepresentation<?> mainRepresentation, @NonNull FormulaRole role, FormulaRepresentation<?>... otherRepresentations) {
        this(mainRepresentation, role, otherRepresentations == null || otherRepresentations.length < 1 ? null : Arrays.asList(otherRepresentations));
    }

    /**
     * Creates a formula with the given list of different representations.
     *
     * @param mainRepresentation the main representation of this formula.
     * <p>Other representations must be either entailed by this representation
     * (if this formula acts as a premise) or they must entail the main
     * representation (if this formula acts as a conclusion).</p>
     * @param otherRepresentations this list of representations must contain at
     * least one element. The first element of the list will become the {@link
     * Formula#getMainRepresentation() main representation}.
     * @param role  the role of this formula in a {@link Goal}.
     */
    public Formula(FormulaRepresentation<?> mainRepresentation, @NonNull FormulaRole role, ArrayList<FormulaRepresentation<?>> otherRepresentations) {
        this(mainRepresentation, role, (Collection<FormulaRepresentation<?>>) otherRepresentations);
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Public Properties">
    /**
     * Returns the main representation of this formula. This is usually the
     * native formula representation of the {@link GoalProvidingReasoner
     * reasoner} that provided this formula.
     *
     * <p>Other representations must be either entailed by this representation
     * (if this formula acts as a premise) or they must entail the main
     * representation (if this formula acts as a conclusion).</p>
     *
     * @return the main representation of this formula.
     */
    public FormulaRepresentation<?> getMainRepresentation() {
        return mainRepresentation;
    }

    /**
     * Returns all representations of this formula. This collection includes the
     * main representation (as the first element).
     *
     * @return all representations of this formula.
     */
    public FormulaRepresentation[] getRepresentations() {
        // Returning a 'wrapped view' of the hash map's values is unsafe. We
        // must return a copy of the representations collection. The backing
        // hash map may still be changed long after this function returns the
        // view.
        // We synchronise here because we may add new representations in another
        // thread.
        synchronized (representations) {
            return representations.values().toArray(new FormulaRepresentation[representations.size()]);
//            return Collections.unmodifiableCollection(representations.values());
        }
    }

    /**
     * Returns the number of representations this formula has. The minimum this
     * function can return is {@code 1}.
     *
     * @return the number of representations this formula has.
     */
    public int getRepresentationsCount() {
        synchronized (representations) {
            return representations.size();
        }
    }

    /**
     * Returns the role of this formula in a {@link Goal}.
     * @return the role of this formula in a {@link Goal}.
     */
    public FormulaRole getRole() {
        return role;
    }

    /**
     * Returns the representation of this formula in the given format. This
     * method does not try to convert the formula into the given format.
     *
     * @param format
     * @return
     */
    public FormulaRepresentation<?> getRepresentation(FormulaFormatDescriptor format) {
        synchronized (representations) {
            return representations.get(format.getFormatName());
        }
    }

    /**
     * Tries to convert this formula into the given format and returns the
     * translated representation if the translation succeeded. 
     * <p><span
     * style="font-weight:bold">Note</span>: if a translation of this format</p>
     *
     * @param format
     * @param tryAllCombinations 
     * @return
     */
    public FormulaRepresentation<?> fetchRepresentation(FormulaFormatDescriptor format, boolean tryAllCombinations) {
        synchronized (representations) {
            return representations.get(format.getFormatName());
        }
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Helper Classes">
    /**
     * Indicates the role of the {@link Formula formula} in a goal.
     */
    public static enum FormulaRole {
        /**
         * Indicates that the {@link Formula formula} is {@link
         * Goal#getPremises() a premise}.
         */
        Premise,
        /**
         * Indicates that the {@link Formula formula} is {@link
         * Goal#getConclusion() the conclusion}.
         */
        Conclusion,
        /**
         * Indicates that the {@link Formula formula} is {@link
         * Goal#asFormula() the goal itself}.
         */
        Goal
    }
    // </editor-fold>
}
