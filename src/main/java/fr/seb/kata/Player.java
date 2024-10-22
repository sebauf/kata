package fr.seb.kata;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class Player {

	@Builder.Default
	private Scores score = Scores.ZERO;

	private String name;

}
