package org.abos.gs.datagen;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.abos.gs.core.TagLike;
import org.abos.gs.data.Tags;
import org.abos.gs.io.TagSerializer;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;

/**
 * Creates all {@link TagLike} files.
 */
public final class TagGenerator implements Runnable {

    private final ObjectMapper mapper;

    public TagGenerator() {
        mapper = new ObjectMapper();
        final SimpleModule module = new SimpleModule("CustomSerializer", new Version(1, 0, 0, null, null, null));
        module.addSerializer(new TagSerializer());
        mapper.registerModule(module);
    }

    @Override
    public void run() {
        final Path resourceLocation = Generators.getResourceLocation().resolve("tags");
        if (!resourceLocation.toFile().isDirectory() && !resourceLocation.toFile().mkdirs()) {
            throw new IllegalStateException("Tag directory couldn't be created!");
        }
        // persist items
        try {
            for (final Field tagField : Tags.class.getFields()) {
                final TagLike tag = (TagLike)tagField.get(null);
                final File file = resourceLocation.resolve(tag.getName() + Generators.FILE_SUFFIX).toFile();
                mapper.writerWithDefaultPrettyPrinter().writeValue(file, tag);
            }
        } catch (final IOException | IllegalAccessException ex) {
            throw new IllegalStateException(ex);
        }
    }

}
