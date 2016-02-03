/*
 * Copyright (c) 2015 mariotaku
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mariotaku.sqliteqb.library;

import java.util.Locale;

public class Utils {

    public static String toString(final Object[] array, final char token, final boolean includeSpace) {
        final StringBuilder builder = new StringBuilder();
        final int length = array.length;
        for (int i = 0; i < length; i++) {
            final String string = objectToString(array[i]);
            if (string != null) {
                if (i > 0) {
                    builder.append(includeSpace ? token + " " : token);
                }
                builder.append(string);
            }
        }
        return builder.toString();
    }

    public static String toStringForSQL(final int size) {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                builder.append(',');
            }
            builder.append('?');
        }
        return builder.toString();
    }

    private static String objectToString(Object o) {
        if (o instanceof SQLLang)
            return ((SQLLang) o).getSQL();
        return o != null ? o.toString() : null;
    }

}
