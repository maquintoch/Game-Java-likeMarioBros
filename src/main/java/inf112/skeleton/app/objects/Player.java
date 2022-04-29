package inf112.skeleton.app.objects;

import inf112.skeleton.app.Input.IInputHandler;
import inf112.skeleton.app.game.gameworld.GameWorld;
import inf112.skeleton.app.objects.attributes.*;
import inf112.skeleton.app.services.AudioPlayer;
import javafx.scene.input.KeyCode;

import java.time.LocalTime;
import java.util.List;

public class Player extends EntityBase {
    private final AudioPlayer hurtAudioPlayer = new AudioPlayer("src/main/java/inf112/skeleton/app/assets/audio/hitHurt.wav");
    private final AudioPlayer jumpAudioPlayer = new AudioPlayer("src/main/java/inf112/skeleton/app/assets/audio/jump.wav");
    private final IInputHandler inputHandler;
    private boolean isSecondPlayer = false;

    private PlayerSprite sprite = new PlayerSprite();

    private LocalTime invinsibilityTime = LocalTime.MAX;
    private boolean isStanding;
    private float jumpSpeed = 7f;
    private float movementSpeed = 2f;

    public Player(Position position, IInputHandler inputHandler) {
        super(position);

        this.inputHandler = inputHandler;

        this.velocity = new Speed(0, 0);
        this.acceleration = new Speed(0, -0.2f);

        this.size = new GameObjectSize(14, 14);

        airDragAmountX = 0.1f;
        airDragAmountY = 0f;

        maxSpeedX = 10f;
        maxSpeedY = 10f;
    }

    public Player(Position position, IInputHandler inputHandler, boolean isSecondPlayer) {
        super(position);

        this.inputHandler = inputHandler;
        this.isSecondPlayer = isSecondPlayer;

        this.velocity = new Speed(0, 0);
        this.acceleration = new Speed(0, -0.2f);

        this.size = new GameObjectSize(14, 14);

        airDragAmountX = 0.1f;
        airDragAmountY = 0f;

        maxSpeedX = 10f;
        maxSpeedY = 10f;
    }


    public void checkKeyCode(){
        if(isSecondPlayer) {
            if(inputHandler.isActive(KeyCode.UP) && isStanding){
                jump();
            }
            if(inputHandler.isActive(KeyCode.LEFT)){
                moveLeft();
            }
            else if(inputHandler.isActive(KeyCode.RIGHT)){
                moveRight();
            }

        } else {
            if(inputHandler.isActive(KeyCode.W) && isStanding){
                jump();
            }
            if(inputHandler.isActive(KeyCode.A)){
                moveLeft();
            }
            else if(inputHandler.isActive(KeyCode.D)){
                moveRight();
            }
        }
    }

    public void moveRight() {
        this.velocity.velocityX = Math.max(movementSpeed, Math.abs(velocity.velocityX));
        this.sprite.moveRight();
    }

    public void moveLeft() {
        this.velocity.velocityX = -Math.max(movementSpeed, Math.abs(velocity.velocityX));
        this.sprite.moveLeft();

    }

    public void jump() {
        velocity.velocityY = jumpSpeed;
        isStanding = false;
        jumpAudioPlayer.play();
    }

    @Override
    public void update(List<IGameObject> gameObjects) {

    	if (position.getY() < -1) { gameWorldObservers.forEach(observer -> observer.setHealth(0)); }

        checkKeyCode(); //Move player based on keycode pressed.

        updateAnimation();

        //Do base collisions
        super.update(gameObjects);
    }

    private void updateAnimation() {
        if(Math.abs(this.velocity.velocityX) > 0 && !this.sprite.isRunning()) {
            this.sprite.start();
        }

        if(this.velocity.velocityX == 0){
            this.sprite.stop();
        }
    }

    public void draw(GameWorld gameWorld) {
        var imageHandler = gameWorld.getDrawImageBehavior();
        imageHandler.draw(position, size, sprite.getImage());
    }

    @Override
    public ItemType getItemType() {
        return ItemType.Player;
    }

    @Override
    public void collide(IGameObject gameObject) {
        var itemType = gameObject.getItemType();
        switch(itemType) {
            case Tile:
                if(this.isAbove(gameObject)) {
                    isStanding = true;
                }
                break;
            case Enemy:
                if(this.isAbove(gameObject)) {
                    this.velocity.velocityY += jumpSpeed/2f;
                } else if (LocalTime.now().isAfter(invinsibilityTime.plusNanos(500000000))) {
                    hurtAudioPlayer.play();
                    gameWorldObservers.forEach(observer -> observer.addHealth(-1));
                    invinsibilityTime = LocalTime.now();
                }
                break;
            case Trampoline:
                this.velocity.velocityY += jumpSpeed * 1.2;
                break;
        }
    }

    public boolean isStanding() {
        return isStanding;
    }
}
