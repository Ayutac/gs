package org.abos.common;

import org.jetbrains.annotations.NotNull;

import java.util.ResourceBundle;

/**
 * Represents a named object that has a translation.
 */
public interface Translatable extends Named {

    /**
     * Returns the translation of this object
     * @return the translation of this object, not {@code null}
     */
    @NotNull
    default String getTranslation(@NotNull ResourceBundle rb) {
        return rb.getString(getName());
    }

}