package org.abos.gs.gui.component.pane;

import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import org.abos.gs.gui.Gui;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class GatchaScreen extends StackPane {

    private final Gui gui;

    public GatchaScreen(@NotNull final Gui gui) {
        this.gui = Objects.requireNonNull(gui);
        final BorderPane borderPane = new BorderPane();
        final Button craftingBtn = new Button(this.gui.translate("gui.crafting"));
        craftingBtn.setOnMouseClicked(event -> this.gui.switchToCrafting());
        borderPane.setTop(new HBox(craftingBtn));
        getChildren().add(borderPane);
    }

    public void update() {
        // TODO needed?
    }
}
