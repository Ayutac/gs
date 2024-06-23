package org.abos.gs.io;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.abos.gs.core.Distribution;
import org.abos.gs.core.Gatcha;
import org.abos.gs.core.StuffStack;

import java.io.IOException;

public final class GatchaSerializer extends StdSerializer<Gatcha> {

    public GatchaSerializer() {
        super(Gatcha.class);
    }

    @Override
    public void serialize(final Gatcha gatcha, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", gatcha.getName());
        jsonGenerator.writeArrayFieldStart("offerings");
        for (final StuffStack stack : gatcha.getOfferings()) {
            jsonGenerator.writeObject(stack);
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeArrayFieldStart("distributions");
        for (final Distribution dist : gatcha.getDistributions()) {
            jsonGenerator.writeObject(dist);
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeEndObject();
    }
}
