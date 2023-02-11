package hw4;
import java.util.Arrays;
import api.Card;

/**
 * Evaluator for a hand consisting of a "straight" in which the
 * card ranks are consecutive numbers.  The number of required 
 * cards is equal to the hand size.  An ace (card of rank 1) 
 * may be treated as the highest possible card or as the lowest
 * (not both).  To evaluate a straight containing an ace it is
 * necessary to know what the highest card rank will be in a
 * given game; therefore, this value must be specified when the
 * evaluator is constructed.  In a hand created by this
 * evaluator the cards are listed in descending order with high 
 * card first, e.g. [10 9 8 7 6] or [A K Q J 10], with
 * one exception: In case of an ace-low straight, the ace
 * must appear last, as in [5 4 3 2 A]
 * 
 * The name of this evaluator is "Straight".
 * 
 * @author justin
 */
//Note: You must edit this declaration to extend AbstractEvaluator
//or to extend some other class that extends AbstractEvaluator
public class StraightEvaluator extends AbstractEvaluator
{  
  /**
   * Constructs the evaluator. Note that the maximum rank of
   * the cards to be used must be specified in order to 
   * correctly evaluate a straight with ace high.
   * @param ranking
   *   ranking of this hand
   * @param handSize
   *   number of cards in a hand
   * @param maxCardRank
   *   largest rank of any card to be used
   */
	
	private int maxCardRank;
	
  public StraightEvaluator(int ranking, int handSize, int maxCardRank)
  {
	  super(ranking, handSize, "Straight", handSize);
	  this.maxCardRank = maxCardRank;
	  
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
		
			int[] mainCardsRank = new int[mainCards.length];
			
			for(int i = 0; i < mainCardsRank.length; i++) {
				mainCardsRank[i] = mainCards[i].getRank();
			}
			
			Arrays.sort(mainCardsRank);
			
			if(mainCardsRank[0] == 1 && mainCardsRank[mainCardsRank.length - 1] == 13) {
				
				mainCardsRank[0] = 14;
				Arrays.sort(mainCardsRank);
				
				for(int i = 0 ; i < mainCardsRank.length - 1; i++) {
					if (mainCardsRank[i] != mainCardsRank[i + 1] - 1) {
						return false;
					}
				}
			}
			
			for(int i = 0 ; i < mainCardsRank.length - 1; i++) {
				if (mainCardsRank[i] != mainCardsRank[i + 1] - 1) {
					return false;
				}
			}
		return true;
		}
		return false;
	}
}
