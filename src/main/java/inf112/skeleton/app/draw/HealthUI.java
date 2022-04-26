package inf112.skeleton.app.draw;

import inf112.skeleton.app.game.gameworld.IHealthObserver;
import inf112.skeleton.app.game.gameworld.IPlayersObserver;
import inf112.skeleton.app.objects.Player;
import inf112.skeleton.app.objects.attributes.Health;
import inf112.skeleton.app.services.HealthManager;
import inf112.skeleton.app.services.IHealthManager;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.LinkedList;

public class HealthUI implements IHealthObserver, IPlayersObserver {
    private GraphicsContext context;
    private IHealthManager healthManager;
    public Health currentHealth;
    private LinkedList<Player> players = new LinkedList<Player>();

    public HealthUI(GraphicsContext context) {
        this.context = context;
        this.healthManager = new HealthManager();
        this.currentHealth = healthManager.getHealth();
    }

    public void draw(double x, double y) {
        for(Player p : players){
            Health currenthealth = p.getHealth();
            Health maxHealth = healthManager.getMaxHealth();
            context.setFill(Color.RED);
            context.fillRect(x,y, 100,10);
            context.setFill(Color.GREEN);
            context.fillRect(x,y, ((double)currenthealth.getHealth() / (double)maxHealth.getHealth()) * (double)100, 10);
        }
    }

    @Override
    public void setHealth(int amount) {
        currentHealth.setHealth(amount);
    }

    @Override
    public void addPlayer(Player player) {
        players.add(player);
    }

    @Override
    public void removePlayer(Player player) {
        players.remove(player);
    }
}
