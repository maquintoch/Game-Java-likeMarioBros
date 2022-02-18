package inf112.skeleton.app;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class GameWorld implements IDrawable, IUpdateable {

    private Collection<IDrawable> drawPipeline;

    public GameWorld(Canvas canvas) {
        var levelFactory = new LevelFactory(canvas);
        var factoryTiles = levelFactory.GetTiles();
        var Tiles = new TileCollection(factoryTiles);
        var backgroundDrawService = new GameBackgroundDrawService(canvas);

        drawPipeline = new ArrayList<IDrawable>();

        drawPipeline.add(backgroundDrawService);
        drawPipeline.add(Tiles);
    }

    @Override
    public void Draw() {
        for(IDrawable drawable : drawPipeline) {
            drawable.Draw();
        }
    }

    @Override
    public void Update() {

    }
}
