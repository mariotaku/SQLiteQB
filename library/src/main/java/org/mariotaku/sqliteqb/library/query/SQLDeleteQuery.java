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
import org.mariotaku.sqliteqb.library.SQLQuery;
import org.mariotaku.sqliteqb.library.Table;

public class SQLDeleteQuery implements SQLQuery {

    private Table table;
    private Expression where;

    @Override
    public String getSQL() {
        if (where == null) return String.format("DELETE FROM %s", table.getSQL());
        return String.format("DELETE FROM %S WHERE %s", table.getSQL(), where.getSQL());
    }

    void setFrom(final Table table) {
        this.table = table;
    }

    void setWhere(final Expression where) {
        this.where = where;
    }

    public static final class Builder implements IBuilder<SQLDeleteQuery> {
        private final SQLDeleteQuery query = new SQLDeleteQuery();
        private boolean buildCalled;

        @Override
        public SQLDeleteQuery build() {
            buildCalled = true;
            return query;
        }

        @Override
        public String buildSQL() {
            return build().getSQL();
        }

        public Builder from(final Table table) {
            checkNotBuilt();
            query.setFrom(table);
            return this;
        }

        public Builder where(final Expression where) {
            checkNotBuilt();
            query.setWhere(where);
            return this;
        }

        private void checkNotBuilt() {
            if (buildCalled) throw new IllegalStateException();
        }
    }

}
