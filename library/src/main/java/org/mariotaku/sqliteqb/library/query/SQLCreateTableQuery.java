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

import org.mariotaku.sqliteqb.library.Constraint;
import org.mariotaku.sqliteqb.library.NewColumn;
import org.mariotaku.sqliteqb.library.SQLQuery;
import org.mariotaku.sqliteqb.library.Utils;

public class SQLCreateTableQuery implements SQLQuery {

    private boolean temporary;
    private boolean createIfNotExists;
    private String table;
    private NewColumn[] newColumns;
    private SQLSelectQuery selectStmt;
    private Constraint[] constraints;

    SQLCreateTableQuery() {
    }

    @Override
    public String getSQL() {
        if (table == null) throw new NullPointerException("NAME must not be null!");
        if ((newColumns == null || newColumns.length == 0) && selectStmt == null)
            throw new NullPointerException("Columns or AS must not be null!");
        final StringBuilder sb = new StringBuilder("CREATE ");
        if (temporary) {
            sb.append("TEMPORARY ");
        }
        sb.append("TABLE ");
        if (createIfNotExists) {
            sb.append("IF NOT EXISTS ");
        }
        sb.append(table);
        sb.append(' ');
        if (newColumns != null && newColumns.length > 0) {
            sb.append('(');
            sb.append(Utils.toString(newColumns, ',', true));
            if (constraints != null && constraints.length > 0) {
                sb.append(", ");
                sb.append(Utils.toString(constraints, ',', true));
                sb.append(' ');
            }
            sb.append(')');
        } else {
            sb.append("AS ");
            sb.append(selectStmt.getSQL());
        }
        return sb.toString();
    }

    void setAs(final SQLSelectQuery selectStmt) {
        this.selectStmt = selectStmt;
    }

    void setCreateIfNotExists(final boolean createIfNotExists) {
        this.createIfNotExists = createIfNotExists;
    }

    void setNewColumns(final NewColumn[] newColumns) {
        this.newColumns = newColumns;
    }

    void setTable(final String table) {
        this.table = table;
    }

    void setTemporary(final boolean temporary) {
        this.temporary = temporary;
    }

    public static final class Builder implements IBuilder<SQLCreateTableQuery> {

        private final SQLCreateTableQuery query = new SQLCreateTableQuery();

        private boolean buildCalled;

        public Builder as(final SQLSelectQuery selectStmt) {
            checkNotBuilt();
            query.setAs(selectStmt);
            return this;
        }

        @Override
        public SQLCreateTableQuery build() {
            buildCalled = true;
            return query;
        }

        @Override
        public String buildSQL() {
            return build().getSQL();
        }

        public Builder columns(final NewColumn... newColumns) {
            checkNotBuilt();
            query.setNewColumns(newColumns);
            return this;
        }

        public Builder constraint(final Constraint... constraints) {
            checkNotBuilt();
            query.setConstraints(constraints);
            return this;
        }

        public Builder createTable(final boolean temporary, final boolean createIfNotExists, final String table) {
            checkNotBuilt();
            query.setTemporary(temporary);
            query.setCreateIfNotExists(createIfNotExists);
            query.setTable(table);
            return this;
        }

        public Builder createTable(final boolean createIfNotExists, final String table) {
            return createTable(false, createIfNotExists, table);
        }

        public Builder createTemporaryTable(final boolean createIfNotExists, final String table) {
            return createTable(true, createIfNotExists, table);
        }

        private void checkNotBuilt() {
            if (buildCalled) throw new IllegalStateException();
        }

    }

    private void setConstraints(Constraint[] constraints) {
        this.constraints = constraints;
    }

}
