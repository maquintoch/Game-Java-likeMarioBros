package inf112.skeleton.app.Services;

import inf112.skeleton.app.GameWorld.Entity.EntityAttributes.Health;

public interface IHealthManager {
    Health GetHealth();

    Health GetMaxHealth();
}
