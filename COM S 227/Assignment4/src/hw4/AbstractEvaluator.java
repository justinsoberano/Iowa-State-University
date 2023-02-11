package hw4;
import java.util.ArrayList;
import java.util.Arrays;

import api.Card;
import api.Hand;
import api.IEvaluator;
import util.SubsetFinder;

/**
 * The class AbstractEvaluator includes common code for all evaluator types.
 * 
 * This whole assignment is designed to make use of the eight methods below with 
 * only the subset Satisfy method needing to be overridden for every evaluator.
 * The AbstractEvaluator implements the iEvaluator while all of the other classes extend 
 * AbstractEvaluator
 * 
 * @author justin
 * 
 */
public abstract class AbstractEvaluator implements IEvaluator {
	 
	/**
	 * Holds the ranking of the evaluator
	 */
	  private int ranking;
	  
	  /**
	   * holds the hand size of the evaluator
	   */
	  private int handSize;
	  
	  /**
	   * holds the name of the evalautor
	   */
	  private String name;
	  
	  /**
	   * holds the minimum cards of the evaluator
	   */
	  private int minimumCards;
	  
	  /**
	   * Constructs a constructor which is used by all of the evaluators. It is protected and keeps track of 
	   * ranking, handSize, name, and minimumCards. AbstractEvaluator checks is subsetSatisfies the evaluator,
	   * checks if is can satisfy the evaluator, creates a hand, and gets the best hand
	   * @param ranking the ranking of the evaluator
	   * @param handSize the handSize of the cards
	   * @param name the name of the evaluator
	   * @param minimumCards the minimum amount of cards for the evaluator to work
	   */
	  protected AbstractEvaluator(int ranking, int handSize, String name, int minimumCards) {
		  this.ranking = ranking;
		  this.handSize = handSize;
		  this.name = name;
		  this.minimumCards = minimumCards;
	  }
	  
	  /**
	   * returns the rank of the evaluator
	   * @return ranking of the eval
	   */
	  public int getRanking() {
		  return this.ranking;
	  }
	  
	  /**
	   * returns the handSize
	   * @return handSize of the eval
	   */
	  public int handSize() {
		  return this.handSize;
	  }
	  
	  /**
	   * returns the name of the evaluator
	   * @return name of eval
	   */
	  public String getName() {
		  return name;
	  }
	  
	  /**
	   * returns the number of cards needed for the eval to work
	   * @return minimumCards required for eval
	   */
	  public int cardsRequired() {
		return minimumCards;
	  }
	  
	  /**
	   * Checks if it satisfies the number of cards and if it does, is overridden by the evaluators
	   * @param mainCards the mainCards that should satisfy the eval.
	   * @return if the cards are equal length to the required amount
	   */
	  public boolean canSatisfy(Card[] mainCards) {
		 
		  if(mainCards.length == cardsRequired()) {
			  return true;
		  }
		  return false;
	  }
	  
	  /**
	   * Inputs all of the cards and then finds all of the possible subsets from those cards and returns 
	   * if the cards satisfy the evaluator
	   * @param allCards all of the cards from the array
	   * @return if the subset of all the cards can satisfy the evaluator
	   */
	  public boolean canSubsetSatisfy(Card[] allCards) {
		  if(allCards.length >= cardsRequired()) {
			  ArrayList<int[]> subsets = SubsetFinder.findSubsets(allCards.length, cardsRequired());
			  ArrayList<Hand> hand = new ArrayList<Hand>();
			  ArrayList<Hand> noNull = new ArrayList<Hand>(); 
			  for(int i = 0; i < subsets.size(); i++) {
				  hand.add(createHand(allCards, subsets.get(i)));
			  }
			  for(int i = 0; i < hand.size(); i++) {
				  if(hand.get(i) != null) {
					  noNull.add(hand.get(i));
				  }
			  }
			  for (Hand element : noNull) {
				  return canSatisfy(element.getMainCards());
			  }
		  }
		  return false;
	  }
	
	  /**
	   * Takes all of the cards and a subset and then separates them from main and side cards.
	   * It then creates a hand and then checks if the main satisfies the evaluator, if it doesn't return null
	   * @param allCards all of the cards
	   * @param subset the subset to pick the main cards from
	   * @return the hand that satisfies the eval
	   */
	  public Hand createHand(Card[] allCards, int[] subset) {
		  Card[] mainCards = new Card[cardsRequired()]; 
		  Card[] sideCards = new Card[allCards.length - cardsRequired()]; 
		  ArrayList<Card> tempCards = new ArrayList<Card>();
		  for(int i = 0; i < subset.length; i++) { 
			  mainCards[i] = allCards[subset[i]]; 
		  } 
		  for(int i = 0; i < allCards.length; i++) {
			  tempCards.add(allCards[i]);
		  }
		  
		  Arrays.sort(subset);
		  
		  for(int i = subset.length - 1; i >= 0; i--) {
				tempCards.remove(subset[i]);
		  }
		  
		  for(int i = 0; i < tempCards.size() - cardsRequired(); i++) {
			  sideCards[i] = tempCards.get(i);
		  }
		  Hand hand = new Hand(mainCards, sideCards, this);
		  	  
		  if(canSatisfy(mainCards) == true) {
			  return hand;
		  } else {
			  return null;
		  }
	  }
	  	
	  /**
	   * Takes in all of the cards and then finds all of the possible subsets. it then compares all of the cards
	   * and checks for which one is the best and returns the best hand.
	   * @param allCards all of the cards
	   * @return the best hand possible for the eval, return null if none.
	   */
	  public Hand getBestHand(Card[] allCards) {	
		  if(allCards.length > handSize()) { // For StraightFlushEvaluator, >= works while > does not.
			  ArrayList<int[]> subset = SubsetFinder.findSubsets(allCards.length, cardsRequired());
			  ArrayList<Hand> createdHands = new ArrayList<Hand>();
			  Hand[] Hands = new Hand[subset.size()];
			  for(int i = 0; i < Hands.length; i++) {
				  Hands[i] = createHand(allCards, subset.get(i));
				  if(Hands[i] != null) {
					  createdHands.add(Hands[i]);
				  }
			  }
			  
			  if(createdHands.size() == 1) {
				  if(createdHands.get(0).getMainCards().length == cardsRequired()) {
					  return createdHands.get(0);
				  }
			  }
			  
			  for(int i = 0 ; i < createdHands.size() - 1; i++) {
				  if((createdHands.get(i).compareTo(createdHands.get(i + 1))) == 0) {
					  if(createdHands.get(i).getMainCards().length == cardsRequired()) {
						  return createdHands.get(i);
					  }
				  }
				  if((createdHands.get(i).compareTo(createdHands.get(i + 1))) < 0) {
					  if(createdHands.get(i).getMainCards().length == cardsRequired()) {
						  return createdHands.get(i);
					  }
				  } 
			  }
		  }
		  return null;
	  }
}
