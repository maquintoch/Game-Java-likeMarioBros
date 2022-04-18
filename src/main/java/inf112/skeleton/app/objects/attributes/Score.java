package inf112.skeleton.app.objects.attributes;

public class Score {
	int amount;
	
	public Score(int amount) {
        this.amount = amount;
    }
	
	public int getScore() {
		return amount;
	}
	
	public void addScore(int score) {
		this.amount += score;
	}
}
