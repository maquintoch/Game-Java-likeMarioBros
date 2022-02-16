package inf112.skeleton.app;

import javafx.scene.canvas.GraphicsContext;

import java.util.ArrayList;

public class GameWorld implements IDrawable, IUpdateable {

    private IDrawable backgroundDrawService;
    private ArrayList<Tile> Tiles;

    public GameWorld(GraphicsContext context) {
        Tiles = LevelFactory.GetTiles(context);
        backgroundDrawService = new GameBackgroundDrawService(context);
    }

    @Override
    public void Draw() {
        backgroundDrawService.Draw();
        for (Tile tile : Tiles) {
            tile.Draw();
        }
    }

    @Override
    public void Update() {

    }
}
