package org.abos.gs.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.abos.gs.gui.component.pane.MainMenu;

import java.util.Locale;
import java.util.ResourceBundle;

public final class Gui extends Application {

    private final Scene mainMenuScene = new Scene(new MainMenu(this), 640, 480);

    private Stage stage;

    @Override
    public void start(Stage stage) {
        stage.setScene(mainMenuScene);
        stage.show();
    }

    public ResourceBundle getResourceBundle() {
        return ResourceBundle.getBundle("i18n.translations", Locale.US);
    }

    public void newGame() {

    }

}
