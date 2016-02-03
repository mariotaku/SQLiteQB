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

import org.mariotaku.sqliteqb.library.NewColumn;
import org.mariotaku.sqliteqb.library.SQLQuery;

public class SQLAlterTableQuery implements SQLQuery {

    private String table;
    private String renameTo;
    private NewColumn addColumn;

    @Override
    public String getSQL() {
        if (table == null) throw new NullPointerException("table must not be null!");
        if (renameTo == null && addColumn == null) throw new NullPointerException();
        final StringBuilder sb = new StringBuilder();
        sb.append("ALTER TABLE ");
        sb.append(table);
        if (renameTo != null) {
            sb.append(" RENAME TO ");
            sb.append(renameTo);
        } else if (addColumn != null) {
            sb.append(" ADD COLUMN ");
            sb.append(addColumn.getSQL());
        }
        return sb.toString();
    }

    void setAddColumn(final NewColumn addColumn) {
        this.addColumn = addColumn;
    }

    void setRenameTo(final String renameTo) {
        this.renameTo = renameTo;
    }

    void setTable(final String table) {
        this.table = table;
    }

    public static final class Builder implements IBuilder<SQLAlterTableQuery> {

        private final SQLAlterTableQuery query = new SQLAlterTableQuery();
        private boolean buildCalled;

        public Builder addColumn(final NewColumn addColumn) {
            checkNotBuilt();
            query.setAddColumn(addColumn);
            return this;
        }

        public Builder alterTable(final String table) {
            checkNotBuilt();
            query.setTable(table);
            return this;
        }

        @Override
        public SQLAlterTableQuery build() {
            return query;
        }

        @Override
        public String buildSQL() {
            return build().getSQL();
        }

        public Builder renameTo(final String renameTo) {
            checkNotBuilt();
            query.setRenameTo(renameTo);
            return this;
        }

        private void checkNotBuilt() {
            if (buildCalled) throw new IllegalStateException();
        }
    }

}
