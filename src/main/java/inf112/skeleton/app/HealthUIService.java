package inf112.skeleton.app;

import javafx.scene.canvas.GraphicsContext;

public class HealthUIService implements IDrawable {

    private GraphicsContext context;
    private IHealthManager healthManager;

    public HealthUIService(GraphicsContext context) {
        this.context = context;
    }

    @Override
    public void Draw() {

    }
}
