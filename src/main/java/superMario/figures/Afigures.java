package superMario.figures;

import javafx.scene.image.Image;
import javafx.geometry.Dimension2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;

public abstract class Afigures {

    private double x, y;
    private double velX, velY;
    private Dimension2D dimension;
    private Image style;

    private double gravityAcc;

    private boolean falling, jumping;

    public Afigures(double x, double y, Image style){
        setLocation(x, y);
        setStyle(style);

        if(style != null){
            setDimension(style.getWidth(), style.getHeight());
        }

        setVelX(0);
        setVelY(0);
        setGravityAcc(0.38);
        jumping = false;
        falling = true;
    }

    public void draw(GraphicsContext g) {
        Image style = getStyle();

        if(style != null){
            g.drawImage(style, x, y);
        }
    }

    public void updateLocation() {
        if(jumping && velY <= 0){
            jumping = false;
            falling = true;
        }
        else if(jumping){
            velY = velY - gravityAcc;
            y = y - velY;
        }

        if(falling){
            y = y + velY;
            velY = velY + gravityAcc;
        }

        x = x + velX;
    }

    public void setLocation(double x, double y) {
        setX(x);
        setY(y);
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public Dimension2D getDimension(){
        return dimension;
    }

    public void setDimension(Dimension2D dimension) {
        this.dimension = dimension;
    }

    public void setDimension(double width, double height){ 
    	this.dimension =  new Dimension2D(width, height); 
    }

    public Image getStyle() {
        return style;
    }

    public void setStyle(Image style) {
        this.style = style;
    }

    public double getVelX() {
        return velX;
    }

    public void setVelX(double velX) {
        this.velX = velX;
    }

    public double getVelY() {
        return velY;
    }

    public void setVelY(double velY) {
        this.velY = velY;
    }

    public double getGravityAcc() {
        return gravityAcc;
    }

    public void setGravityAcc(double gravityAcc) {
        this.gravityAcc = gravityAcc;
    }

    public Rectangle2D getTopBounds(){
        return new Rectangle2D(x+dimension.getWidth()/6, y, 2*dimension.getWidth()/3, dimension.getHeight()/2);
    }

    public Rectangle2D getBottomBounds(){
        return new Rectangle2D(x+dimension.getWidth()/6, y + dimension.getHeight()/2, 2*dimension.getWidth()/3, dimension.getHeight()/2);
    }

    public Rectangle2D getLeftBounds(){
        return new Rectangle2D(x, y + dimension.getHeight()/4, dimension.getWidth()/4, dimension.getHeight()/2);
    }

    public Rectangle2D getRightBounds(){
        return new Rectangle2D(x + 3*dimension.getWidth()/4, y + dimension.getHeight()/4, dimension.getWidth()/4, dimension.getHeight()/2);
    }

    public Rectangle2D getBounds(){
        return new Rectangle2D(x, y, dimension.getWidth(), dimension.getHeight());
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }

    public boolean isJumping() {
        return jumping;
    }

    public void setJumping(boolean jumping) {
        this.jumping = jumping;
    }
}

