package inf112.skeleton.app.Graphics;


import inf112.skeleton.app.Services.HealthManager;
import inf112.skeleton.app.Services.IHealthManager;
import inf112.skeleton.app.DrawBehavior.IDrawable;
import inf112.skeleton.app.GameWorld.Entity.EntityAttributes.Health;
import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class HealthUIService implements IDrawable {

    private GraphicsContext context;
    private IHealthManager healthManager;

    public HealthUIService(GraphicsContext context) {
        this.context = context;
        this.healthManager = new HealthManager();
    }

    @Override
    public void Draw() {
        Health currenthealth = healthManager.GetHealth();
        Health maxHealth = healthManager.GetMaxHealth();
        context.setFill(Color.RED);
        context.fillRect(0,0,maxHealth.getHealth(),10);
        context.setFill(Color.GREEN);
        context.fillRect(0,0, currenthealth.getHealth(), 10);
        
    }
}
