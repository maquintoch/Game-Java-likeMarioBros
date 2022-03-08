package inf112.skeleton.app;

import inf112.skeleton.app.Entity.EntityAttributes.Score;

public class CoinManager {
	private Score score = new Score(0);
	
	public Score GetScore() {
        return score;
    }
	
	/*
    public Score WinningScore() {
        return new Score(100);
    }*/

}
