package inf112.skeleton.app.services;

import inf112.skeleton.app.objects.attributes.Score;

public class CoinManager {
	private Score score = new Score(0);
	
	public Score getScore() {
        return score;
    }
	
	/*
    public Score WinningScore() {
        return new Score(100);
    }*/

}
