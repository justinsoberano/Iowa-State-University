package hw2;

/**
 * Model of a Monopoly-like game. Two players take turns rolling dice to move
 * around a board. The game ends when one of the players has at least
 * MONEY_TO_WIN money or one of the players goes bankrupt (has negative money).
 * 
 * @author justin
 */
public class CyGame {
	/**
	 * Do nothing square type.
	 */
	public static final int DO_NOTHING = 0;
	/**
	 * Pass go square type.
	 */
	public static final int PASS_GO = 1;
	/**
	 * Cyclone square type.
	 */
	public static final int CYCLONE = 2;
	/**
	 * Pay the other player square type.
	 */
	public static final int PAY_PLAYER = 3;
	/**
	 * Get an extra turn square type.
	 */
	public static final int EXTRA_TURN = 4;
	/**
	 * Jump forward square type.
	 */
	public static final int JUMP_FORWARD = 5;
	/**
	 * Stuck square type.
	 */
	public static final int STUCK = 6;
	/**
	 * Points awarded when landing on or passing over go.
	 */
	public static final int PASS_GO_PRIZE = 200;
	/**
	 * The amount payed to the other player per unit when landing on a
	 * PAY_PLAYER square.
	 */
	public static final int PAYMENT_PER_UNIT = 20;
	/**
	 * The amount of money required to win.
	 */
	public static final int MONEY_TO_WIN = 400;
	/**
	 * The cost of one unit.
	 */
	public static final int UNIT_COST = 50;
	/**
	 * Tracks the current player
	 */
	private int currentPlayer;
	/**
	 * The position that player1 is on
	 */	
	private int player1Square;
	/**
	 * The position that player2 is on
	 */
	private int player2Square;
	/**
	 * The amount of money player1 holds
	 */
	private int player1Money;
	/**
	 * The amount of money player2 holds
	 */
	private int player2Money;
	/**
	 * The amount of units player1 has
	 */
	private int player1Units;
	/**
	 * The amount of units player2 has
	 */
	private int player2Units;
	/**
	 * Keeps track of the board size
	 */
	private int boardSize;
	
	/**
	 * Constructs a new CyGame with the given board size and 
	 * amount of money each player should start with. Both players 
	 * start with 1 unit and player 1 goes first
	 * @param boardSize The size of the board
	 * @param money The amount of money the players will start with
	 */
	public CyGame(int boardSize, int money) {
		currentPlayer = 1;
		player1Money = money;
		player2Money = money;
		this.boardSize = boardSize;
		player1Units = 1;
		player2Units = 1;
	}
	
	/**
	 * Determines what the type of that square is
	 * @param numSquare The square that the player is on
	 * @return The type of square
	 */
	public int getSquareType(int numSquare) {
		
		if (numSquare == 0) {
			return PASS_GO;
		} else if ((boardSize - 1) == numSquare) {
			return CYCLONE;
		} else if ((numSquare % 5) == 0 ) {
			return PAY_PLAYER;
		} else if ((numSquare % 7) == 0 || (numSquare % 11) == 0) {
			return EXTRA_TURN;
		} else if ((numSquare % 3) == 0 ) {
			return STUCK;
		} else if ((numSquare % 2) == 0 ) {
			return JUMP_FORWARD;
		} else {
			return DO_NOTHING;
		}
	}
	
	/**
	 * Returns the square that player1 is on
	 * @return player1 square
	 */
	public int getPlayer1Square() {
		return player1Square;
	}
	
	/**
	 * Returns the square that player2 is on
	 * @return player2 square
	 */
	public int getPlayer2Square() {
		return player2Square;
	}
	
	/**
	 * Returns the units that player1 has
	 * @return player1 units
	 */
	public int getPlayer1Units() {
		return player1Units;
	}
	
	/**
	 * Returns the units that player2 has
	 * @return player2 units
	 */
	public int getPlayer2Units() {
		return player2Units;
	}
	
	/**
	 * Returns the amount of money that player1 holds
	 * @return player1 money
	 */
	public int getPlayer1Money() {
		return player1Money;
	}
	
	/**
	 * Returns the amount of money that player2 holds
	 * @return player2 money
	 */
	public int getPlayer2Money() {
		return player2Money;
	}
	
	/**
	 * Returns the current player
	 * @return Current player
	 */
	public int getCurrentPlayer() {
		return currentPlayer;
	}
	
	/**
	 * Ends the turn of the current playing 
	 * player and sets it to the other player
	 */
	public void endTurn() {
		
		if (getCurrentPlayer() == 1) {
			currentPlayer = 2;
		} else if (getCurrentPlayer() == 2) {
			currentPlayer = 1;
		}
		
	}
	
	/**
	 * Allows players to buy a unit if they 
	 * are on Do Nothing square type and the 
	 * game has not ended
	 */
	public void buyUnit() {
		if (getSquareType(player1Square) == 0 && getCurrentPlayer() == 1 && player1Money > UNIT_COST && isGameEnded() == false) {
				player1Money -= 50;
				player1Units += 1;
				endTurn();
		}
		else if (getSquareType(player2Square) == 0 && getCurrentPlayer() == 2 && player2Money > UNIT_COST && isGameEnded() == false) {
				player2Money -= 50;
				player2Units += 1;
				endTurn();
		}
	}
	
	/**
	 * Checks if the game has ended
	 * @return If the game ended
	 */
	public boolean isGameEnded() {
		return ((player1Money >= MONEY_TO_WIN) || (player2Money >= MONEY_TO_WIN) || (player1Money < 0) || (player2Money < 0));
	}
	
	/**
	 * Allows players to sell their units if 
	 * they have at least 1 and the game has 
	 * not ended
	 */
	public void sellUnit() {
		if (getCurrentPlayer() == 1 && player1Units > 0 && isGameEnded() == false) {
			player1Money += 50;
			player1Units -= 1;
			endTurn();
		} else if (getCurrentPlayer() == 2 && player1Units > 0 && isGameEnded() == false) {
			player2Money += 50;
			player2Units -= 1;
			endTurn();
		}
	}
	
	/**
	 * Allows the player to move to different squares. First 
	 * checks if the player is on the stuck square, if not it 
	 * will then check what type of square it is and then execute 
	 * the square's rule. 
	 * @param rollValue the value that the player rolled
	 */
	public void roll(int rollValue) {
	    if (currentPlayer == 1 && (getSquareType(player1Square) != STUCK || rollValue % 2 == 0) && isGameEnded() == false ) {
	    	player1Square += rollValue;
	    	if (getSquareType(player1Square) == JUMP_FORWARD) {
	    		player1Square += 4;
	    	} 
	    	if (getSquareType(player1Square) == PAY_PLAYER && rollValue != boardSize) {
	    		player1Money -= (player2Units * PAYMENT_PER_UNIT);
	    		player2Money += (player2Units * PAYMENT_PER_UNIT);	
	    	}
	    	if (getSquareType(player1Square) == EXTRA_TURN) {
	    		endTurn();
	    	}
	    	if (getSquareType(player1Square) == CYCLONE) {
	    		player1Square = player2Square;
	    	} 
	    	if (player1Square >= boardSize) {
	    		player1Square -= boardSize;
	    		player1Money += PASS_GO_PRIZE;
	    	}
	    	endTurn();
	    	
	    } else if (currentPlayer == 2 && isGameEnded() == false && (getSquareType(player2Square) != STUCK || rollValue % 2 == 0)) {
		    player2Square += rollValue;
		    if (getSquareType(player2Square) == JUMP_FORWARD) {
		    	player2Square += 4;
		    } 
		    if (getSquareType(player2Square) == PAY_PLAYER && rollValue != boardSize) {
		    	player2Money -= (player1Units * PAYMENT_PER_UNIT);
		    	player1Money += (player1Units * PAYMENT_PER_UNIT);
		    }
		    if (getSquareType(player2Square) == EXTRA_TURN) {
		    	endTurn();
		    }
		    if (getSquareType(player2Square) == CYCLONE) {
		    	player2Square = player1Square;
		    } 
		    if (player2Square >= boardSize) {
		    	player2Square -= boardSize;
		    	player2Money += PASS_GO_PRIZE;
		    }
		    endTurn();
		    
	    } else {
	    	endTurn();
	    }
	}

	/**
	 * Returns a one-line string representation of the current game state. The
	 * format is:
	 * <p>
	 * <tt>Player 1*: (0, 0, $0) Player 2: (0, 0, $0)</tt>
	 * <p>
	 * The asterisks next to the player's name indicates which players turn it
	 * is. The numbers (0, 0, $0) indicate which square the player is on, how
	 * many units the player has, and how much money the player has
	 * respectively.
	 * 
	 * @return one-line string representation of the game state
	 */
	
	public String toString() {
		String fmt = "Player 1%s: (%d, %d, $%d) Player 2%s: (%d, %d, $%d)";
		String player1Turn = "";
		String player2Turn = "";
		if (getCurrentPlayer() == 1) {
			player1Turn = "*";
		} else {
			player2Turn = "*";
		}
		return String.format(fmt,
				player1Turn, getPlayer1Square(), getPlayer1Units(), getPlayer1Money(),
				player2Turn, getPlayer2Square(), getPlayer2Units(), getPlayer2Money());
	}
}
