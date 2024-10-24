package fr.seb.kata;

import java.util.LinkedList;
import java.util.List;

public class TennisGame {

  private static final String PLAYER_A_PLAYER_B = "Player A : %s / Player B : %s";
  private static final String PLAYER_WINS_THE_GAME = "Player %S wins the game";
  private static final String PLAYER_IS_NOT_PLAYING = "Player %c is not playing";

  public MatchResult play(String pointsString) {
    if (pointsString == null || pointsString.isBlank()) {
      return null;
    }

    Player playerA = Player.builder().name("A").build();
    Player playerB = Player.builder().name("B").build();

    List<ScoreHistory> scoreHistoryList = new LinkedList<>();
    MatchResult matchResult = new MatchResult(scoreHistoryList, null);
    pointsString.chars().forEachOrdered(winnerChar -> {
      Player winner = null;
      switch (winnerChar) {
        case 'A':
          winner = updateScore(playerA, playerB);
          break;
        case 'B':
          winner = updateScore(playerB, playerA);
          break;
        default:
          System.out.println(String.format(PLAYER_IS_NOT_PLAYING, winnerChar));
      }
      if (winner != null) {
        matchResult.setWinner(winner);
        return;
      }
      scoreHistoryList.addLast(new ScoreHistory(playerA.getScore(), playerB.getScore()));
    });

    dumpResult(matchResult);
    return matchResult;
  }

  private Player updateScore(Player winner, Player loser) {
    Scores winnerScore = winner.getScore();
    if ((Scores.FORTY.equals(winnerScore)
        && loser.getScore().getOrder() <= Scores.THIRTY.getOrder())
        || (Scores.ADVANTAGE.equals(winnerScore)
            && loser.getScore().getOrder() <= Scores.FORTY.getOrder())) {
      return winner;
    }
    if (Scores.FORTY.equals(winnerScore) && Scores.ADVANTAGE.equals(loser.getScore())) {
      winner.setScore(Scores.FORTY);
      loser.setScore(Scores.FORTY);
    } else {
      winner.setScore(Scores.getByOrder(winnerScore.getOrder() + 1));
    }
    return null;
  }

  private void dumpResult(MatchResult matchResult) {
    matchResult.getScoreHistoryList().stream()
        .forEachOrdered(histo -> System.out.println(String.format(PLAYER_A_PLAYER_B,
            histo.playerAScore().getScoreDisplayed(), histo.playerBScore().getScoreDisplayed())));
    if (matchResult.getWinner() != null) {
      System.out.println(String.format(PLAYER_WINS_THE_GAME, matchResult.getWinner().getName()));
    }
  }

}
