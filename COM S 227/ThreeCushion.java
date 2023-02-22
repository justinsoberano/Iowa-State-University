package hw2;

import api.PlayerPosition;
import api.BallType;
import static api.PlayerPosition.*;
import static api.BallType.*;

/**
 * A model of the game of three cushion billiards.
 * 
 * @author tancreti
 */
public class ThreeCushion {
	private PlayerPosition lagWinner;
	private PlayerPosition inningPlayer;
	private BallType cueBall;
	private int playerAscore;
	private int playerBscore;
	private int inning;
	private int pointsToWin;
	private BallType firstObjectBallStrike;
	private BallType secondObjectBallStrike;
	private int cushionImpactCount;
	private boolean isBreakShot;
	private boolean isShotStarted;
	private boolean isInningStarted;
	private boolean isGameStarted;
	private boolean isShotValid;
	private boolean isFoul;
	private boolean isBankShot;

	/**
	 * Creates a new game of three-cushion billiards with a given lag winner and the
	 * predetermined number of points required to win the game. The inning count
	 * starts at 1.
	 * 
	 * @param lagWinner   either player A or B
	 * @param pointsToWin the number of points a player needs to reach for the game
	 *                    to end
	 */
	public ThreeCushion(PlayerPosition lagWinner, int pointsToWin) {
		this.lagWinner = lagWinner;
		this.pointsToWin = pointsToWin;
		inning = 1;
	}

	private static PlayerPosition otherPlayer(PlayerPosition player) {
		if (player == PLAYER_A) {
			return PLAYER_B;
		}
		return PLAYER_A;
	}

	private static BallType otherCue(BallType ball) {
		if (ball == WHITE) {
			return YELLOW;
		}
		return WHITE;
	}

	/**
	 * Sets whether the player that won the lag chooses to break (take first shot),
	 * or chooses the other player to break. If this method is called more than once
	 * it should have no effect. In other words, the lag winner can only choose
	 * these options once and may not change their mind afterwards.
	 * 
	 * @param selfBreak if true the lag winner chooses to take the break shot
	 * @param cueBall   the lag winners chosen cue ball (either WHITE or YELLOW)
	 */
	public void lagWinnerChooses(boolean selfBreak, BallType cueBall) {
		if (isGameStarted) {
			// do nothing if not first time called
			return;
		}

		if (selfBreak) {
			inningPlayer = lagWinner;
			this.cueBall = cueBall;
		} else {
			inningPlayer = otherPlayer(lagWinner);
			this.cueBall = otherCue(cueBall);
		}
		isBreakShot = true;
		isGameStarted = true;
		isBankShot = false;
	}

	/**
	 * Indicates the cue stick has struck the given ball. If a shot has not already
	 * begun, indicates the start of a new shot. If this method is called while a
	 * shot is still in progress (i.e., endShot() has not been called for the
	 * previous shot), the player has committed a foul (see the method foul()).
	 * Also, if the player strikes anything other than their own cue ball, they
	 * committed a foul.
	 * <p>
	 * Calling this method signifies both the start of a shot and the start of an
	 * inning, assuming a shot or inning has not already begun, respectively.
	 * <p>
	 * Even if a foul has been committed, calling this method is considered the
	 * start of a shot. That includes even the case when the player strikes a ball
	 * other than their own cue ball. It is expected that the endShot() method will
	 * be called in any case to indicate the end of the shot.
	 * <p>
	 * No play can begin until the lag player has chosen who will break (see
	 * lagWinnerChooses). If this method is called before the break is chosen, it
	 * should do nothing.
	 * <p>
	 * If this method is called after the game has ended, it should do nothing.
	 */
	public void cueStickStrike(BallType ball) {
		if (!isGameStarted || isGameOver()) {
			// do nothing is lag player has not made selections
			return;
		}

		if (isShotStarted) {
			foul();
			return;
		}

		isShotStarted = true;
		isInningStarted = true;
		firstObjectBallStrike = null;
		secondObjectBallStrike = null;
		cushionImpactCount = 0;

		if ((ball == WHITE || ball == YELLOW) && ball == cueBall) {
			isShotValid = true;
			isBankShot = true;
		} else {
			foul();
		}
	}

	/**
	 * Indicates the player's cue ball has struck the given ball.
	 * <p>
	 * A ball strike cannot happen before a stick strike. If this method is called
	 * before the start of a shot (i.e., cueStickStrike() is called), it should do
	 * nothing.
	 * <p>
	 * If this method is called after the game has ended, it should do nothing.
	 */
	public void cueBallStrike(BallType ball) {
		if (!isGameStarted || isGameOver()) {
			// do nothing
			return;
		}

		if (firstObjectBallStrike == null) {
			firstObjectBallStrike = ball;

			if (isBreakShot && ball != RED && !isFoul) {
				foul();
			}

			if (cushionImpactCount < 3) {
				isBankShot = false;
			}
			
		} else if (firstObjectBallStrike != ball) {
			// second object ball strike
			secondObjectBallStrike = ball;
			if (cushionImpactCount < 3) {
				isShotValid = false;
			}
		}
	}

	/**
	 * Indicates the given ball has impacted the given cushion.
	 * <p>
	 * A cushion impact cannot happen before a stick strike. If this method is
	 * called before the start of a shot (i.e., cueStickStrike() is called), it
	 * should do nothing.
	 * <p>
	 * If this method is called after the game has ended, it should do nothing.
	 */
	public void cueBallImpactCushion() {
		if (!isGameStarted || isGameOver()) {
			// do nothing
			return;
		}

		if (isBreakShot && firstObjectBallStrike == null && !isFoul) {
			foul();
		}

		cushionImpactCount++;
	}

	/**
	 * Indicates that all balls have stopped motion. If the shot was valid and no
	 * foul was committed, the player scores 1 point.
	 * <p>
	 * The shot cannot end before it has started with a call to cueStickStrike. If
	 * this method is called before cueStickStrike, it should be ignored.
	 * <p>
	 * A shot cannot end before the start of a shot. If this method is called before
	 * the start of a shot (i.e., cueStickStrike() is called), it should do nothing.
	 * <p>
	 * If this method is called after the game has ended, it should do nothing.
	 */
	public void endShot() {
		if (!isShotStarted || isGameOver()) {
			// do nothing
			return;
		}

		if (isShotValid && secondObjectBallStrike != null && !isFoul) {
			if (inningPlayer == PLAYER_A) {
				playerAscore++;
			} else {
				playerBscore++;
			}
		} else {
			isBankShot = false;
			if (!isFoul) {
				inning++;
				inningPlayer = otherPlayer(inningPlayer);
				cueBall = otherCue(cueBall);
			}
		}

		isInningStarted = false;
		isShotStarted = false;
		isBreakShot = false;
		isFoul = false;
	}

	/**
	 * A foul immediately ends the player's inning, even if the current shot has not
	 * yet ended. When a foul is called, the player does not score a point for the
	 * shot.
	 * <p>
	 * A foul may also be called before the inning has started. In that case the
	 * player whose turn it was to shot has their inning forfeited and the inning
	 * count is increased by one.
	 * <p>
	 * No foul can be called until the lag player has chosen who will break (see
	 * lagWinnerChooses()). If this method is called before the break is chosen, it
	 * should do nothing.
	 * <p>
	 * If this method is called after the game has ended, it should do nothing.
	 */
	public void foul() {
		if (!isGameStarted || isGameOver()) {
			// do nothing
			return;
		}
		isFoul = true;
		inning++;
		isInningStarted = false;
		inningPlayer = otherPlayer(inningPlayer);
		cueBall = otherCue(cueBall);
	}

	/**
	 * Gets the number of points scored by Player A.
	 * 
	 * @return the number of points
	 */
	public int getPlayerAScore() {
		return playerAscore;
	}

	/**
	 * Gets the number of points scored by Player B.
	 * 
	 * @return the number of points
	 */
	public int getPlayerBScore() {
		return playerBscore;
	}

	/**
	 * Gets the inning number. The inning count starts at 1.
	 * 
	 * @return the inning number
	 */
	public int getInning() {
		return inning;
	}

	/**
	 * Gets the cue ball of the current player. If this method is called in between
	 * innings, the cue ball should be the for the player of the upcoming inning. If
	 * this method is called before the lag winner has chosen a cue ball, the cue
	 * ball is undefined (this method may return anything).
	 * 
	 * @return the player's cue ball
	 */
	public BallType getCueBall() {
		return cueBall;
	}

	/**
	 * Gets the current player. If this method is called in between innings, the
	 * current player is the player of the upcoming inning. If this method is called
	 * before the lag winner has chosen to break, the current player is undefined
	 * (this method may return anything).
	 * 
	 * @return the current player
	 */
	public PlayerPosition getInningPlayer() {
		return inningPlayer;
	}

	/**
	 * Returns true if and only if this is the break shot (i.e., the first shot of
	 * the game).
	 * 
	 * @return true if this is the break shot, false otherwise
	 */
	public boolean isBreakShot() {
		return isBreakShot;
	}

	/**
	 * Returns true if and only if the most recently completed shot was a bank shot.
	 * A bank shot is when the cue ball impacts the cushions at least 3 times and
	 * then strikes both object balls.
	 * 
	 * @return true if shot was a bank shot, false otherwise
	 */
	public boolean isBankShot() {
		return isBankShot;
	}

	/**
	 * Returns true if a shot has been taken (see cueStickStrike()), but not ended
	 * (see endShot()).
	 * 
	 * @return true if the shot has been started, false otherwise
	 */
	public boolean isShotStarted() {
		return isShotStarted;
	}

	/**
	 * Returns true if the shooting player has taken their first shot of the inning.
	 * The inning starts at the beginning of the shot (i.e., the shot may not have
	 * ended yet).
	 * 
	 * @return true if the inning has started, false otherwise
	 */
	public boolean isInningStarted() {
		return isInningStarted;
	}

	/**
	 * Returns true if the game is over (i.e., one of the players has reached the
	 * designated number of points to win).
	 * 
	 * @return true if the game is over, false otherwise
	 */
	public boolean isGameOver() {
		return playerAscore == pointsToWin || playerBscore == pointsToWin;
	}

	/**
	 * Returns a one-line string representation of the current game state. The
	 * format is:
	 * <p>
	 * <tt>Player A*: X Player B: Y, Inning: Z</tt>
	 * <p>
	 * The asterisks next to the player's name indicates which player is at the
	 * table this inning. The number after the player's name is their score. Z is
	 * the inning number. Other messages will appear at the end of the string.
	 * 
	 * @return one-line string representation of the game state
	 */
	public String toString() {
		String fmt = "Player A%s: %d, Player B%s: %d, Inning: %d %s%s";
		String playerATurn = "";
		String playerBTurn = "";
		String inningStatus = "";
		String gameStatus = "";
		if (getInningPlayer() == PLAYER_A) {
			playerATurn = "*";
		} else if (getInningPlayer() == PLAYER_B) {
			playerBTurn = "*";
		}
		if (isInningStarted()) {
			inningStatus = "started";
		} else {
			inningStatus = "not started";
		}
		if (isGameOver()) {
			gameStatus = ", game result final";
		}
		return String.format(fmt, playerATurn, getPlayerAScore(), playerBTurn, getPlayerBScore(), getInning(),
				inningStatus, gameStatus);
	}
}