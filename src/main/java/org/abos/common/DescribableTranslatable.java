package org.abos.common;

import java.util.ResourceBundle;

/**
 * Represents a named translatable object that also has a translatable description.
 */
public interface DescribableTranslatable extends Describable, Translatable {

    /**
     * Returns the translation of this object's description.
     * @return the translation of this object's description, might be empty but not {@code null}
     */
    default String getDescriptionTranslation(ResourceBundle rb) {
        return rb.getString(getDescription());
    }

}