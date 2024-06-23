package org.abos.gs.io;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.abos.gs.core.StuffStack;

import java.io.IOException;

/**
 * Serializer for {@link StuffStack} class.
 */
public final class StuffStackSerializer extends StdSerializer<StuffStack> {

    public StuffStackSerializer() {
        super(StuffStack.class);
    }

    @Override
    public void serialize(final StuffStack stack, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", stack.stuff().getName());
        jsonGenerator.writeNumberField("count", stack.count());
        jsonGenerator.writeEndObject();
    }
}
