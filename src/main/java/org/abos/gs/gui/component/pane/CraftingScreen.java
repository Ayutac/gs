package org.abos.gs.gui.component.pane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.abos.gs.core.Player;
import org.abos.gs.gui.Gui;
import org.abos.gs.gui.component.LabelledList;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public final class CraftingScreen extends StackPane {

    private final Gui gui;

    private final LabelledList inventory;
    private final LabelledList craftingInv;

    public CraftingScreen(@NotNull final Gui gui) {
        this.gui = Objects.requireNonNull(gui);
        inventory = new LabelledList(this.gui.translate("gui.inventory"));
        craftingInv = new LabelledList(this.gui.translate("gui.craft_inv"));
        final BorderPane borderPane = new BorderPane();
        final Button toCraft = new Button(">>");
        final Button toInv = new Button("<<");
        final VBox arrows = new VBox(toCraft, toInv);
        arrows.setAlignment(Pos.CENTER);
        final HBox main = new HBox(inventory, arrows, craftingInv);
        main.setAlignment(Pos.CENTER);
        borderPane.setCenter(main);
        getChildren().add(borderPane);
    }

    public void update() {
        final Player player = gui.getPlayer();
        if (player == null) {
            final ObservableList<String> emptyList = FXCollections.emptyObservableList();
            inventory.getListView().setItems(emptyList);
            craftingInv.getListView().setItems(emptyList);
        }
        else {
            final List<String> invContent = new ArrayList<>(player.getInventory().viewContent().stream()
                    .map(stack -> stack.getTranslation(gui.getResourceBundle()))
                    .toList());
            Collections.sort(invContent);
            inventory.getListView().setItems(FXCollections.observableList(invContent));
            final List<String> craftInvContent = new ArrayList<>(player.getCraftingInv().viewContent().stream()
                    .map(stack -> stack.getTranslation(gui.getResourceBundle()))
                    .toList());
            Collections.sort(craftInvContent);
            inventory.getListView().setItems(FXCollections.observableList(craftInvContent));
        }
    }

}
