package org.abos.gs.gui.component.dialog;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import org.abos.gs.gui.Gui;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class PlayerNameDialog extends Dialog<String> {

    private final Gui gui;

    public PlayerNameDialog(@NotNull final Gui gui) {
        this.gui = Objects.requireNonNull(gui);
        setTitle(this.gui.translate("gui.player_name_title"));
        final TextField textField = new TextField(this.gui.translate("gui.player_name"));
        textField.setAlignment(Pos.CENTER);
        getDialogPane().getButtonTypes().add(ButtonType.OK);
        final Button confirmBtn = (Button) getDialogPane().lookupButton(ButtonType.OK);
        confirmBtn.setOnAction(event -> PlayerNameDialog.this.resultProperty().setValue(textField.getText()));
        final VBox vbox = new VBox(textField, confirmBtn);
        vbox.setSpacing(10d);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(5));
        getDialogPane().setContent(vbox);
        textField.requestFocus();
    }

}
