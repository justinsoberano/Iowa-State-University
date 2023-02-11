package hw4;
import api.Card;

/**
 * Evaluator satisfied by any set of cards.  The number of
 * required cards is equal to the hand size.
 * 
 * The name of this evaluator is "Catch All".
 * 
 * @author justin
 */
//Note: You must edit this declaration to extend AbstractEvaluator
//or to extend some other class that extends AbstractEvaluator
public class CatchAllEvaluator extends AbstractEvaluator
{
  /**
   * Constructs the evaluator.
   * @param ranking
   *   ranking of this hand
   * @param handSize
   *   number of cards in a hand
   */
  public CatchAllEvaluator(int ranking, int handSize)
  {
    super(ranking, handSize, "Catch All", handSize);
  }
  /**
   * Checks if it satisfies the number of cards and if it does, overrides the evaluator and checks
   * if the mainCards satisfy the evaluator
   * @param mainCards cards equal the cardsRequired length
   * @return if the cards are equal length to the required amount
   */
@Override
public boolean canSatisfy(Card[] mainCards) {
	
	if(mainCards.length == cardsRequired()) {
		for(int i = 0; i < mainCards.length; i++) {
			for(int j = i + 1; j < mainCards.length - 1; j++) {
				if(mainCards[i].getRank() == mainCards[j].getRank()) {
					return false;
				}
			}
 		}
		return true;
	}
	return false;
}
}
