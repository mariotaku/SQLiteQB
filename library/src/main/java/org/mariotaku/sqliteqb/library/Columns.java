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

public class Columns implements Selectable {

    private final AbsColumn[] columns;

    public Columns(String... columns) {
        this(Columns.fromStrings(columns));
    }

    public Columns(final AbsColumn... columns) {
        this.columns = columns;
    }

    private static Column[] fromStrings(String... columnsString) {
        final Column[] columns = new Column[columnsString.length];
        for (int i = 0, j = columnsString.length; i < j; i++) {
            columns[i] = new Column(columnsString[i]);
        }
        return columns;
    }

    @Override
    public String getSQL() {
        return Utils.toString(columns, ',', true);
    }

    public abstract static class AbsColumn implements Selectable {

    }

    public static class AllColumn extends AbsColumn {

        private final Table table;

        public AllColumn() {
            this(null);
        }

        public AllColumn(final Table table) {
            this.table = table;
        }

        @Override
        public String getSQL() {
            return table != null ? table.getSQL() + ".*" : "*";
        }

    }

    public static class Column extends AbsColumn {

        private final Table table;
        private final String columnName, alias;

        public Column(final String columnName) {
            this(null, columnName, null);
        }

        public Column(final String columnName, final String alias) {
            this(null, columnName, alias);
        }

        public Column(final Table table, final String columnName) {
            this(table, columnName, null);
        }

        public Column(final Table table, final String columnName, final String alias) {
            if (columnName == null) throw new IllegalArgumentException("");
            this.table = table;
            this.columnName = columnName;
            this.alias = alias;
        }

        @Override
        public String getSQL() {
            final String col = table != null ? table.getSQL() + "." + columnName : columnName;
            return alias != null ? col + " AS " + alias : col;
        }
    }

}
