package inf112.skeleton.app;

import inf112.skeleton.app.Entity.EntityAttributes.Health;

public interface IHealthManager {
    Health GetHealth();

    Health GetMaxHealth();
}
