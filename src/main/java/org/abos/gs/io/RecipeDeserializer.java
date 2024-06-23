package org.abos.gs.io;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import org.abos.gs.core.Recipe;
import org.abos.gs.core.StuffStack;

import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Deserializer for {@link Recipe} class. Needs registered items and tags to work.
 */
public final class RecipeDeserializer extends StdDeserializer<Recipe> {

    public RecipeDeserializer() {
        super(Recipe.class);
    }

    @Override
    public Recipe deserialize(final JsonParser jsonParser, final DeserializationContext deserializationContext) throws IOException {
        final JsonNode node = jsonParser.getCodec().readTree(jsonParser);
        final Collection<StuffStack> input = new LinkedList<>();
        final Iterator<JsonNode> inputIt = node.get("input").elements();
        while (inputIt.hasNext()) {
            final JsonNode stackNode = inputIt.next();
            input.add(jsonParser.getCodec().treeToValue(stackNode, StuffStack.class));
        }
        final Collection<StuffStack> output = new LinkedList<>();
        final Iterator<JsonNode> outputIt = node.get("output").elements();
        while (outputIt.hasNext()) {
            final JsonNode stackNode = outputIt.next();
            output.add(jsonParser.getCodec().treeToValue(stackNode, StuffStack.class));
        }
        final Collection<StuffStack> catalysts = new LinkedList<>();
        final Iterator<JsonNode> catalystsIt = node.get("catalysts").elements();
        while (catalystsIt.hasNext()) {
            final JsonNode stackNode = catalystsIt.next();
            catalysts.add(jsonParser.getCodec().treeToValue(stackNode, StuffStack.class));
        }
        return new Recipe(node.get("name").asText(), input, output, catalysts, node.get("duration").asInt());
    }
}
