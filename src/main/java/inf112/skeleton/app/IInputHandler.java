package inf112.skeleton.app;

import javafx.scene.input.KeyCode;

public interface IInputHandler {
    boolean isActive(KeyCode keyCode);
    void setActive(KeyCode keyCode);
    void setInactive(KeyCode keyCode);
}
