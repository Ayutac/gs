package org.abos.gs.io;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.abos.gs.core.Item;
import org.abos.gs.core.ItemLike;
import org.abos.gs.core.Tag;

import java.io.IOException;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Deserializer for {@link Tag} class. Needs registered items to work.
 */
public final class TagDeserializer extends StdDeserializer<Tag> {

    public TagDeserializer() {
        super(Tag.class);
    }

    @Override
    public Tag deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        final Set<ItemLike> items = new HashSet<>();
        final Iterator<JsonNode> it = node.get("items").elements();
        while (it.hasNext()) {
            if (!items.add(Item.lookup(it.next().asText()).get())) {
                throw new IllegalStateException("Duplicate item detected in " + node.get("name").asText());
            }
        }
        return new Tag(node.get("name").asText(), items);
    }
}
