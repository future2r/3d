package name.ulbricht.threed.ui;

import javafx.beans.NamedArg;
import javafx.scene.Parent;
import javafx.scene.SceneAntialiasing;
import javafx.scene.SubScene;

public final class ResizableSubScene extends SubScene {

    public ResizableSubScene(@NamedArg("root") Parent root, @NamedArg("width") double width,
            @NamedArg("height") double height) {
        this(root, width, height, false, SceneAntialiasing.DISABLED);
    }

    public ResizableSubScene(@NamedArg("root") Parent root, @NamedArg("width") double width,
            @NamedArg("height") double height,
            @NamedArg("depthBuffer") boolean depthBuffer, @NamedArg("antiAliasing") SceneAntialiasing antiAliasing) {
        super(root, width, height, depthBuffer, antiAliasing);
    }

    @Override
    public double minHeight(double width) {
        return 1;
    }

    @Override
    public double maxHeight(double width) {
        return Double.MAX_VALUE;
    }

    @Override
    public double prefHeight(double width) {
        return minHeight(width);
    }

    @Override
    public double minWidth(double height) {
        return 1;
    }

    @Override
    public double maxWidth(double height) {
        return Double.MAX_VALUE;
    }

    @Override
    public boolean isResizable() {
        return true;
    }

    @Override
    public void resize(final double width, final double height) {
        setWidth(width);
        setHeight(height);
    }
}
