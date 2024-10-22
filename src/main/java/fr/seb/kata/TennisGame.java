package fr.seb.kata;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

public class TennisGame {

  private static final String PLAYER_A_PLAYER_B = "Player A : %s / Player B : %s";
  private static final String PLAYER_WINS_THE_GAME = "Player %S wins the game";
  private static final String PLAYER_IS_NOT_PLAYING = "Player %c is not playing";


  public void play(String pointsString) {

    Player playerA = Player.builder().name("A").build();
    Player playerB = Player.builder().name("B").build();

    pointsString.chars().forEachOrdered(winnerChar -> {
      Boolean isGameOver = FALSE;
      switch (winnerChar) {
        case 'A':
          isGameOver = updateScore(playerA, playerB);
          break;
        case 'B':
          isGameOver = updateScore(playerB, playerA);
          break;
        default:
          System.out.println(String.format(PLAYER_IS_NOT_PLAYING, winnerChar));
      }
      if (isGameOver) {
        return;
      }
      System.out.println(String.format(PLAYER_A_PLAYER_B, playerA.getScore().getScoreDisplayed(),
          playerB.getScore().getScoreDisplayed()));
    });
  }

  private Boolean updateScore(Player winner, Player loser) {
    Scores winnerScore = winner.getScore();
    if ((Scores.FORTY.equals(winnerScore)
        && loser.getScore().getOrder() <= Scores.THIRTY.getOrder())
        || (Scores.ADVANTAGE.equals(winnerScore)
            && loser.getScore().getOrder() <= Scores.FORTY.getOrder())) {
      System.out.println(String.format(PLAYER_WINS_THE_GAME, winner.getName()));
      return TRUE;
    }
    if (Scores.FORTY.equals(winnerScore) && Scores.ADVANTAGE.equals(loser.getScore())) {
      winner.setScore(Scores.FORTY);
      loser.setScore(Scores.FORTY);
    } else {
      winner.setScore(Scores.getByOrder(winnerScore.getOrder() + 1));
    }
    return FALSE;
  }

}
