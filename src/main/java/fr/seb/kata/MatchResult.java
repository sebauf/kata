package fr.seb.kata;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;


@AllArgsConstructor
@Data
@EqualsAndHashCode
public class MatchResult {

  private List<ScoreHistory> scoreHistoryList;

  private Player winner;

}
