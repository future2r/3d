package name.ulbricht.threed.ui;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.stream.IntStream;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.AmbientLight;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.SubScene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.image.PixelFormat;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.Sphere;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;

public final class MainWindow {

    public static MainWindow create(final Stage stage) throws IOException {

        final var fxml = MainWindow.class.getResource(MainWindow.class.getSimpleName() + ".fxml");
        final var loader = new FXMLLoader(fxml);
        loader.load();

        final var root = loader.<Parent>getRoot();
        final var controller = loader.<MainWindow>getController();

        final var scene = new Scene(root);

        stage.setTitle("Space: the final frontier...");
        stage.getIcons().addAll( //
                IntStream.of(32, 48, 64, 96, 128) //
                        .mapToObj(size -> "app-" + size + ".png") //
                        .map(MainWindow.class::getResource) //
                        .map(URL::toString) //
                        .map(Image::new) //
                        .toList());

        stage.setScene(scene);

        return controller;
    }

    @FXML
    private Parent rootPane;

    @FXML
    private AmbientLight backgroundLight;

    @FXML
    private SubScene spaceScene;
    private PerspectiveCamera camera;
    @FXML
    private Group space;

    @FXML
    private Sphere earth;
    @FXML
    private Rotate earthRotate;
    @FXML
    private Timeline earthTimeline;

    @FXML
    private Sphere moon;
    @FXML
    private Rotate moonRotate;
    @FXML
    private Timeline moonTimeline;

    @FXML
    private AxesEditor rotationEditor;
    @FXML
    private AxesEditor translationEditor;

    @FXML
    private Slider backgroundBrightnessSlider;

    @FXML
    private CheckBox animateEarthCheckBox;

    @FXML
    private CheckBox animateMoonCheckBox;

    @FXML
    private Label statusBarLabel;

    @FXML
    private Image focusImage;

    @FXML
    private void initialize() {
        /*
         * We cannot set the camera in FXML because the 'fixedEyeAtCameraZero'
         * constructor argument is not annotated and has no setter.
         */
        this.camera = new PerspectiveCamera(true);
        camera.setFarClip(2000);
        this.spaceScene.setCamera(this.camera);

        setupRotationControls();
        setupTranslationControls();

        setupLighting();

        setupEarthAnimation();
        setupMoonAnimation();

        this.focusImage = createSolidImage(1, 1, Color.color(0.2, 0.2, 0.2));

        this.earth.focusedProperty().addListener((observable, oldValue, newValue) -> toggleFocus(this.earth, newValue));
        this.moon.focusedProperty().addListener((observable, oldValue, newValue) -> toggleFocus(this.moon, newValue));
    }

    private void setupRotationControls() {
        final var xRotate = new Rotate(0, Rotate.X_AXIS);
        xRotate.angleProperty().bind(this.rotationEditor.xValueProperty());

        final var yRotate = new Rotate(0, Rotate.Y_AXIS);
        yRotate.angleProperty().bind(this.rotationEditor.yValueProperty());

        final var zRotate = new Rotate(0, Rotate.Z_AXIS);
        zRotate.angleProperty().bind(this.rotationEditor.zValueProperty());

        this.camera.getTransforms().addAll(xRotate, yRotate, zRotate);
    }

    private void setupTranslationControls() {
        this.camera.translateXProperty().bind(this.translationEditor.xValueProperty());
        this.camera.translateYProperty().bind(this.translationEditor.yValueProperty());
        this.camera.translateZProperty().bind(this.translationEditor.zValueProperty());
    }

    private void setupLighting() {
        this.backgroundLight.colorProperty().bind(
                Bindings.createObjectBinding(() -> {
                    final var brightness = backgroundBrightnessSlider.getValue();
                    return Color.color(brightness, brightness, brightness);
                }, backgroundBrightnessSlider.valueProperty()));
    }

    private void setupEarthAnimation() {
        this.earthTimeline.getKeyFrames()
                .add(new KeyFrame(Duration.seconds(5), new KeyValue(this.earthRotate.angleProperty(), -360)));

        toggleEarthAnimation();
    }

    @FXML
    private void toggleEarthAnimation() {
        if (this.animateEarthCheckBox.isSelected())
            this.earthTimeline.play();
        else
            this.earthTimeline.pause();
    }

    private void setupMoonAnimation() {
        this.moonTimeline.getKeyFrames()
                .add(new KeyFrame(Duration.seconds(20), new KeyValue(this.moonRotate.angleProperty(), -360)));

        toggleMoonAnimation();
    }

    @FXML
    private void toggleMoonAnimation() {
        if (this.animateMoonCheckBox.isSelected())
            this.moonTimeline.play();
        else
            this.moonTimeline.pause();
    }

    private static Image createSolidImage(final int width, final int height, final Color color) {
        final var buffer = new int[width * height];
        Arrays.fill(buffer, toArgb(color));
        final var image = new WritableImage(width, height);
        final var pixelWriter = image.getPixelWriter();
        pixelWriter.setPixels(0, 0, width, height, PixelFormat.getIntArgbInstance(), buffer, 0, width);
        return image;
    }

    private static int toArgb(final Color color) {
        final var r = (int) (255 * color.getRed());
        final var g = (int) (255 * color.getGreen());
        final var b = (int) (255 * color.getBlue());
        final var a = (int) (255 * color.getOpacity());
        return (a << 24) | (r << 16) | (g << 8) | b;
    }

    @FXML
    private void spaceSceneKeyPressed(final KeyEvent event) {
        if (event.getCode() == KeyCode.ESCAPE) {
            this.rootPane.requestFocus();
            event.consume();
        }
    }

    @FXML
    private void shapeEntered(final MouseEvent event) {
        if (event.getSource() instanceof Shape3D shape) {
            this.statusBarLabel.setText(shape.getId());
        }
    }

    @FXML
    private void shapeExited() {
        this.statusBarLabel.setText(null);
    }

    @FXML
    private void shapeClicked(final MouseEvent event) {
        if (event.getSource() instanceof Shape3D shape) {
            shape.requestFocus();
        }
    }

    private void toggleFocus(final Shape3D shape, final boolean focused) {
        if (shape.getMaterial() instanceof PhongMaterial material) {
            material.setSelfIlluminationMap(focused ? this.focusImage : null);
        }
    }
}
