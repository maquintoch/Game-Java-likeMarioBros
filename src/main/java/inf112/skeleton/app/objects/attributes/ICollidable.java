package inf112.skeleton.app.objects.attributes;

import inf112.skeleton.app.objects.IItem;

public interface ICollidable extends IItem {
    ItemType getItemType();
    void collide(ItemType itemType);

}
