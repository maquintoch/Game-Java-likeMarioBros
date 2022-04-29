package inf112.skeleton.app.draw;

import inf112.skeleton.app.game.gameworld.IHealthObserver;
import inf112.skeleton.app.objects.attributes.Health;
import inf112.skeleton.app.services.HealthManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class HealthUI implements IHealthObserver {
    private GraphicsContext context;
    private HealthManager healthManager;
    public Health currentHealth;

    public HealthUI(GraphicsContext context) {
        this.context = context;
        this.healthManager = new HealthManager();
        this.currentHealth = healthManager.getHealth();
    }

    public void draw(double x, double y) {
        Health currenthealth = healthManager.getHealth();
        Health maxHealth = healthManager.getMaxHealth();
        context.setFill(Color.RED);
        context.fillRect(x,y, 100,10);
        context.setFill(Color.GREEN);
        context.fillRect(x,y, ((double)currenthealth.getHealth() / (double)maxHealth.getHealth()) * (double)100, 10);
    }

    @Override
    public void setHealth(int amount) {
        currentHealth.setHealth(amount);
    }
}
