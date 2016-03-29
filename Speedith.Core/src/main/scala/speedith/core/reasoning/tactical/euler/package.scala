package speedith.core.reasoning.tactical

import speedith.core.reasoning.{Goals, Proof}
import speedith.core.reasoning.automatic.wrappers.SpiderDiagramOccurrence

/**
 * Type definitions for tactic/tactical support.
 *
 * @author Sven Linker [s.linker@brighton.ac.uk]
 *
 */
package object euler {
  type Tactical = String => Goals => Int => TacticApplicationResult => Option[TacticApplicationResult]
  type Chooser[A] = SpiderDiagramOccurrence => Option[A]
  type Predicate = SpiderDiagramOccurrence => Boolean
}
