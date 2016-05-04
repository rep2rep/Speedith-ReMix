package speedith.core.reasoning.tactical

import java.util
import java.util.Locale

import speedith.core.lang.DiagramType
import speedith.core.reasoning.{RuleApplicationException, Goals}
import speedith.core.reasoning.args.{SubgoalIndexArg, RuleArg}
import speedith.core.reasoning.tactical.euler.BasicTactics

import scala.collection.JavaConversions._

/**
  * TODO: Description
  *
  * @author Sven Linker [s.linker@brighton.ac.uk]
  *
  */
class UnifyContours extends SimpleTactic with Serializable {
  override def apply(args: RuleArg, goals: Goals): TacticApplicationResult = {
    args match {
      case arg: SubgoalIndexArg =>
         BasicTactics.unifyContourSets(getPrettyName())(goals)(arg.getSubgoalIndex)(new TacticApplicationResult()) match {
           case Some(result) => result.getApplicationList.isEmpty match {
             case false => result
             case true => throw new TacticApplicationException("Could not apply tactic "+getPrettyName())
           }
           case None => throw new TacticApplicationException("Could not apply tactic "+getPrettyName())
         }
      case _ =>
        throw new RuleApplicationException("Wrong argument type")
    }
  }

  override def getDescription(locale: Locale): String = "Introduces contours until all premises contain all contours"

  override def getPrettyName(locale: Locale): String = "Unify Contours in Premises"

  override def getApplicableTypes: util.Set[DiagramType] = Set(DiagramType.EulerDiagram)

  override def getInferenceName: String = "unify_contours"

}
