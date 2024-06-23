package org.abos.gs.io;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.abos.gs.core.Recipe;
import org.abos.gs.core.StuffStack;

import java.io.IOException;

/**
 * Serializer for {@link Recipe} class.
 */
public final class RecipeSerializer extends StdSerializer<Recipe> {

    public RecipeSerializer() {
        super(Recipe.class);
    }

    @Override
    public void serialize(final Recipe recipe, final JsonGenerator jsonGenerator, final SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", recipe.getName());
        jsonGenerator.writeArrayFieldStart("input");
        for (final StuffStack stack : recipe.getInput()) {
            jsonGenerator.writeObject(stack);
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeArrayFieldStart("output");
        for (final StuffStack stack : recipe.getOutput()) {
            jsonGenerator.writeObject(stack);
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeArrayFieldStart("catalysts");
        for (final StuffStack stack : recipe.getCatalysts()) {
            jsonGenerator.writeObject(stack);
        }
        jsonGenerator.writeEndArray();
        jsonGenerator.writeNumberField("duration", recipe.getDuration());
        jsonGenerator.writeEndObject();
    }
}
