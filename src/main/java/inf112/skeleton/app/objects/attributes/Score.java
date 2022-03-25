package inf112.skeleton.app.objects.attributes;

public class Score {
	int amount;
	
	public Score(int amount) {
        this.amount = amount;
    }
	
	public int getScore() {
		return amount;
	}
	
	public void addOneToScore() {
		this.amount++;
	}
	
	public void subtractOneFromScore() {
		this.amount--;
	}
	
	public void subtractTenFromScore() { 
		this.amount -= 10;
	}

}
