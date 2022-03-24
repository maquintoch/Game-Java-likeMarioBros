package inf112.skeleton.app.Services;

import inf112.skeleton.app.GameWorld.Entity.EntityAttributes.Health;

public class HealthManager implements IHealthManager {

    private Health health = new Health(100);

    @Override
    public Health GetHealth() {
        return health;
    }

    @Override
    public Health GetMaxHealth() {
        return new Health(100);
    }
}
