package fr.seb.kata;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;

public class TennisGameTest {

	@Test
	void classicGameTest() {
		assertDoesNotThrow(() -> new TennisGame().play("AAAA"));
	}
}
