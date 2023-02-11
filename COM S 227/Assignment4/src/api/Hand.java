package api;
import java.util.Arrays;

/**
 * A Hand represents a collection of cards satisfying some
 * criteria defined by an instance of <code>IEvaluator</code>.
 * A hand has zero or more <em>main cards</em> that determine
 * whether it satisfies the evaluator's criteria, plus zero
 * or more <em>side cards</em> that are irrelevant to the
 * evaluator's criteria but may be used to determine a winning
 * hand when the main cards are the same, disregarding suits.
 * 
 * This class is immutable.
 */
public class Hand implements Comparable<Hand>
{
  /**
   * Main cards for this hand.
   */
  private final Card[] mainCards;
  
  /**
   * Side cards for this hand.
   */
  private final Card[] sideCards;
  
  /**
   * Name for this hand (name of the evaluator used to construct it).
   */
  private final String name;
  
  /**
   * Ranking for this hand (as given by the evaluator used to construct it).
   */
  private final int ranking;
  
  /**
   * Constructs a new Hand from the given cards. The number of mainCards
   * given must match the number of required cards specified by the
   * given evaluator and the main card array must be ordered so that 
   * lexicographic comparison with another array satisfying the same
   * evaluator will correctly order the hands.  The number of side cards
   * given must be sufficient to complete the total size of the hand
   * as specified by the evaluator, and the side cards must be ordered
   * according to the compareTo method of the Card class.  It is 
   * the responsibility of the caller to ensure that the main cards
   * satisfy the criteria of the evaluator.
   * 
   * @param mainCards
   *   main cards for the hand
   * @param sideCards
   *   side cards for the hand
   * @param evaluator
   *   reference to an IEvaluator whose criteria are satisfied by this hand
   * @throws IllegalArgumentException
   *   if the number of main cards is incorrect or the number of
   *   side cards is insufficient
   */
  public Hand(Card[] mainCards, Card[] sideCards, IEvaluator evaluator)
  {
    // check whether we have the correct number of main cards
    // and enough side cards to complete the hand
    int mainCount = 0;
    if (mainCards != null)
    {
      mainCount = mainCards.length;
    }
    if (mainCount != evaluator.cardsRequired())
    {
      throw new IllegalArgumentException("Wrong number of main cards: " + mainCount);
    }
    int sideCount = 0;
    if (sideCards != null)
    {
      sideCount = sideCards.length;
    }
    if (mainCount + sideCount < evaluator.handSize())
    {
      throw new IllegalArgumentException("Insufficient total cards: " + (mainCount + sideCount));      
    }
    
    
    // copy the arrays
    if (mainCards != null)
    {
      this.mainCards = Arrays.copyOf(mainCards, mainCount);
    }
    else
    {
      this.mainCards = new Card[0];
    }
    if (sideCards != null)
    {
      // only keep the number of side cards we really need
      sideCount = evaluator.handSize() - mainCount;
      this.sideCards = Arrays.copyOf(sideCards, sideCount);
    }
    else
    {
      this.sideCards = new Card[0];
    }

    // save the name and ranking
    name = evaluator.getName();
    ranking = evaluator.getRanking();
  }

  /**
   * Returns the ranking of this hand according to the evaluator
   * used in its construction.
   * @return
   *   ranking of this hand
   */
  public int getRanking()
  {
    return ranking;
  }
  
  /**
   * Returns the name of this hand according to the evaluator 
   * used in its construction.
   * @return
   *   name of this hand
   */
  public String getName()
  {
    return name;
  }
  
  /**
   * Returns a copy of the main cards for this hand.
   * @return
   *   main cards for this hand
   */
  public Card[] getMainCards()
  {
    return Arrays.copyOf(mainCards, mainCards.length);
  }
  
  /**
   * Returns a copy of the side cards for this hand.
   * @return
   *   side cards for this hand
   */
  public Card[] getSideCards()
  {
    return Arrays.copyOf(sideCards, sideCards.length);
  }
  
  /**
   * Returns a string representation of this object.
   */
  public String toString()
  {
    StringBuilder sb = new StringBuilder();
    sb.append(getName());
    sb.append(" (");
    sb.append(getRanking());
    sb.append(") [");
    if (mainCards.length > 0)
    {
      sb.append(mainCards[0].toString());
      for (int i = 1; i < mainCards.length; ++i)
      {
        sb.append(" ");
        sb.append(mainCards[i].toString());
      }
    }
    if (sideCards.length > 0)
    {
      sb.append(" :");
      for (int i = 0; i < sideCards.length; ++i)
      {
        sb.append(" ");
        sb.append(sideCards[i].toString());
      }
    }
    
    sb.append("]");
    return sb.toString();
  }
  
  @Override
  public int compareTo(Hand other)
  {
    if (getRanking() != other.getRanking())
    {
      return getRanking() - other.getRanking();
    } 
    
    int c = compareCardArrays(mainCards, other.mainCards);
    if (c != 0)
    {
      return c;
    }
    return compareCardArrays(sideCards, other.sideCards);
  }
  
  /**
   * Lexicographically compares two arrays of cards having
   * the same length, ignoring suits.
   * @param lhs
   * @param rhs
   * @return
   */
  private int compareCardArrays(Card[] lhs, Card[] rhs)
  {
    if (lhs.length != rhs.length)
    {
      throw new IllegalArgumentException();
    }
        
    // Lexicographic ordering, note both should be the same length
    for (int i = 0; i < lhs.length; ++i)
    {
      int comp = lhs[i].compareToIgnoreSuit(rhs[i]);
      if (comp != 0)
      {
        return comp;
      }
    }
    return 0;
  }

}
