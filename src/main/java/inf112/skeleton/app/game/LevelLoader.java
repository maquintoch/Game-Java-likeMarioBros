package inf112.skeleton.app.game;

import inf112.skeleton.app.game.gameworld.GameWorld;
import inf112.skeleton.app.objects.*;
import inf112.skeleton.app.objects.attributes.Position;

import java.util.*;

public class LevelLoader {

    private final List<String> level1 = Arrays.asList(
            "...............................",
            "...............................",
            "...............................",
            ".......ee......................",
            "....e.e..bb....................",
            "..be.ee..c.e...c.b.............",
            "..bbbbbbbb.......b..bbbbbbb.bbb",
            ".........b.......b.............",
            "..bbb....b.......b.........bbb.",
            ".........b...t...b.b...........",
            ".........bbbbbbbbb...........b.",
            ".....c................c.........",
            ".....................bb..b.....",
            ".....bcb..........c......b.....",
            ".....bbb.............bc.eb.....",
            ".................c...bbbbb.....",
            "...............................",
            ".......................bb......",
            "..bb..bb.......................",
            "...b..b...e....................",
            "...b..e..ce..t.........bc..e.b.",
            "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"
    );
    private final List<String> level2 = Arrays.asList(
            "...........................c...",
            ".........................eb....",
            "........................bbb....",
            "...............................",
            ".........bb..........b.........",
            "..b.........be...b..b..ec.b....",
            "..bbbbb...b.bbbbbb..bbbbbbb.bb.",
            "...............................",
            "..bbb.....................e..b.",
            "......................bbbbbbbb.",
            "...........b..........b......b.",
            "......................b..bec.b.",
            ".....b.ce.e..b........b..bbbbb.",
            ".....bbbbbbbbbb...c...b..b.....",
            ".b...bbb..............b.bbbbb..",
            ".b.........bbbb..c....b..b.....",
            ".b...........e.................",
            "........b...bbbbbbb............",
            "...b.ecb.......bb...........b..",
            "b...bbbb..bbbbb..bb....b....b..",
            "...b.e...c.e....ceb....ec..bbb.",
            "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"
    );
    private final List<String> level3 = Arrays.asList(
            "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb",
            "b...........e........e........b",
            "b.............................b",
            "b...............c.............b",
            "b........bb...................b",
            "b.be.............b..be..ceb...b",
            "b.bbbbb.............bbbbbbb..bb",
            "b.............................b",
            "b.bbb..........c..............b",
            "b.....bbb.....................b",
            "b..........b..c..............bb",
            "b....c................e.......b",
            "b....b.......b.......bb.......b",
            "b....bce.bbbbbb...c......be.e.b",
            "b....bbbb................bbbbbb",
            "b..........bbbb..........ee...b",
            "b.......b...c.eb....bb...bb...b",
            "b......bb...b.b..b...........b",
            "b.....bb....bcb.........b..ee.b",
            "b...bb......b.b.........bbbbbbb",
            "....b.....ee.........b..c.e...b",
            "bbbbbbbbbbbbbbbbbbbbbbbbbbbbbbb"
    );

    private final ArrayList<List<String>> levels = new ArrayList<List<String>>() {{
        add(level1);
        add(level2);
        add(level3);
    }};

    public ArrayList<IGameObject> getLevelGameObjects(int levelIndex) {
        var gameObjects = new ArrayList<IGameObject>();
        var level = levels.get(levelIndex);
        Collections.reverse(level);
        for(int y = 0; y < level.size(); y++) {
            for (int x = 0; x < level.get(y).length(); x++) {
                char character = level.get(y).charAt(x);
                var xPosition = x * 16;
                var yPosition = y * 16;
                var position = new Position(xPosition, yPosition);
                switch (character) {
                    case 'b':
                        var tile = new Tile(position);
                        gameObjects.add(tile);
                        break;
                    case 'e':
                        var enemy = new Enemy(position);
                        gameObjects.add(enemy);
                        break;
                    case 'c':
                        var coin = new Coin(position);
                        gameObjects.add(coin);
                        break;
                    case 't':
                        var trampoline = new Trampoline(position);
                        gameObjects.add(trampoline);
                        break;

                }
            }
        }
        return gameObjects;
    }

    public boolean levelExists(int levelIndex) {
        return this.levels.size() > levelIndex;
    }
}
