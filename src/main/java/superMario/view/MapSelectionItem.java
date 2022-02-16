package superMario.view;

import javafx.geometry.Dimension2D;
import javafx.geometry.Point2D;
//import javafx.scene.image.Image;

public class MapSelectionItem {

//    private Image image;
    private String name;
    private Point2D location;
    private Dimension2D dimension;

    public MapSelectionItem(String map, Point2D location){
        this.location = location;
        this.name = map;

//        ImageLoader loader = new ImageLoader();
//        this.image = loader.loadImage("/maps/" + map);

        this.dimension = new Dimension2D(0, 0);
    }


    public String getName() {
        return name;
    }

    public Point2D getLocation() {
        return location;
    }

    public Dimension2D getDimension() {
        return dimension;
    }

    public void setDimension(Dimension2D dimension) {
        this.dimension = dimension;
    }

    public void setLocation(Point2D location) {
        this.location = location;
    }
}
