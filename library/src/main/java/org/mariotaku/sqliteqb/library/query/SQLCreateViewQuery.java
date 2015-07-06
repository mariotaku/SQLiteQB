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

import org.mariotaku.sqliteqb.library.SQLQuery;

public class SQLCreateViewQuery implements SQLQuery {

    private boolean temporary;
    private boolean createIfNotExists;
    private String name;
    private SQLSelectQuery selectStmt;

    SQLCreateViewQuery() {

    }

    @Override
    public String getSQL() {
        if (name == null) throw new NullPointerException("NAME must not be null!");
        if (selectStmt == null)
            throw new NullPointerException("SELECT statement must not be null!");
        final StringBuilder sb = new StringBuilder("CREATE ");
        if (temporary) {
            sb.append("TEMPORARY ");
        }
        sb.append("VIEW ");
        if (createIfNotExists) {
            sb.append("IF NOT EXISTS ");
        }
        sb.append(String.format("%s AS %s", name, selectStmt.getSQL()));
        return sb.toString();
    }

    void setAs(final SQLSelectQuery selectStmt) {
        this.selectStmt = selectStmt;
    }

    void setCreateIfNotExists(final boolean createIfNotExists) {
        this.createIfNotExists = createIfNotExists;
    }

    void setName(final String name) {
        this.name = name;
    }

    void setTemporary(final boolean temporary) {
        this.temporary = temporary;
    }

    public static final class Builder implements IBuilder<SQLCreateViewQuery> {

        private final SQLCreateViewQuery query = new SQLCreateViewQuery();
        private boolean buildCalled;

        public Builder as(final SQLSelectQuery selectStmt) {
            checkNotBuilt();
            query.setAs(selectStmt);
            return this;
        }

        @Override
        public SQLCreateViewQuery build() {
            buildCalled = true;
            return query;
        }

        @Override
        public String buildSQL() {
            return build().getSQL();
        }

        public Builder createTemporaryView(final boolean createIfNotExists, final String name) {
            return createView(true, createIfNotExists, name);
        }

        public Builder createView(final boolean temporary, final boolean createIfNotExists, final String name) {
            checkNotBuilt();
            query.setTemporary(temporary);
            query.setCreateIfNotExists(createIfNotExists);
            query.setName(name);
            return this;
        }

        public Builder createView(final boolean createIfNotExists, final String name) {
            return createView(false, createIfNotExists, name);
        }

        private void checkNotBuilt() {
            if (buildCalled) throw new IllegalStateException();
        }
    }
}
