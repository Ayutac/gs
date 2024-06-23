package org.abos.gs.datagen;

import java.nio.file.Path;

public final class Generators {

    public static final String FILE_SUFFIX = ".json";

    private Generators() {
        /* No instantiation */
    }

    public static Path getResourceLocation() {
        return Path.of(System.getProperty("user.dir"), "src", "main", "resources");
    }

    public static void main(String[] args) {
        new ItemGenerator().run();
        new TagGenerator().run();
    }

}
