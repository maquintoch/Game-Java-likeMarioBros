package inf112.skeleton.app.objects.attributes;

public enum ItemType {
    Player(0x00, false),
    Tile(0x01, true),
    Coin(0x02, false),
    Enemy(0x03, true),
    SpeedPowerup(0x04, true),
    JumpPowerup(0x05, true),
    ResistancePowerup(0x06, true),
    GravityPowerup(0x07, true),
    Trampoline(0x08, true),
    MultiplayerPlayer(0x09, false);

    private final boolean solid;
    private final int id;

    ItemType(int id, boolean solid) {
        this.id = id;
        this.solid = solid;
    }

    public boolean isSolid() {
        return this.solid;
    }

    public int getId() {
        return id;
    }
}
