package inf112.skeleton.app.Graphics;

import inf112.skeleton.app.DrawBehavior.IDrawable;
import inf112.skeleton.app.GameWorld.Entity.EntityAttributes.Score;
import inf112.skeleton.app.Services.CoinManager;
import javafx.scene.canvas.GraphicsContext;

public class CoinUIService implements IDrawable{
	
	 private GraphicsContext context;
	 private CoinManager coinManager;
	 public Score currentscore;

	 public CoinUIService(GraphicsContext context) {
		 
	     this.context = context;
	     this.coinManager = new CoinManager();
	     this.currentscore = coinManager.GetScore();
	 }

	@Override
	public void Draw() {
	
		context.fillText("You have: " + currentscore.getScore() + " coins!", 0, 25);
		
	}

}
