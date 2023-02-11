package api;

/**
 * An IEvaluator is a utility for evaluating groups of cards in 
 * card games based on ranking of hands.  Each evaluator 
 * represents a type of hand based on some evaluation criteria,
 * such as pairs of matching cards or consecutive runs.  
 * An evaluator also has a ranking relative to other evaluators
 * that might be used in a given game. In traditional poker games,
 * ranking of hands is based on mathematical probability, but 
 * there is no particular requirement here other than that
 * different evaluators are constructed with different rankings.
 * 
 * Within a given ranking the hands are also ordered.  The ordering
 * is defined by the Hand class.  Each hand consists of an array of 
 * <em>main</em> cards and a (possibly empty) array of <em>side cards</em>.
 * The Hand class orders hands within the same ranking by first
 * lexicographically comparing the main cards, and if they are the same,
 * lexicographically comparing the side cards.  Therefore, it is 
 * up to the evaluator to correctly order the cards in these groups.
 * The side cards can always be sorted according to the Card class
 * compareTo method, which orders cards in descending order with 
 * higher cards first.  In most cases it is sufficient to sort the main cards
 * using the Card class; however, this may not work for particular 
 * evaluators.  For example, in a traditional full house there is a
 * group of three cards and a group of two cards, and they are
 * ordered based on the group of three, even if the group of two
 * consists of cards of higher rank.  Thus, when creating a Hand,
 * the main cards would have to be listed with the group of three 
 * first, e.g., [3 3 3 5 5] should defeat [2 2 2 10 10].
 */
public interface IEvaluator
{
  /**
   * Returns a name for this evaluator.
   * @return
   *   name of this evaluator
   */
  String getName();
  
  /**
   * Returns the ranking for this evaluator, where a lower number
   * is considered "better".
   * @return
   *   ranking for this evaluator
   */
  int getRanking();
  
  /**
   * Returns the minimum number of cards needed for a hand
   * produced by this evaluator (main cards only).
   * @return
   */
  int cardsRequired();
  
  /**
   * Returns the number of cards in a hand.  This value is generally
   * defined by a game, and is not necessarily the same as
   * the value returned by <code>cardsRequired</code>
   * (main cards plus side cards).
   * @return
   *   number of cards in a hand
   */
  int handSize();
  
  /**
   * Determines whether the given group of cards satisfies the
   * criteria this evaluator.  The length
   * of the given array must exactly match the value 
   * returned by <code>cardsRequired()</code>.  The
   * given array must be sorted with highest-ranked card first
   * according to <code>Card.compareTo()</code>.  The array
   * is not modified by this operation.
   * @param mainCards
   *   array of cards
   * @return
   *   true if the given cards satisfy this evaluator
   */
  boolean canSatisfy(Card[] mainCards);
  
  /**
   * Determines whether there exists a subset of the given cards
   * that satisfies the criteria for this evaluator.  The length of
   * the given array must be greater than or equal to the value
   * returned by <code>cardsRequired()</code>. The
   * given array must be sorted with highest-ranked card first
   * according to <code>Card.compareTo()</code>.  The array
   * is not modified by this operation.
   * @param allCards
   *   list of cards to evaluate
   * @return
   *   true if some subset of the given cards satisfy this evaluator
   */
  boolean canSubsetSatisfy(Card[] allCards);
  
  /**
   * Returns a hand whose main cards consist of the indicated subset
   * of the given cards.  If the indicated subset does
   * not satisfy the criteria for this evaluator, this
   * method returns null. The subset is described as
   * an ordered array of indices to be selected from the given
   * Card array.  The number of main cards in the hand
   * will be the value of <code>getRequiredCards()</code>
   * and the total number of cards in the hand will
   * be the value of <code>handSize()</code>.  If the length
   * of the given array of cards is less than <code>handSize()</code>,
   * this method returns null.   The
   * given array must be sorted with highest-ranked card first
   * according to <code>Card.compareTo()</code>.  The array
   * is not modified by this operation.
   * 
   * @param allCards
   *   list of cards from which to select the main cards for the hand
   * @param subset
   *   list of indices of cards to be selected, in ascending order
   * @return
   *   hand whose main cards consist of the indicated subset, or null
   *   if the indicated subset does not satisfy this evaluator
   */
  Hand createHand(Card[] allCards, int[] subset);
  
  /**
   * Returns the best possible hand satisfying this evaluator's 
   * criteria that can be formed from the given list of cards.
   * "Best" is defined to be first according to the compareTo() method of 
   * Hand.  Returns null if there is no such hand.  The number of main cards 
   * in the hand will be the value of <code>getRequiredCards()</code>
   * and the total number of cards in the hand will
   * be the value of <code>handSize()</code>.  If the length
   * of the given array of cards is less than <code>totalCards()</code>,
   * this method returns null.  The
   * given array must be sorted with highest-ranked card first
   * according to <code>Card.compareTo()</code>.  The array
   * is not modified by this operation.
   *  
   * @param allCards
   *   list of cards from which to create the hand
   * @return
   *   best possible hand satisfying this evaluator that can be formed
   *   from the given cards
   */
  Hand getBestHand(Card[] allCards);
}
