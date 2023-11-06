module name.ulbricht.threed {

    // JDK dependencies
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.controls;
    requires transitive javafx.graphics;
    
    exports name.ulbricht.threed;
    
    opens name.ulbricht.threed.ui;
}
