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

public class NewColumn implements SQLLang {

    private final String name;
    private final String type;

    public NewColumn(final String name, final String type) {
        this.name = name;
        this.type = type;
    }

    public static NewColumn[] createNewColumns(final String[] colNames, final String[] colTypes) {
        if (colNames == null || colTypes == null || colNames.length != colTypes.length)
            throw new IllegalArgumentException("length of columns and types not match.");
        final NewColumn[] newColumns = new NewColumn[colNames.length];
        for (int i = 0, j = colNames.length; i < j; i++) {
            newColumns[i] = new NewColumn(colNames[i], colTypes[i]);
        }
        return newColumns;
    }

    public String getName() {
        return name;
    }

    @Override
    public String getSQL() {
        if (name == null || type == null)
            throw new NullPointerException("name and type must not be null!");
        return name + " " + type;
    }

    public String getType() {
        return type;
    }

}
