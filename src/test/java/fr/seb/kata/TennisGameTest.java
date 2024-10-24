package fr.seb.kata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TennisGameTest {

  private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
  private final PrintStream systemOut = System.out;

  @BeforeEach
  public void redirectStrandardOutput() {
    System.setOut(new PrintStream(outContent));
  }

  @AfterEach
  public void restoreStrandardOutput() {
    System.setOut(new PrintStream(systemOut));
  }


  @Test
  void testClassicGame() {
    ScoreHistory scoreRound1 = new ScoreHistory(Scores.FIFTEEN, Scores.ZERO);
    ScoreHistory scoreRound2 = new ScoreHistory(Scores.THIRTY, Scores.ZERO);
    ScoreHistory scoreRound3 = new ScoreHistory(Scores.FORTY, Scores.ZERO);
    var expectedMatchResult = new MatchResult(List.of(scoreRound1, scoreRound2, scoreRound3),
        Player.builder().name("A").score(Scores.FORTY).build());
    var matchResult = new TennisGame().play("AAAA");

    assertEquals(matchResult, expectedMatchResult);
  }

  @Test
  void testAdvantageForA() {

    ScoreHistory scoreRound1 = new ScoreHistory(Scores.FIFTEEN, Scores.ZERO);
    ScoreHistory scoreRound2 = new ScoreHistory(Scores.THIRTY, Scores.ZERO);
    ScoreHistory scoreRound3 = new ScoreHistory(Scores.FORTY, Scores.ZERO);
    ScoreHistory scoreRound4 = new ScoreHistory(Scores.FORTY, Scores.FIFTEEN);
    ScoreHistory scoreRound5 = new ScoreHistory(Scores.FORTY, Scores.THIRTY);
    ScoreHistory scoreRound6 = new ScoreHistory(Scores.FORTY, Scores.FORTY);
    ScoreHistory scoreRound7 = new ScoreHistory(Scores.ADVANTAGE, Scores.FORTY);
    ScoreHistory scoreRound8 = new ScoreHistory(Scores.FORTY, Scores.FORTY);
    ScoreHistory scoreRound9 = new ScoreHistory(Scores.ADVANTAGE, Scores.FORTY);
    var expectedMatchResult = new MatchResult(
        List.of(scoreRound1, scoreRound2, scoreRound3, scoreRound4, scoreRound5, scoreRound6,
            scoreRound7, scoreRound8, scoreRound9),
        Player.builder().name("A").score(Scores.ADVANTAGE).build());
    var matchResult = new TennisGame().play("AAABBBABAA");

    assertEquals(matchResult, expectedMatchResult);
  }

  @Test
  void testNoWinner() {
    ScoreHistory scoreRound1 = new ScoreHistory(Scores.FIFTEEN, Scores.ZERO);
    ScoreHistory scoreRound2 = new ScoreHistory(Scores.THIRTY, Scores.ZERO);
    ScoreHistory scoreRound3 = new ScoreHistory(Scores.FORTY, Scores.ZERO);
    ScoreHistory scoreRound4 = new ScoreHistory(Scores.FORTY, Scores.FIFTEEN);
    ScoreHistory scoreRound5 = new ScoreHistory(Scores.FORTY, Scores.THIRTY);
    ScoreHistory scoreRound6 = new ScoreHistory(Scores.FORTY, Scores.FORTY);
    ScoreHistory scoreRound7 = new ScoreHistory(Scores.ADVANTAGE, Scores.FORTY);
    ScoreHistory scoreRound8 = new ScoreHistory(Scores.FORTY, Scores.FORTY);
    var expectedMatchResult = new MatchResult(List.of(scoreRound1, scoreRound2, scoreRound3,
        scoreRound4, scoreRound5, scoreRound6, scoreRound7, scoreRound8), null);

    var matchResult = new TennisGame().play("AAABBBAB");
    assertEquals(matchResult, expectedMatchResult);
  }

  @Test
  void testNoInput() {
    var matchResult = new TennisGame().play("");
    assertNull(matchResult);
  }

  @Test
  void testNullInput() {
    var matchResult = new TennisGame().play(null);
    assertNull(matchResult);
  }

  @Test
  void testBadInput() {
    ScoreHistory scoreRound1 = new ScoreHistory(Scores.ZERO, Scores.ZERO);
    var expectedMatchResult = new MatchResult(List.of(scoreRound1), null);
    var matchResult = new TennisGame().play("X");
    assertEquals(matchResult, expectedMatchResult);
  }

  @Test
  void testOutputWithWinner() {
    String expected = """
        Player A : 15 / Player B : 0
        Player A : 30 / Player B : 0
        Player A : 40 / Player B : 0
        Player A : 40 / Player B : 15
        Player A : 40 / Player B : 30
        Player A : 40 / Player B : 40
        Player A : ADVANTAGE / Player B : 40
        Player A : 40 / Player B : 40
        Player A : 40 / Player B : ADVANTAGE
        Player B wins the game
        """.replace("\n", "\r\n");
    new TennisGame().play("AAABBBABBB");
    assertEquals(expected, outContent.toString());
  }

  @Test
  void testOutputWithoutWinner() {
    String expected = """
        Player A : 15 / Player B : 0
        Player A : 30 / Player B : 0
        Player A : 40 / Player B : 0
        Player A : 40 / Player B : 15
        Player A : 40 / Player B : 30
        Player A : 40 / Player B : 40
        Player A : ADVANTAGE / Player B : 40
        Player A : 40 / Player B : 40
        """.replace("\n", "\r\n");
    new TennisGame().play("AAABBBAB");
    assertEquals(expected, outContent.toString());
  }

}
