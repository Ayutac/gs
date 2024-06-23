package org.abos.gs.io;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.abos.gs.core.Distribution;
import org.abos.gs.core.DistributionEntry;

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Deserializer for {@link Distribution} class. Needs registered items and tags to work.
 */
public final class DistributionDeserializer extends StdDeserializer<Distribution> {

    public DistributionDeserializer() {
        super(Distribution.class);
    }

    @Override
    public Distribution deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        final List<DistributionEntry> entries = new LinkedList<>();
        final Iterator<JsonNode> it = node.get("entries").elements();
        while (it.hasNext()) {
            final JsonNode stackNode = it.next();
            entries.add(jsonParser.getCodec().treeToValue(stackNode, DistributionEntry.class));
        }
        return new Distribution(entries, node.get("rolls").asInt());
    }
}
