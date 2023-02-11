package hw4;
import api.Card;

/**
 * Evaluator for a hand containing (at least) four cards of the same rank.
 * The number of cards required is four.
 * 
 * The name of this evaluator is "Four of a Kind".
 * @author justin
 */
//Note: You must edit this declaration to extend AbstractEvaluator
//or to extend some other class that extends AbstractEvaluator
public class FourOfAKindEvaluator extends AbstractEvaluator
{
  /**
   * Constructs the evaluator.
   * @param ranking
   *   ranking of this hand
   * @param handSize
   *   number of cards in a hand
   */
	public FourOfAKindEvaluator(int ranking, int handSize) {
	  super(ranking, handSize, "Four of a Kind", 4);   
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
			if(mainCards[0].getRank() == mainCards[1].getRank() && mainCards[1].getRank() == mainCards[2].getRank() && mainCards[2].getRank() == mainCards[3].getRank()) {
				return true;
			}
		}
		return false;
	}
}
