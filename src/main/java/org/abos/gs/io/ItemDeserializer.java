package org.abos.gs.io;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.abos.gs.core.Item;

import java.io.IOException;

public final class ItemDeserializer extends StdDeserializer<Item> {

    public ItemDeserializer() {
        super(Item.class);
    }

    @Override
    public Item deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        return new Item(node.get("name").asText(), node.get("rarity").asInt());
    }
}
