package fr.seb.kata;

public class Application {

	public static void main(String[] args) {
		new TennisGame().play("AAABBBAA");
		new TennisGame().play("AAABBBBB");
		new TennisGame().play("AAABBBBAAA");
		new TennisGame().play("ABABABABABABABABABABABABABABABABBB");
	}
}
