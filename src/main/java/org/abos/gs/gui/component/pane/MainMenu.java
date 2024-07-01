package org.abos.gs.gui.component.pane;

import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.abos.gs.gui.Gui;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class MainMenu extends StackPane {

    private static final int BUTTON_WIDTH = 100;

    private final Gui gui;

    public MainMenu(@NotNull final Gui gui) {
        this.gui = Objects.requireNonNull(gui);
        final BorderPane borderPane = new BorderPane();
        final Button newGame = new Button(this.gui.translate("gui.new_game"));
        newGame.setMinWidth(BUTTON_WIDTH);
        newGame.setOnMouseClicked(event -> this.gui.newGame());
        final Button loadGame = new Button(this.gui.translate("gui.load_game"));
        loadGame.setMinWidth(BUTTON_WIDTH);
        loadGame.setOnMouseClicked(event -> this.gui.loadGame());
        final Button exit = new Button(this.gui.translate("gui.exit"));
        exit.setMinWidth(BUTTON_WIDTH);
        exit.setOnMouseClicked(event -> Platform.exit());
        final VBox buttons = new VBox(newGame, loadGame, exit);
        borderPane.setRight(buttons);
        getChildren().add(borderPane);
    }

}
