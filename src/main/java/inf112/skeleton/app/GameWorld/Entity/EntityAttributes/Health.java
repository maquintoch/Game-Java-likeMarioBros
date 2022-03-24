package inf112.skeleton.app.GameWorld.Entity.EntityAttributes;

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
