package inf112.skeleton.app.Input;

import javafx.scene.input.KeyCode;

import java.util.HashSet;

public class InputHandler implements IInputHandler {

    private final HashSet<KeyCode> activeKeys;

    public InputHandler() {
        this.activeKeys = new HashSet<KeyCode>();
    }

    @Override
    public boolean isActive(KeyCode keyCode) {
        return activeKeys.contains(keyCode);
    }

    @Override
    public void setActive(KeyCode keyCode) {
        activeKeys.add(keyCode);
    }

    @Override
    public void setInactive(KeyCode keyCode) {
        activeKeys.remove(keyCode);
    }
}
