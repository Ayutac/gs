package org.abos.gs.io;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.abos.gs.core.Distribution;
import org.abos.gs.core.DistributionEntry;

import java.io.IOException;

public final class DistributionSerializer extends StdSerializer<Distribution> {

    public DistributionSerializer() {
        super(Distribution.class);
    }

    @Override
    public void serialize(final Distribution dist, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeArrayFieldStart("items");
        for (final DistributionEntry de : dist.getEntries()) {
            jsonGenerator.writeObject(de);
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeNumberField("rolls", dist.getRolls());
        jsonGenerator.writeEndObject();
    }
}
