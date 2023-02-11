package hw4;

import api.Card;

/**
 * Evaluator for a hand in which the rank of each card is a prime number.
 * The number of cards required is equal to the hand size. 
 * 
 * The name of this evaluator is "All Primes".
 * @author justin
 */
public class AllPrimesEvaluator extends AbstractEvaluator
{
  /**
   * Constructs the evaluator.
   * @param ranking
   *   ranking of this hand
   * @param handSize
   *   number of cards in a hand
   */
	
  public AllPrimesEvaluator(int ranking, int handSize) {
	  super(ranking, handSize, "All Primes", handSize);
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
			if(mainCards[i].getRank() != 2 && mainCards[i].getRank() != 3 && mainCards[i].getRank() != 5 && 
			   mainCards[i].getRank() != 7 && mainCards[i].getRank() != 11 && mainCards[i].getRank() != 13) {
				return false;
			}
 		}
		return true;
	}
	return false;
}
}
