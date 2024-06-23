package org.abos.gs.io;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.abos.gs.core.Item;
import org.abos.gs.core.Stuff;
import org.abos.gs.core.StuffStack;
import org.abos.gs.core.Tag;

import java.io.IOException;
import java.util.Optional;

/**
 * Deserializer for {@link StuffStack} class. Needs registered items and tags to work.
 */
public final class StuffStackDeserializer extends StdDeserializer<StuffStack> {

    public StuffStackDeserializer() {
        super(StuffStack.class);
    }

    @Override
    public StuffStack deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        final String name = node.get("name").asText();
        Optional<? extends Stuff> resolvedStuff;
        resolvedStuff = Item.lookup(name);
        if (resolvedStuff.isEmpty()) {
            resolvedStuff = Tag.lookup(name);
        }
        if (resolvedStuff.isEmpty()) {
            throw new IllegalStateException(String.format("Stuff %s isn't registered!", name));
        }
        return new StuffStack(resolvedStuff.get(), node.get("count").asInt());
    }
}
