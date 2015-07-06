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

/**
 * Created by mariotaku on 15/3/30.
 */
public class Constraint implements SQLLang {
    private final String name;
    private final String type;
    private final SQLQuery constraint;

    public Constraint(String name, String type, SQLQuery constraint) {
        this.name = name;
        this.type = type;
        this.constraint = constraint;
    }

    @Override
    public String getSQL() {
        final StringBuilder sb = new StringBuilder();
        if (name != null) {
            sb.append("CONSTRAINT ");
            sb.append(name);
            sb.append(" ");
        }
        sb.append(type);
        sb.append(" ");
        sb.append(constraint.getSQL());
        return sb.toString();
    }

    public static Constraint unique(String name, Columns columns, OnConflict onConflict) {
        return new Constraint(name, "UNIQUE", new ColumnConflictConstaint(columns, onConflict));
    }

    public static Constraint unique(Columns columns, OnConflict onConflict) {
        return unique(null, columns, onConflict);
    }

    private static final class ColumnConflictConstaint implements SQLQuery {

        private final Columns columns;
        private final OnConflict onConflict;

        public ColumnConflictConstaint(Columns columns, OnConflict onConflict) {
            this.columns = columns;
            this.onConflict = onConflict;
        }

        @Override
        public String getSQL() {
            final StringBuilder sb = new StringBuilder();
            sb.append("(");
            sb.append(columns.getSQL());
            sb.append(") ");
            sb.append("ON CONFLICT ");
            sb.append(onConflict.getAction());
            return sb.toString();
        }
    }

}
