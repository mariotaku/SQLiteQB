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

package org.mariotaku.sqliteqb.library.query;

import org.mariotaku.sqliteqb.library.Expression;
import org.mariotaku.sqliteqb.library.OnConflict;
import org.mariotaku.sqliteqb.library.SQLQuery;
import org.mariotaku.sqliteqb.library.SetValue;
import org.mariotaku.sqliteqb.library.Table;
import org.mariotaku.sqliteqb.library.Utils;

public class SQLUpdateQuery implements SQLQuery {

    private OnConflict onConflict;
    private Table table;
    private SetValue[] values;
    private Expression where;

    SQLUpdateQuery() {

    }

    @Override
    public String getSQL() {
        if (table == null) throw new NullPointerException("table must not be null!");
        final StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        if (onConflict != null) {
            sb.append("OR ");
            sb.append(onConflict.getAction());
            sb.append(" ");
        }
        sb.append(table.getSQL());
        sb.append(" SET ");
        sb.append(Utils.toString(values, ',', true));
        if (where != null) {
            sb.append(" WHERE ");
            sb.append(where.getSQL());
        }
        return sb.toString();
    }

    void setWhere(final Expression where) {
        this.where = where;
    }

    void setValues(final SetValue[] columns) {
        this.values = columns;
    }

    void setOnConflict(final OnConflict onConflict) {
        this.onConflict = onConflict;
    }

    void setTable(final Table table) {
        this.table = table;
    }

    public static final class Builder implements IBuilder<SQLUpdateQuery> {

        private final SQLUpdateQuery query = new SQLUpdateQuery();

        private boolean buildCalled;

        @Override
        public SQLUpdateQuery build() {
            buildCalled = true;
            return query;
        }

        @Override
        public String buildSQL() {
            return build().getSQL();
        }

        public Builder set(final SetValue... values) {
            checkNotBuilt();
            query.setValues(values);
            return this;
        }

        public Builder where(final Expression where) {
            checkNotBuilt();
            query.setWhere(where);
            return this;
        }

        public Builder update(final OnConflict onConflict, final Table table) {
            checkNotBuilt();
            query.setOnConflict(onConflict);
            query.setTable(table);
            return this;
        }

        public Builder update(final Table table) {
            return update(null, table);
        }

        private void checkNotBuilt() {
            if (buildCalled) throw new IllegalStateException();
        }

    }

}
