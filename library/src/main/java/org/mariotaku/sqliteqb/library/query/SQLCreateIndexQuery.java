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

import org.mariotaku.sqliteqb.library.Columns;
import org.mariotaku.sqliteqb.library.Expression;
import org.mariotaku.sqliteqb.library.SQLQuery;
import org.mariotaku.sqliteqb.library.Table;

public class SQLCreateIndexQuery implements SQLQuery {

    private boolean unique;
    private boolean createIfNotExists;
    private Table table;
    private String indexName;
    private Columns indexedColumns;
    private Expression where;

    SQLCreateIndexQuery() {

    }

    @Override
    public String getSQL() {
        if (table == null) throw new NullPointerException("Table must not be null!");
        if (indexName == null)
            throw new NullPointerException("SELECT statement must not be null!");
        final StringBuilder sb = new StringBuilder("CREATE");
        if (unique) {
            sb.append(" UNIQUE");
        }
        sb.append(" INDEX");
        if (createIfNotExists) {
            sb.append(" IF NOT EXISTS");
        }
        if (indexedColumns == null)
            throw new NullPointerException("Indexed columns must not be null !");
        sb.append(" ");
        sb.append(indexName);
        sb.append(" ON ");
        sb.append(table.getSQL());
        sb.append(" (");
        sb.append(indexedColumns.getSQL());
        sb.append(")");
        if (where != null) {
            sb.append(" WHERE");
            sb.append(where.getSQL());
        }
        return sb.toString();
    }

    public void setIndexedColumns(Columns indexedColumns) {
        this.indexedColumns = indexedColumns;
    }

    public void setWhere(Expression where) {
        this.where = where;
    }

    void setIndexName(final String indexName) {
        this.indexName = indexName;
    }

    void setCreateIfNotExists(final boolean createIfNotExists) {
        this.createIfNotExists = createIfNotExists;
    }

    void setTable(final Table table) {
        this.table = table;
    }

    void setUnique(final boolean unique) {
        this.unique = unique;
    }

    public static final class Builder implements IBuilder<SQLCreateIndexQuery> {

        private final SQLCreateIndexQuery query = new SQLCreateIndexQuery();
        private boolean buildCalled;

        public Builder on(final Table table, Columns indexedColumns) {
            checkNotBuilt();
            query.setTable(table);
            query.setIndexedColumns(indexedColumns);
            return this;
        }

        public Builder name(final String name) {
            checkNotBuilt();
            query.setIndexName(name);
            return this;
        }

        public Builder where(final Expression expression) {
            checkNotBuilt();
            query.setWhere(expression);
            return this;
        }


        @Override
        public SQLCreateIndexQuery build() {
            buildCalled = true;
            return query;
        }

        @Override
        public String buildSQL() {
            return build().getSQL();
        }


        public Builder createIndex(final boolean unique, final boolean createIfNotExists) {
            checkNotBuilt();
            query.setUnique(unique);
            query.setCreateIfNotExists(createIfNotExists);
            return this;
        }

        private void checkNotBuilt() {
            if (buildCalled) throw new IllegalStateException();
        }
    }
}
