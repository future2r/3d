package name.ulbricht.threed.ui;

import java.io.IOException;

import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.shape.Cylinder;

public class Axes extends Group {

    public Axes() {
        final var fxml = getClass().getResource(getClass().getSimpleName() + ".fxml");
        final var loader = new FXMLLoader(fxml);
        loader.setRoot(this);
        loader.setController(this);

        try {
            loader.load();
        } catch (IOException exception) {
            throw new InternalError(exception);
        }
    }

    @FXML
    private Cylinder xAxis;
    @FXML
    private Cylinder yAxis;
    @FXML
    private Cylinder zAxis;

    public DoubleProperty xLengthProperty() {
        return this.xAxis.heightProperty();
    }

    public double getXLength() {
        return this.xAxis.getHeight();
    }

    public void setXLength(final double value) {
        this.xAxis.setHeight(value);
    }

    public DoubleProperty xRadiusProperty() {
        return this.xAxis.radiusProperty();
    }

    public double getXRadius() {
        return this.xAxis.getRadius();
    }

    public void setXRadius(final double value) {
        this.xAxis.setRadius(value);
    }

    public DoubleProperty yLengthProperty() {
        return this.yAxis.heightProperty();
    }

    public double getYLength() {
        return this.yAxis.getHeight();
    }

    public void setYLength(final double value) {
        this.yAxis.setHeight(value);
    }

    public DoubleProperty yRadiusProperty() {
        return this.yAxis.radiusProperty();
    }

    public double getYRadius() {
        return this.yAxis.getRadius();
    }

    public void setYRadius(final double value) {
        this.yAxis.setRadius(value);
    }

    public DoubleProperty zLengthProperty() {
        return this.zAxis.heightProperty();
    }

    public double getZLength() {
        return this.zAxis.getHeight();
    }

    public void setZLength(final double value) {
        this.zAxis.setHeight(value);
    }

    public DoubleProperty zRadiusProperty() {
        return this.zAxis.radiusProperty();
    }

    public double getZRadius() {
        return this.zAxis.getRadius();
    }

    public void setZRadius(final double value) {
        this.zAxis.setRadius(value);
    }
}
