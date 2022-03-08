package inf112.skeleton.app.Camera;

import inf112.skeleton.app.IUpdateable;

public class Camera implements ICamera, IUpdateable{
	private float x;
	private float y;
	private float t;
	

	public Camera(float x, float y){
		this.x = x;
		this.y = y;
		this.t = 0;
		
	}
	
	public float getx() {
		return x;
		
	}
	
	public float gety() {
		return y;
	
	}

	@Override
	public void Update() {
		x = (float) (10 * Math.cos(t));
		y = (float) (10 * Math.sin(t));
		t+= 0.1;
	}
}


