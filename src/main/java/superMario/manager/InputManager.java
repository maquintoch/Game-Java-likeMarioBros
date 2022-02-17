package superMario.manager;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent; 


public class InputManager{

    private GameEngine engine;

    InputManager(GameEngine engine) {
        this.engine = engine; 
    }

    public void keyTyped(KeyEvent event) {
        int keyCode = event.getCode().getCode();
        GameStatus status = engine.getGameStatus();
        ButtonAction currentAction = ButtonAction.NO_ACTION;

        if (keyCode == KeyCode.UP.getCode()) {
            if(status == GameStatus.START_SCREEN || status == GameStatus.MAP_SELECTION)
                currentAction = ButtonAction.GO_UP;
            else
                currentAction = ButtonAction.JUMP;
        }
        else if(keyCode == KeyCode.DOWN.getCode()){
            if(status == GameStatus.START_SCREEN || status == GameStatus.MAP_SELECTION)
                currentAction = ButtonAction.GO_DOWN;
        }
        else if (keyCode == KeyCode.RIGHT.getCode()) {
            currentAction = ButtonAction.M_RIGHT;
        }
        else if (keyCode == KeyCode.LEFT.getCode()) {
            currentAction = ButtonAction.M_LEFT;
        }
        else if (keyCode == KeyCode.ENTER.getCode()) {
            currentAction = ButtonAction.SELECT;
        }
        else if (keyCode == KeyCode.ESCAPE.getCode()) {
            if(status == GameStatus.RUNNING || status == GameStatus.PAUSED )
                currentAction = ButtonAction.PAUSE_RESUME;
            else
                currentAction = ButtonAction.GO_TO_START_SCREEN;

        }
        else if (keyCode == KeyCode.SPACE.getCode()){
            currentAction = ButtonAction.FIRE;
        }

        notifyInput(currentAction);
    }

    public void mousePressed(MouseEvent e) {
        if(engine.getGameStatus() == GameStatus.MAP_SELECTION){
            engine.selectMapViaMouse();
        }
    }
   
    public void keyReleased(KeyEvent event) {
        if(event.getCode().getCode() == KeyCode.RIGHT.getCode() || event.getCode().getCode() == KeyCode.LEFT.getCode())
            notifyInput(ButtonAction.ACTION_COMPLETED);
    }

    private void notifyInput(ButtonAction action) {
        if(action != ButtonAction.NO_ACTION)
            engine.receiveInput(action);
    }
}


