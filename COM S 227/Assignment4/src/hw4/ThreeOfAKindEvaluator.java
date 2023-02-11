package hw4;

import api.Card;

/**
 * Evaluator for a hand containing (at least) three cards of the same rank.
 * The number of cards required is three.
 * 
 * The name of this evaluator is "Three of a Kind".
 * 
 * @author justin
 */
//Note: You must edit this declaration to extend AbstractEvaluator
//or to extend some other class that extends AbstractEvaluator
public class ThreeOfAKindEvaluator extends AbstractEvaluator
{
  /**
   * Constructs the evaluator.
   * @param ranking
   *   ranking of this hand
   * @param handSize
   *   number of cards in a hand
   */
	public ThreeOfAKindEvaluator(int ranking, int handSize) {
		super(ranking, handSize, "Three of a Kind", 3);
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
			if(mainCards[0].getRank() == mainCards[1].getRank() && mainCards[1].getRank() == mainCards[2].getRank()) {
				return true;
			}
		}	
		return false;
	}
}
