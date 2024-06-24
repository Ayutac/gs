package org.abos.gs.gui.fx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class FxMenu extends Application {
    @Override
    public void start(Stage stage) {
        final StackPane sp = new StackPane();
        final Scene scene = new Scene(sp, 640, 480);
        stage.setScene(scene);
        stage.show();
    }
}
