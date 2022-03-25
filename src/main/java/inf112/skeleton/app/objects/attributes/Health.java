package inf112.skeleton.app.objects.attributes;

public class Health {
    int amount;

    public Health(int amount) {
        this.amount = amount;
    }
    
    public int getHealth() {
		return amount;
	}
    public void loseHealth() {
    	amount--;
    }
}