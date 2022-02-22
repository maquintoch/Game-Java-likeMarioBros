package inf112.skeleton.app;

import javafx.scene.canvas.GraphicsContext;

public class CoinUIService implements IDrawable{
	
	 private GraphicsContext context;
	 private CoinManager coinManager;

	 public CoinUIService(GraphicsContext context) {
		 
	     this.context = context;
	     this.coinManager = new CoinManager();
	 }

	@Override
	public void Draw() {
		Score currentscore = coinManager.GetScore();
	
		context.fillText("You have: " + currentscore.amount + " coins!", 0, 25);
		
	}

}
