package inf112.skeleton.app.draw;

import inf112.skeleton.app.objects.attributes.Health;
import inf112.skeleton.app.services.HealthManager;
import inf112.skeleton.app.services.IHealthManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class HealthUI{
    private GraphicsContext context;
    private IHealthManager healthManager;
    public Health currentHealth;

    public HealthUI(GraphicsContext context) {
        this.context = context;
        this.healthManager = new HealthManager();
        this.currentHealth = healthManager.getHealth();
    }

    public void draw() {
        Health currenthealth = healthManager.getHealth();
        Health maxHealth = healthManager.getMaxHealth();
        context.setFill(Color.RED);
        context.fillRect(0,0,maxHealth.getHealth(),10);
        context.setFill(Color.GREEN);
        context.fillRect(0,0, currenthealth.getHealth(), 10);   
    }
}
