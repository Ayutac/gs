package org.abos.gs.datagen;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.abos.gs.core.GatchaLike;
import org.abos.gs.core.TagLike;
import org.abos.gs.data.Gatchas;
import org.abos.gs.io.DistributionSerializer;
import org.abos.gs.io.GatchaSerializer;
import org.abos.gs.io.StuffStackSerializer;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Path;

/**
 * Creates all {@link TagLike} files.
 */
public final class GatchaGenerator implements Runnable {

    private final ObjectMapper mapper;

    public GatchaGenerator() {
        mapper = new ObjectMapper();
        final SimpleModule module = new SimpleModule("CustomSerializer", new Version(1, 0, 0, null, null, null));
        module.addSerializer(new GatchaSerializer());
        module.addSerializer(new DistributionSerializer());
        module.addSerializer(new StuffStackSerializer());
        mapper.registerModule(module);
    }

    @Override
    public void run() {
        final Path resourceLocation = Generators.getResourceLocation().resolve("gatcha");
        if (!resourceLocation.toFile().isDirectory() && !resourceLocation.toFile().mkdirs()) {
            throw new IllegalStateException("Gatcha directory couldn't be created!");
        }
        // persist items
        try {
            for (final Field recipeField : Gatchas.class.getFields()) {
                final GatchaLike gatcha = (GatchaLike)recipeField.get(null);
                final File file = resourceLocation.resolve(gatcha.getName() + Generators.FILE_SUFFIX).toFile();
                mapper.writerWithDefaultPrettyPrinter().writeValue(file, gatcha);
            }
        } catch (final IOException | IllegalAccessException ex) {
            throw new IllegalStateException(ex);
        }
    }

}
