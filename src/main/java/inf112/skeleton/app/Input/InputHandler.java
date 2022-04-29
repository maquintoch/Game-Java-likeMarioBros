package inf112.skeleton.app.Input;

import javafx.scene.input.KeyCode;

import java.util.HashSet;

public class InputHandler {

    private final HashSet<KeyCode> activeKeys;

    public InputHandler() {
        this.activeKeys = new HashSet<KeyCode>();
    }

    public boolean isActive(KeyCode keyCode) {
        return activeKeys.contains(keyCode);
    }

    public void setActive(KeyCode keyCode) {
        activeKeys.add(keyCode);
    }

    public void setInactive(KeyCode keyCode) {
        activeKeys.remove(keyCode);
    }
}
