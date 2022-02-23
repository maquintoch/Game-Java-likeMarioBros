package inf112.skeleton.app;

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
	
		context.fillText("You have: " + currentscore.amount + " coins!", 0, 25);
		
	}

}
