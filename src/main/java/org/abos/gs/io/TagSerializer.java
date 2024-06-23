package org.abos.gs.io;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.abos.gs.core.ItemLike;
import org.abos.gs.core.Tag;

import java.io.IOException;

public final class TagSerializer extends StdSerializer<Tag> {

    public TagSerializer() {
        super(Tag.class);
    }

    @Override
    public void serialize(final Tag tag, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", tag.getName());
        jsonGenerator.writeArrayFieldStart("items");
        for (final ItemLike item : tag) {
            jsonGenerator.writeString(item.getName());
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}
