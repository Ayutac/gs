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

    private final Gui gui;

    public MainMenu(@NotNull final Gui gui) {
        this.gui = Objects.requireNonNull(gui);
        final BorderPane borderPane = new BorderPane();
        final Button newGame = new Button(this.gui.translate("gui.new_game"));
        newGame.setMinWidth(100);
        newGame.setOnMouseClicked(event -> this.gui.newGame());
        final Button exit = new Button(this.gui.translate("gui.exit"));
        exit.setMinWidth(100);
        exit.setOnMouseClicked(event -> Platform.exit());
        final VBox buttons = new VBox(newGame, exit);
        borderPane.setRight(buttons);
        getChildren().add(borderPane);
    }

}
