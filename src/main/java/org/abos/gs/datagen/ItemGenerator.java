package org.abos.gs.datagen;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.abos.gs.core.Item;
import org.abos.gs.core.ItemLike;
import org.abos.gs.io.ItemSerializer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

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
        final List<ItemLike> items = new LinkedList<>();
        final Path resourceLocation = Generators.getResourceLocation().resolve("items");
        if (!resourceLocation.toFile().isDirectory() && !resourceLocation.toFile().mkdirs()) {
            throw new IllegalStateException("Item directory couldn't be created!");
        }
        // add items
        items.add(new Item("stick", 0));
        items.add(new Item("small_stone", 0));
        items.add(new Item("big_stone", 0));
        items.add(new Item("small_sharp_stone", 1));
        items.add(new Item("big_sharp_stone", 1));
        // persist items
        try {
            for (final ItemLike item : items) {
                final File file = resourceLocation.resolve(item.getName() + Generators.FILE_SUFFIX).toFile();
                mapper.writerWithDefaultPrettyPrinter().writeValue(file, item);
            }
        } catch (final IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

}
