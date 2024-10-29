/*******************************************************************************
 * Copyright (c) 2014
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    Benjamin Klatt - initial API and implementation and/or initial documentation
 *******************************************************************************/
package coveragepac;

import java.util.Map;
import java.util.regex.Pattern;

import com.google.common.base.Strings;

/**
 * Utilities to handle normalization of elements, values, name spaces etc.
 */
public final class NormalizationUtil {
    /** Disable constructor to prevent initialization. */
    private NormalizationUtil() {
    }

    /**
     * Apply a set of normalizations patterns to a string. The patterns to apply are provided as a
     * map, linked to a string the pattern should be replaced with in case of a match.
     *
     * @param original
     *            The string to normalize.
     * @param normalizations
     *            The map of normalization patterns and according replacements.
     * @return The normalized string. If null was submitted, an empty string will be returned.
     */
    public static String normalize(String original, Map<Pattern, String> normalizations) {
        String renamed = Strings.nullToEmpty(original);
        for (Pattern pattern : normalizations.keySet()) {
            String replaceString = normalizations.get(pattern);
            renamed = pattern.matcher(renamed).replaceAll(replaceString);
        }
        return renamed;
    }
}
