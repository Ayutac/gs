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
import org.abos.gs.core.Stuff;
import org.abos.gs.core.StuffStack;
import org.abos.gs.gui.Gui;
import org.abos.gs.gui.component.LabelledList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public final class CraftingScreen extends StackPane {

    private static final Logger LOGGER = LogManager.getLogger(CraftingScreen.class);

    private final Gui gui;

    private final LabelledList inventory;
    private final LabelledList craftingInv;

    private Map<String, Stuff> invContentMap;
    private Map<String, Stuff> craftInvContentMap;

    public CraftingScreen(@NotNull final Gui gui) {
        this.gui = Objects.requireNonNull(gui);
        final BorderPane borderPane = new BorderPane();
        final Button gatchaBtn = new Button(this.gui.translate("gui.gatcha"));
        gatchaBtn.setOnMouseClicked(event -> this.gui.switchToGatcha());
        borderPane.setTop(new HBox(gatchaBtn));
        inventory = new LabelledList(this.gui.translate("gui.inventory"));
        craftingInv = new LabelledList(this.gui.translate("gui.craft_inv"));
        final Button toCraft = new Button(">>");
        toCraft.setOnMouseClicked(event -> moveSelectedRight());
        final Button toInv = new Button("<<");
        toInv.setOnMouseClicked(event -> moveSelectedLeft());
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
            invContentMap.clear();
            inventory.getListView().setItems(emptyList);
            craftInvContentMap.clear();
            craftingInv.getListView().setItems(emptyList);
        }
        else {
            // we need these maps because no one else remembers translation -> original
            invContentMap = player.getInventory().viewContent().stream()
                    .collect(Collectors.toMap(
                            stack -> stack.getTranslation(gui.getResourceBundle()),
                            StuffStack::stuff));
            final List<String> invContent = new LinkedList<>(invContentMap.keySet());
            Collections.sort(invContent);
            inventory.getListView().setItems(FXCollections.observableList(invContent));
            craftInvContentMap = player.getCraftingInv().viewContent().stream()
                    .collect(Collectors.toMap(
                            stack -> stack.getTranslation(gui.getResourceBundle()),
                            StuffStack::stuff));
            final List<String> craftInvContent = new LinkedList<>(craftInvContentMap.keySet());
            Collections.sort(craftInvContent);
            craftingInv.getListView().setItems(FXCollections.observableList(craftInvContent));
        }
    }

    public void moveSelectedRight() {
        final String selection = inventory.getListView().getSelectionModel().getSelectedItem();
        if (selection == null) {
            return;
        }
        final StuffStack selectedStuff = new StuffStack(invContentMap.get(selection));
        if (gui.getPlayer().getInventory().subtract(selectedStuff)) {
            gui.getPlayer().getCraftingInv().add(selectedStuff);
            update();
        }
    }

    public void moveSelectedLeft() {
        final String selection = craftingInv.getListView().getSelectionModel().getSelectedItem();
        if (selection == null) {
            return;
        }
        final StuffStack selectedStuff = new StuffStack(craftInvContentMap.get(selection));
        if (gui.getPlayer().getCraftingInv().subtract(selectedStuff)) {
            gui.getPlayer().getInventory().add(selectedStuff);
            update();
        }
    }

}
