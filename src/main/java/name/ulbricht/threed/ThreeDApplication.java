package name.ulbricht.threed;

import name.ulbricht.threed.ui.MainWindow;

import javafx.application.Application;
import javafx.stage.Stage;

public final class ThreeDApplication extends Application {

    public ThreeDApplication() {
        // define default constructor, see JDK linter warning 'missing-explicit-ctor'
    }

    @Override
    public void init() throws Exception {
        super.init();

        // nothing to do yet
    }

    @Override
    public void start(final Stage primaryStage) throws Exception {

        MainWindow.create(primaryStage);

        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        // nothing to do yet

        super.stop();
    }
}
