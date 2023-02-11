package hw4;

import java.util.ArrayList;


import api.Card;
import util.SubsetFinder;

/**
 * Evaluator for a hand containing (at least) two cards of the same rank.
 * The number of cards required is two.
 * 
 * The name of this evaluator is "One Pair".
 * 
 * @author justin
 */

//Note: You must edit this declaration to extend AbstractEvaluator
//or to extend some other class that extends AbstractEvaluator

public class OnePairEvaluator extends AbstractEvaluator {
  /**
   * Constructs the evaluator.
   * @param ranking
   *   ranking of this hand
   * @param handSize
   *   number of cards in a hand
   */
	
	public OnePairEvaluator(int ranking, int handSize) {
		super(ranking, handSize, "One Pair", 2);
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
			if(mainCards[0].getRank() == mainCards[1].getRank()) {
				return true;
			}
		}
		return false;
	}
	/**
	   * Inputs all of the cards and then finds all of the possible subsets from those cards and returns 
	   * if the cards satisfy the evaluator
	   * @param allCards all of the cards from the array
	   * @return if the subset of all the cards can satisfy the evaluator
	   */
	@Override
	public boolean canSubsetSatisfy(Card[] allCards) { // OnePairEvaluator wants to be special by 
													   // hating the super class, canSubsetSatisfy(). 
													   // Had to create one specifically for it.
		
		ArrayList<Integer> cardRanks = new ArrayList<Integer>();
		
		for(int i = 0; i < allCards.length; i++) {
			cardRanks.add(allCards[i].getRank());
		}
		
		ArrayList<int[]> subset = SubsetFinder.findSubsets(cardRanks, cardsRequired());
		
		for(int i = 0; i < subset.size(); i++) {
			int[] cards = subset.get(i);
			if(cards.length == cardsRequired()) {
				if(cards[0] == cards[1]) {
					return true;
				}
			}
		}
		return false;
	}
}
