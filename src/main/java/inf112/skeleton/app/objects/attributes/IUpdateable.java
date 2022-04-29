package inf112.skeleton.app.objects.attributes;

import inf112.skeleton.app.objects.IGameObject;

import java.util.List;

public interface IUpdateable {
    public void update(List<IGameObject> gameObjects);
}
