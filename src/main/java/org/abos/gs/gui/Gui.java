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
import org.abos.gs.gui.component.dialogue.PlayerNameDialog;
import org.abos.gs.gui.component.pane.CraftingScreen;
import org.abos.gs.gui.component.pane.MainMenu;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Field;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

public final class Gui extends Application {

    private final Scene mainMenuScene = new Scene(new MainMenu(this), 640, 480);
    private final CraftingScreen craftingScreen = new CraftingScreen(this);
    private final Scene craftingScreenScene = new Scene(craftingScreen, 640, 480);
    private Stage stage;

    private Player player;

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        this.stage.setScene(mainMenuScene);
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

    public Player getPlayer() {
        return player;
    }

    public ResourceBundle getResourceBundle() {
        return ResourceBundle.getBundle("i18n.translations", Locale.US);
    }

    public String translate(@NotNull final String key) {
        return getResourceBundle().getString(key);
    }

}
