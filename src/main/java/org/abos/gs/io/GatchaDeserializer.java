package org.abos.gs.io;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.abos.gs.core.Distribution;
import org.abos.gs.core.Gatcha;
import org.abos.gs.core.StuffStack;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Deserializer for {@link Gatcha} class. Needs registered items and tags to work.
 */
public final class GatchaDeserializer extends StdDeserializer<Gatcha> {

    public GatchaDeserializer() {
        super(Gatcha.class);
    }

    @Override
    public Gatcha deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        final Collection<StuffStack> offerings = new LinkedList<>();
        final Iterator<JsonNode> offeringsIt = node.get("offerings").elements();
        while (offeringsIt.hasNext()) {
            final JsonNode stackNode = offeringsIt.next();
            offerings.add(jsonParser.getCodec().treeToValue(stackNode, StuffStack.class));
        }
        final Collection<Distribution> distributions = new LinkedList<>();
        final Iterator<JsonNode> outputIt = node.get("distributions").elements();
        while (outputIt.hasNext()) {
            final JsonNode stackNode = outputIt.next();
            distributions.add(jsonParser.getCodec().treeToValue(stackNode, Distribution.class));
        }
        return new Gatcha(node.get("name").asText(), offerings, distributions);
    }
}
