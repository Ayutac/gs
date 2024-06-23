package org.abos.gs.io;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.abos.gs.core.Item;

import java.io.IOException;

public final class ItemSerializer extends StdSerializer<Item> {

    public ItemSerializer() {
        super(Item.class);
    }

    @Override
    public void serialize(final Item item, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", item.getName());
        jsonGenerator.writeNumberField("rarity", item.getRarity());
        jsonGenerator.writeEndObject();
    }
}
