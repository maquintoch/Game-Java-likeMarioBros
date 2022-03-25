package inf112.skeleton.app.services;

import inf112.skeleton.app.objects.attributes.Health;

public class HealthManager implements IHealthManager {

    private Health health = new Health(100);

    @Override
    public Health getHealth() {
        return health;
    }

    @Override
    public Health getMaxHealth() {
        return new Health(100);
    }
}
