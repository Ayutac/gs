package org.abos.gs.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.abos.gs.core.Gatcha;
import org.abos.gs.core.GatchaLike;
import org.abos.gs.core.Item;
import org.abos.gs.core.ItemLike;
import org.abos.gs.core.Player;
import org.abos.gs.core.Recipe;
import org.abos.gs.core.RecipeLike;
import org.abos.gs.core.Tag;
import org.abos.gs.core.TagLike;
import org.abos.gs.data.Gatchas;
import org.abos.gs.data.Items;
import org.abos.gs.data.Recipes;
import org.abos.gs.data.Tags;
import org.abos.gs.gui.component.dialog.PlayerNameDialog;
import org.abos.gs.gui.component.pane.CraftingScreen;
import org.abos.gs.gui.component.pane.GatchaScreen;
import org.abos.gs.gui.component.pane.MainMenu;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public final class Gui extends Application {

    private static final int DEFAULT_WIDTH = 640;
    private static final int DEFAULT_HEIGHT = 480;

    private final Scene mainMenuScene = new Scene(new MainMenu(this), DEFAULT_WIDTH, DEFAULT_HEIGHT);
    private final CraftingScreen craftingScreen = new CraftingScreen(this);
    private final Scene craftingScreenScene = new Scene(craftingScreen, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    private final GatchaScreen gatchaScreen = new GatchaScreen(this);
    private final Scene gatchaScreenScene = new Scene(gatchaScreen, DEFAULT_WIDTH, DEFAULT_HEIGHT);
    private Stage stage;

    private Player player;

    static {
        // TODO register not the default stuff
        try {
            for (final Field field : Items.class.getFields()) {
                Item.register((ItemLike)field.get(null));
            }
            for (final Field field : Tags.class.getFields()) {
                Tag.register((TagLike)field.get(null));
            }
            for (final Field field : Recipes.class.getFields()) {
                Recipe.register((RecipeLike)field.get(null));
            }
            for (final Field field : Gatchas.class.getFields()) {
                Gatcha.register((GatchaLike)field.get(null));
            }
        } catch (IllegalAccessException ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        this.stage.setScene(mainMenuScene);
        this.stage.show();
    }

    public void newGame() {
        final PlayerNameDialog dialog = new PlayerNameDialog(this);
        Optional<String> result = dialog.showAndWait();
        if (result.isPresent()) {
            player = new Player(result.get());
            craftingScreen.update();
            stage.setScene(craftingScreenScene);
        }
    }

    public void switchToCrafting() {
        craftingScreen.update();
        stage.setScene(craftingScreenScene);
    }

    public void switchToGatcha() {
        stage.setScene(gatchaScreenScene);
    }

    public Player getPlayer() {
        return player;
    }

    public ResourceBundle getResourceBundle() {
        return ResourceBundle.getBundle("i18n.translations", Locale.US);
    }

    public String translate(@NotNull final String key) {
        return getResourceBundle().getString(key);
    }

    public static void main(String[] args) {
        launch();
    }

}
