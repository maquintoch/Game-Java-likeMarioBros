package inf112.skeleton.app.draw;

import inf112.skeleton.app.game.gameworld.ScoreObserver;
import inf112.skeleton.app.objects.attributes.Score;
import inf112.skeleton.app.services.CoinManager;
import javafx.scene.canvas.GraphicsContext;

public class CoinUI implements ScoreObserver {
	 private GraphicsContext context;
	 private CoinManager coinManager;
	 public Score currentscore;

	 public CoinUI(GraphicsContext context) {
		 
	     this.context = context;
	     this.coinManager = new CoinManager();
	     this.currentscore = coinManager.getScore();
	 }

	public void draw() {
		context.fillText("You have: " + currentscore.getScore() + " coins!", 0, 25);
	}

	@Override
	public void setScore(int amount) {
		this.currentscore.addScore(amount);
	}
}
