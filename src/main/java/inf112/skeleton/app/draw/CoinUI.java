package inf112.skeleton.app.draw;

import inf112.skeleton.app.objects.attributes.Score;
import inf112.skeleton.app.services.CoinManager;
import javafx.scene.canvas.GraphicsContext;

public class CoinUI{
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

}
