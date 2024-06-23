package org.abos.gs.datagen;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.abos.gs.core.ItemLike;
import org.abos.gs.data.Items;
import org.abos.gs.io.ItemSerializer;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;

/**
 * Creates all {@link org.abos.gs.core.ItemLike} files.
 */
public final class ItemGenerator implements Runnable {

    private final ObjectMapper mapper;

    public ItemGenerator() {
        mapper = new ObjectMapper();
        final SimpleModule module = new SimpleModule("CustomSerializer", new Version(1, 0, 0, null, null, null));
        module.addSerializer(new ItemSerializer());
        mapper.registerModule(module);
    }

    @Override
    public void run() {
        final Path resourceLocation = Generators.getResourceLocation().resolve("generated").resolve("items");
        if (!resourceLocation.toFile().isDirectory() && !resourceLocation.toFile().mkdirs()) {
            throw new IllegalStateException("Item directory couldn't be created!");
        }
        // persist items
        try {
            for (final Field itemField : Items.class.getFields()) {
                final ItemLike item = (ItemLike)itemField.get(null);
                final File file = resourceLocation.resolve(item.getName() + Generators.FILE_SUFFIX).toFile();
                mapper.writerWithDefaultPrettyPrinter().writeValue(file, item);
            }
        } catch (final IOException | IllegalAccessException ex) {
            throw new IllegalStateException(ex);
        }
    }

}
