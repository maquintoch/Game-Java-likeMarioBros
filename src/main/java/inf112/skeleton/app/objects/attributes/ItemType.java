package inf112.skeleton.app.objects.attributes;

public enum ItemType {
    Player(true),
    Tile(true),
    Coin(false),
    Enemy(true),
    Trampoline(false);

    private final boolean solid;

    ItemType(boolean solid) {
        this.solid = solid;
    }

    public boolean isSolid() {
        return this.solid;
    }
}
