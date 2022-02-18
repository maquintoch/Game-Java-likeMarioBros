package inf112.skeleton.app;

import javafx.beans.binding.DoubleBinding;
import javafx.beans.property.DoubleProperty;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class HealthUIService implements IDrawable {

    private GraphicsContext context;
    private IHealthManager healthManager;
    private Health currenthealth = healthManager.GetHealth();
    private Health maxHealth = healthManager.GetMaxHealth();

    public HealthUIService(GraphicsContext context) {
        this.context = context;
    }

    @Override
    public void Draw() {
        context.setFill(Color.RED);
        context.fillRect(0,0,maxHealth.amount,10);
        context.setFill(Color.GREEN);
        context.fillRect(0,0, currenthealth.amount, 10);
    }
}
