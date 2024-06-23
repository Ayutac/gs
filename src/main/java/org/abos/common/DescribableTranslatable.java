package org.abos.common;

import org.jetbrains.annotations.NotNull;

import java.util.ResourceBundle;

/**
 * Represents a named translatable object that also has a translatable description.
 */
public interface DescribableTranslatable extends Describable, Translatable {

    /**
     * Returns the translation of this object's description.
     * @return the translation of this object's description, might be empty but not {@code null}
     */
    @NotNull
    default String getDescriptionTranslation(@NotNull ResourceBundle rb) {
        return rb.getString(getDescription());
    }

}