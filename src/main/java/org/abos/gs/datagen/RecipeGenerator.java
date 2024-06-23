package org.abos.gs.datagen;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.abos.gs.core.RecipeLike;
import org.abos.gs.data.Recipes;
import org.abos.gs.core.TagLike;
import org.abos.gs.io.RecipeSerializer;
import org.abos.gs.io.StuffStackSerializer;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;

/**
 * Creates all {@link TagLike} files.
 */
public final class RecipeGenerator implements Runnable {

    private final ObjectMapper mapper;

    public RecipeGenerator() {
        mapper = new ObjectMapper();
        final SimpleModule module = new SimpleModule("CustomSerializer", new Version(1, 0, 0, null, null, null));
        module.addSerializer(new RecipeSerializer());
        module.addSerializer(new StuffStackSerializer());
        mapper.registerModule(module);
    }

    @Override
    public void run() {
        final Path resourceLocation = Generators.getResourceLocation().resolve("recipes");
        if (!resourceLocation.toFile().isDirectory() && !resourceLocation.toFile().mkdirs()) {
            throw new IllegalStateException("Recipe directory couldn't be created!");
        }
        // persist items
        try {
            for (final Field recipeField : Recipes.class.getFields()) {
                final RecipeLike recipe = (RecipeLike)recipeField.get(null);
                final File file = resourceLocation.resolve(recipe.getName() + Generators.FILE_SUFFIX).toFile();
                mapper.writerWithDefaultPrettyPrinter().writeValue(file, recipe);
            }
        } catch (final IOException | IllegalAccessException ex) {
            throw new IllegalStateException(ex);
        }
    }

}
