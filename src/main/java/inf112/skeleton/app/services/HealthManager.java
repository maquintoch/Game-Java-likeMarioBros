package inf112.skeleton.app.services;

import inf112.skeleton.app.objects.attributes.Health;

public class HealthManager {

    private Health health = new Health(3);

    public Health getHealth() {
        return health;
    }

    public Health getMaxHealth() {
        return new Health(3);
    }
}
