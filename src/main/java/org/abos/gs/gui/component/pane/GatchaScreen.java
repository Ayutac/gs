package org.abos.gs.gui.component.pane;

import javafx.collections.FXCollections;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import org.abos.gs.core.Gatcha;
import org.abos.gs.core.GatchaLike;
import org.abos.gs.core.Player;
import org.abos.gs.core.StuffStack;
import org.abos.gs.gui.Gui;
import org.abos.gs.gui.component.LabelledList;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;

public final class GatchaScreen extends StackPane {

    private final Gui gui;

    private final ComboBox<String> comboBox;
    private final LabelledList offerings;
    private final LabelledList draw;

    private final Random random = new Random();
    private final Map<String, GatchaLike> gatchaContentMap;

    public GatchaScreen(@NotNull final Gui gui) {
        this.gui = Objects.requireNonNull(gui);
        gatchaContentMap = Gatcha.getAll().stream().collect(Collectors.toMap(
                gatcha -> gatcha.getTranslation(this.gui.getResourceBundle()),
                gatcha -> gatcha));
        final List<String> gatchaContent = new ArrayList<>(gatchaContentMap.keySet());
        Collections.sort(gatchaContent);
        final BorderPane borderPane = new BorderPane();
        final Button craftingBtn = new Button(this.gui.translate("gui.crafting"));
        craftingBtn.setOnMouseClicked(event -> this.gui.switchToCrafting());
        final Button saveBtn = new Button(this.gui.translate("gui.save_game"));
        saveBtn.setOnMouseClicked(event -> this.gui.saveGame());
        final Button loadBtn = new Button(this.gui.translate("gui.load_game"));
        loadBtn.setOnMouseClicked(event -> this.gui.loadGame());
        borderPane.setTop(new HBox(craftingBtn, saveBtn, loadBtn));
        final Label cbLabel = new Label(this.gui.translate("gui.gatcha_selection"));
        comboBox = new ComboBox<>(FXCollections.observableList(gatchaContent));
        comboBox.setOnAction(event -> gatchaSelected());
        offerings = new LabelledList(this.gui.translate("gui.gatcha_offerings"));
        final Button drawBtn = new Button(this.gui.translate("gui.gatcha_draw"));
        drawBtn.setOnMouseClicked(event -> gatchaDraw());
        final VBox leftSide = new VBox(cbLabel, comboBox, offerings, drawBtn);
        leftSide.setAlignment(Pos.CENTER);
        draw = new LabelledList(this.gui.translate("gui.gatcha_draws"));
        final HBox main = new HBox(leftSide, draw);
        main.setAlignment(Pos.CENTER);
        borderPane.setCenter(main);
        getChildren().add(borderPane);
    }

    public void gatchaSelected() {
        final String selection = comboBox.getSelectionModel().getSelectedItem();
        if (selection == null) {
            offerings.getListView().setItems(FXCollections.emptyObservableList());
            draw.getListView().setItems(FXCollections.emptyObservableList());
            return;
        }
        final GatchaLike selectedGatcha = gatchaContentMap.get(selection);
        offerings.getListView().setItems(FXCollections.observableList(
                selectedGatcha.getOfferings().stream().map(stack -> stack.getTranslation(gui.getResourceBundle())).toList()
        ));
    }

    public void gatchaDraw() {
        final Player player = gui.getPlayer();
        final String selection = comboBox.getSelectionModel().getSelectedItem();
        if (player == null || selection == null) {
            draw.getListView().setItems(FXCollections.emptyObservableList());
            return;
        }
        final GatchaLike selectedGatcha = gatchaContentMap.get(selection);
        if (player.getInventory().subtractAll(selectedGatcha.getOfferings())) {
            final Collection<StuffStack> result = selectedGatcha.roll(random);
            draw.getListView().setItems(FXCollections.observableList(
                    result.stream().map(stack -> stack.getTranslation(gui.getResourceBundle())).toList()
            ));
            player.getInventory().addAll(result);
        }
        else {
            draw.getListView().setItems(FXCollections.emptyObservableList());
        }
    }

}
