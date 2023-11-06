package name.ulbricht.threed.ui;

import java.io.IOException;

import javafx.beans.property.DoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;

public final class AxesEditor extends GridPane {

    public AxesEditor() {
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
    private Slider xSlider;
    @FXML
    private Slider ySlider;
    @FXML
    private Slider zSlider;

    public DoubleProperty xValueProperty() {
        return this.xSlider.valueProperty();
    }

    public double getXValue() {
        return this.xSlider.getValue();
    }

    public void setXValue(final double value) {
        this.xSlider.setValue(value);
    }

    public DoubleProperty xMinProperty() {
        return this.xSlider.minProperty();
    }

    public double getXMin() {
        return this.xSlider.getMin();
    }

    public void setXMin(final double value) {
        this.xSlider.setMin(value);
    }

    public DoubleProperty xMaxProperty() {
        return this.xSlider.maxProperty();
    }

    public double getXMax() {
        return this.xSlider.getMax();
    }

    public void setXMax(final double value) {
        this.xSlider.setMax(value);
    }

    public DoubleProperty yValueProperty() {
        return this.ySlider.valueProperty();
    }

    public double getYValue() {
        return this.ySlider.getValue();
    }

    public void setYValue(final double value) {
        this.ySlider.setValue(value);
    }

    public DoubleProperty yMinProperty() {
        return this.ySlider.minProperty();
    }

    public double getYMin() {
        return this.ySlider.getMin();
    }

    public void setYMin(final double value) {
        this.ySlider.setMin(value);
    }

    public DoubleProperty yMaxProperty() {
        return this.ySlider.maxProperty();
    }

    public double getYMax() {
        return this.ySlider.getMax();
    }

    public void setYMax(final double value) {
        this.ySlider.setMax(value);
    }

    public DoubleProperty zValueProperty() {
        return this.zSlider.valueProperty();
    }

    public double getZValue() {
        return this.zSlider.getValue();
    }

    public void setZValue(final double value) {
        this.zSlider.setValue(value);
    }

    public DoubleProperty zMinProperty() {
        return this.zSlider.minProperty();
    }

    public double getZMin() {
        return this.zSlider.getMin();
    }

    public void setZMin(final double value) {
        this.zSlider.setMin(value);
    }

    public DoubleProperty zMaxProperty() {
        return this.zSlider.maxProperty();
    }

    public double getZMax() {
        return this.zSlider.getMax();
    }

    public void setZMax(final double value) {
        this.zSlider.setMax(value);
    }
}
