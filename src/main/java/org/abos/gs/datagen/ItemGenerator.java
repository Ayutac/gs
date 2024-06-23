package org.abos.gs.datagen;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.abos.gs.core.Item;
import org.abos.gs.core.ItemLike;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

/**
 * Creates all {@link org.abos.gs.core.ItemLike} files.
 */
public final class ItemGenerator implements Runnable {

    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    public void run() {
        final List<ItemLike> items = new LinkedList<>();
        final Path resourceLocation = Generators.getResourceLocation().resolve("items");
        if (!resourceLocation.toFile().isDirectory() && !resourceLocation.toFile().mkdirs()) {
            throw new IllegalStateException("Item directory couldn't be created!");
        }
        // add items
        items.add(new Item("test", 0));
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
