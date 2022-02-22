package inf112.skeleton.app;

public interface IMoveHandler {

    /**
     * Takes in a position you want to move to. This is a (x, y) coordinate.
     *
     * Example: player.MoveTo(1,1) moves player to position (1,1)
     *
     * @param position
     */
    void MoveTo(Position position);

    /**
     * Move takes a speed you want to move the given object.
     * Moves in a given direction not to a specific grid position.
     *
     * Example: If you fall you want to fall in a given pace and fall downwards.
     *
     * if(player.fall==true){
     *     player.move(0,0.25)// Makes the player fall with a speed of 0.25 in the y-axis.
     * }
     *
     * @param speed
     */
    void Move(Speed speed);
}
