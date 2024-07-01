package org.abos.gs.gui.component.pane;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.abos.gs.core.ItemLike;
import org.abos.gs.core.Player;
import org.abos.gs.core.RecipeLike;
import org.abos.gs.core.StuffStack;
import org.abos.gs.gui.Gui;
import org.abos.gs.gui.component.LabelledList;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public final class CraftingScreen extends StackPane {

    private final Gui gui;

    private final LabelledList inventory;
    private final LabelledList craftingInv;
    private final ComboBox<String> resultBox;

    private Map<String, ItemLike> invContentMap;
    private Map<String, ItemLike> craftInvContentMap;
    private Map<String, RecipeLike> recipeContentMap;

    public CraftingScreen(@NotNull final Gui gui) {
        this.gui = Objects.requireNonNull(gui);
        final BorderPane borderPane = new BorderPane();
        final Button gatchaBtn = new Button(this.gui.translate("gui.gatcha"));
        gatchaBtn.setOnMouseClicked(event -> this.gui.switchToGatcha());
        final Button saveBtn = new Button(this.gui.translate("gui.save_game"));
        saveBtn.setOnMouseClicked(event -> this.gui.saveGame());
        final Button loadBtn = new Button(this.gui.translate("gui.load_game"));
        loadBtn.setOnMouseClicked(event -> this.gui.loadGame());
        borderPane.setTop(new HBox(gatchaBtn, saveBtn, loadBtn));
        inventory = new LabelledList(this.gui.translate("gui.inventory"));
        craftingInv = new LabelledList(this.gui.translate("gui.craft_inv"));
        final Button toCraft = new Button(">>");
        toCraft.setOnMouseClicked(event -> moveSelectedRight(event.getButton()));
        final Button toInv = new Button("<<");
        toInv.setOnMouseClicked(event -> moveSelectedLeft(event.getButton()));
        final VBox arrows = new VBox(toCraft, toInv);
        arrows.setAlignment(Pos.CENTER);
        final HBox main = new HBox(inventory, arrows, craftingInv);
        main.setAlignment(Pos.CENTER);
        borderPane.setCenter(main);
        final Label resultLab = new Label(this.gui.translate("gui.craft_result"));
        resultBox = new ComboBox<>();
        resultBox.setPrefWidth(300);
        final Button resultBtn = new Button(this.gui.translate("gui.craft_confirm"));
        resultBtn.setOnMouseClicked(event -> craft());
        HBox craftingLine = new HBox(resultLab, resultBox, resultBtn);
        craftingLine.setSpacing(10d);
        craftingLine.setAlignment(Pos.CENTER);
        craftingLine.setPadding(new Insets(5));
        borderPane.setBottom(craftingLine);
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
            recipeContentMap.clear();
            resultBox.setItems(emptyList);
        }
        else {
            // we need these maps because no one else remembers translation -> original
            invContentMap = player.getInventory().viewContent().stream()
                    .filter(stack -> stack.stuff() instanceof ItemLike)
                    .collect(Collectors.toMap(
                            stack -> stack.getTranslation(gui.getResourceBundle()),
                            stack -> (ItemLike)stack.stuff()
                    ));
            final List<String> invContent = new ArrayList<>(invContentMap.keySet());
            Collections.sort(invContent);
            int oldSelection = inventory.getListView().getSelectionModel().getSelectedIndex();
            inventory.getListView().setItems(FXCollections.observableList(invContent));
            if (oldSelection >= 0) {
                if (invContent.size() > oldSelection) {
                    inventory.getListView().getSelectionModel().select(oldSelection);
                } else {
                    inventory.getListView().getSelectionModel().select(invContent.size() - 1);
                }
            }
            // same for the crafting inventory
            craftInvContentMap = player.getCraftingInv().viewContent().stream()
                    .filter(stack -> stack.stuff() instanceof ItemLike)
                    .collect(Collectors.toMap(
                            stack -> stack.getTranslation(gui.getResourceBundle()),
                            stack -> (ItemLike)stack.stuff()
                    ));
            final List<String> craftInvContent = new ArrayList<>(craftInvContentMap.keySet());
            Collections.sort(craftInvContent);
            oldSelection = craftingInv.getListView().getSelectionModel().getSelectedIndex();
            craftingInv.getListView().setItems(FXCollections.observableList(craftInvContent));
            if (oldSelection >= 0) {
                if (craftInvContent.size() > oldSelection) {
                    craftingInv.getListView().getSelectionModel().select(oldSelection);
                } else {
                    craftingInv.getListView().getSelectionModel().select(craftInvContent.size() - 1);
                }
            }
            // similar for the result box
            recipeContentMap = player.matchingRecipes().stream()
                    .collect(Collectors.toMap(
                            recipe -> recipe.getOutput().iterator().next().getTranslation(gui.getResourceBundle()),
                            recipe -> recipe
                    ));
            final List<String> resultContent = new ArrayList<>(recipeContentMap.keySet());
            Collections.sort(resultContent);
            resultBox.setItems(FXCollections.observableList(resultContent));
        }
    }

    public void moveSelectedRight(final MouseButton btn) {
        final String selection = inventory.getListView().getSelectionModel().getSelectedItem();
        if (selection == null) {
            return;
        }
        final ItemLike selectedItem = invContentMap.get(selection);
        int count;
        if (btn == MouseButton.PRIMARY) {
            count = 1;
        }
        else if (btn == MouseButton.SECONDARY) {
            count = gui.getPlayer().getInventory().count(selectedItem);
        }
        else if (btn == MouseButton.MIDDLE) {
            count = Math.min(10, gui.getPlayer().getInventory().count(selectedItem));
        }
        else {
            return;
        }
        final StuffStack selectedStuffStack = new StuffStack(selectedItem, count);
        if (gui.getPlayer().getInventory().subtract(selectedStuffStack)) {
            gui.getPlayer().getCraftingInv().add(selectedStuffStack);
            update();
        }
    }

    public void moveSelectedLeft(final MouseButton btn) {
        final String selection = craftingInv.getListView().getSelectionModel().getSelectedItem();
        if (selection == null) {
            return;
        }
        final ItemLike selectedItem = craftInvContentMap.get(selection);
        int count;
        if (btn == MouseButton.PRIMARY) {
            count = 1;
        }
        else if (btn == MouseButton.SECONDARY) {
            count = gui.getPlayer().getCraftingInv().count(selectedItem);
        }
        else if (btn == MouseButton.MIDDLE) {
            count = Math.min(10, gui.getPlayer().getCraftingInv().count(selectedItem));
        }
        else {
            return;
        }
        final StuffStack selectedStuffStack = new StuffStack(selectedItem, count);
        if (gui.getPlayer().getCraftingInv().subtract(selectedStuffStack)) {
            gui.getPlayer().getInventory().add(selectedStuffStack);
            update();
        }
    }

    public void craft() {
        final String selection = resultBox.getSelectionModel().getSelectedItem();
        final Player player = gui.getPlayer();
        if (player == null || selection == null) {
            return;
        }
        final RecipeLike recipe = recipeContentMap.get(selection);
        if (player.getCraftingInv().subtractRecipe(recipe)) {
            player.getInventory().addAll(recipe.getOutput());
            update();
        }
    }

}
