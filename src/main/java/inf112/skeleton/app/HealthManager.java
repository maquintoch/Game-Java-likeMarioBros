package inf112.skeleton.app;

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
