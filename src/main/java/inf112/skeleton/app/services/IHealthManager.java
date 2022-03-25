package inf112.skeleton.app.services;

import inf112.skeleton.app.objects.attributes.Health;

public interface IHealthManager {
    Health getHealth();
    Health getMaxHealth();
}
