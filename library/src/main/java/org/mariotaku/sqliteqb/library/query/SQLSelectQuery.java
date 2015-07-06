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
import org.mariotaku.sqliteqb.library.Join;
import org.mariotaku.sqliteqb.library.OrderBy;
import org.mariotaku.sqliteqb.library.SQLLang;
import org.mariotaku.sqliteqb.library.SQLQuery;
import org.mariotaku.sqliteqb.library.SQLQueryException;
import org.mariotaku.sqliteqb.library.Selectable;

import java.util.ArrayList;
import java.util.List;

public class SQLSelectQuery implements SQLQuery, Selectable {

    private final List<InternalQuery> internalQueries = new ArrayList<>();

    private InternalQuery currentInternalQuery;
    private OrderBy orderBy;
    private Integer limit = null, offset = null;

    SQLSelectQuery() {
        initCurrentQuery();
    }

    @Override
    public String getSQL() {
        final StringBuilder sb = new StringBuilder();
        final int size = internalQueries.size();
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sb.append("UNION ");
            }
            final InternalQuery query = internalQueries.get(i);
            sb.append(query.getSQL());
        }
        if (orderBy != null) {
            sb.append(String.format("ORDER BY %s ", orderBy.getSQL()));
        }
        if (limit != null) {
            sb.append(String.format("LIMIT %s ", limit));
            if (offset != null) {
                sb.append(String.format("OFFSET %s ", offset));
            }
        }
        return sb.toString();
    }

    private void initCurrentQuery() {
        currentInternalQuery = new InternalQuery();
        internalQueries.add(currentInternalQuery);
    }

    void setDistinct(final boolean distinct) {
        currentInternalQuery.setDistinct(distinct);
    }

    void setFrom(final Selectable from) {
        currentInternalQuery.setFrom(from);
    }

    void setGroupBy(final Selectable groupBy) {
        currentInternalQuery.setGroupBy(groupBy);
    }

    void setHaving(final Expression having) {
        currentInternalQuery.setHaving(having);
    }

    void setJoin(final Join join) {
        currentInternalQuery.setJoin(join);
    }

    void setLimit(final int limit) {
        this.limit = limit;
    }

    void setOffset(final int offset) {
        this.offset = offset;
    }

    void setOrderBy(final OrderBy orderBy) {
        this.orderBy = orderBy;
    }

    void setSelect(final Selectable select) {
        currentInternalQuery.setSelect(select);
    }

    void setWhere(final Expression where) {
        currentInternalQuery.setWhere(where);
    }

    void union() {
        initCurrentQuery();
    }

    public static final class Builder implements IBuilder<SQLSelectQuery> {
        private final SQLSelectQuery query = new SQLSelectQuery();
        private boolean buildCalled;

        @Override
        public SQLSelectQuery build() {
            buildCalled = true;
            return query;
        }

        @Override
        public String buildSQL() {
            return build().getSQL();
        }

        public Builder from(final Selectable from) {
            checkNotBuilt();
            query.setFrom(from);
            return this;
        }

        public Builder groupBy(final Selectable groupBy) {
            checkNotBuilt();
            query.setGroupBy(groupBy);
            return this;
        }

        public Builder having(final Expression having) {
            checkNotBuilt();
            query.setHaving(having);
            return this;
        }


        public Builder limit(final int limit) {
            checkNotBuilt();
            query.setLimit(limit);
            return this;
        }

        public Builder join(final Join join) {
            checkNotBuilt();
            query.setJoin(join);
            return this;
        }

        public Builder offset(final int offset) {
            query.setOffset(offset);
            return this;
        }

        public Builder orderBy(final OrderBy orderBy) {
            checkNotBuilt();
            query.setOrderBy(orderBy);
            return this;
        }

        public Builder select(final boolean distinct, final Selectable select) {
            checkNotBuilt();
            query.setSelect(select);
            query.setDistinct(distinct);
            return this;
        }

        public Builder select(final Selectable select) {
            checkNotBuilt();
            select(false, select);
            return this;
        }

        public Builder union() {
            checkNotBuilt();
            query.union();
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

    private static class InternalQuery implements SQLLang {

        private boolean distinct;
        private Selectable select, from, groupBy;
        private Expression where, having;
        private Join join;

        @Override
        public String getSQL() {
            if (select == null) throw new SQLQueryException("selectable is null");
            final StringBuilder sb = new StringBuilder("SELECT ");
            if (distinct) {
                sb.append("DISTINCT ");
            }
            sb.append(String.format("%s ", select.getSQL()));
            if (!(select instanceof SQLSelectQuery) && from == null)
                throw new SQLQueryException("FROM not specified");
            else if (from != null) {
                if (from instanceof SQLSelectQuery) {
                    sb.append(String.format("FROM (%s) ", from.getSQL()));
                } else {
                    sb.append(String.format("FROM %s ", from.getSQL()));
                }
            }
            if (join != null) {
                sb.append(String.format("%s ", join.getSQL()));
            }
            if (where != null) {
                sb.append(String.format("WHERE %s ", where.getSQL()));
            }
            if (groupBy != null) {
                sb.append(String.format("GROUP BY %s ", groupBy.getSQL()));
                if (having != null) {
                    sb.append(String.format("HAVING %s ", having.getSQL()));
                }
            }
            return sb.toString();
        }

        void setJoin(final Join join) {
            this.join = join;
        }

        void setDistinct(final boolean distinct) {
            this.distinct = distinct;
        }

        void setFrom(final Selectable from) {
            this.from = from;
        }

        void setGroupBy(final Selectable groupBy) {
            this.groupBy = groupBy;
        }

        void setHaving(final Expression having) {
            this.having = having;
        }

        void setSelect(final Selectable select) {
            this.select = select;
        }

        void setWhere(final Expression where) {
            this.where = where;
        }
    }
}
