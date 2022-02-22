package inf112.skeleton.app;

public class Score {
	int amount;
	
	public Score(int amount) {
        this.amount = amount;
    }
	
	public int getScore() {
		return amount;
	}
	
	public void AddOneToScore() {
		amount++;
	}
	
	public void SubtractOneFromScore() {
		amount--;
	}

}
