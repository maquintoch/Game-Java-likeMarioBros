package inf112.skeleton.app;

import javafx.scene.canvas.Canvas;

import java.util.ArrayList;
import java.util.Collection;

public class GameWorld implements IDrawable, IUpdateable {

    private Collection<IDrawable> drawPipeline;
    private Collection<IUpdateable> updatePipeline;

    public GameWorld(Canvas canvas, IInputHandler inputHandler) {
        var levelFactory = new LevelFactory(canvas);
        var factoryTiles = levelFactory.GetTiles();
        var Tiles = new TileCollection(factoryTiles);
        var backgroundDrawService = new GameBackgroundDrawService(canvas);

        var player = new PlayerEntity(canvas, factoryTiles, inputHandler);

        drawPipeline = new ArrayList<IDrawable>();
        updatePipeline = new ArrayList<IUpdateable>();

        drawPipeline.add(backgroundDrawService);
        drawPipeline.add(Tiles);
        drawPipeline.add(player);

        updatePipeline.add(player);
    }

    @Override
    public void Draw() {
        for(IDrawable drawable : drawPipeline) {
            drawable.Draw();
        }
    }

    @Override
    public void Update() {
        for(IUpdateable updateable : updatePipeline) {
            updateable.Update();
        }
    }
}
