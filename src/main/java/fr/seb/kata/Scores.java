package fr.seb.kata;

import java.util.stream.Stream;
import lombok.Getter;

@Getter
public enum Scores {

  ZERO("0", 0), FIFTEEN("15", 1), THIRTY("30", 2), FORTY("40", 3), ADVANTAGE("ADVANTAGE", 4);

  private String scoreDisplayed;
  private Integer order;

  Scores(String scoreDisplayed, Integer order) {
    this.scoreDisplayed = scoreDisplayed;
    this.order = order;
  }

  public static Scores getByOrder(Integer orderParam) {
    return Stream.of(Scores.values()).filter(score -> score.order.equals(orderParam)).toList()
        .getFirst();
  }

}
