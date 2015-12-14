package org.mariotaku.sqliteqb.library.query;

import org.mariotaku.sqliteqb.library.SQLLang;
import org.mariotaku.sqliteqb.library.Selectable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mariotaku on 15/12/14.
 */
public class SQLWithSelectQuery extends SQLSelectQuery {

    private List<CTE> ctes = new ArrayList<>();

    @Override
    public String getSQL() {
        StringBuilder sb = new StringBuilder();
        sb.append("WITH ");
        boolean first = true;
        for (CTE cte : ctes) {
            if (!first) {
                sb.append(" ,");
            }
            sb.append(cte.getSQL());
            first = false;
        }
        sb.append(" ");
        sb.append(select.getSQL());
        return sb.toString();
    }

    private SQLSelectQuery select;

    void setSelectQuery(SQLSelectQuery select) {
        this.select = select;
    }

    public static class Builder extends SQLSelectQuery.Builder {

        private final SQLWithSelectQuery internalQuery = new SQLWithSelectQuery();

        @Override
        public SQLWithSelectQuery build() {
            internalQuery.setSelectQuery(super.build());
            return internalQuery;
        }

        @Override
        public String buildSQL() {
            return build().getSQL();
        }

        public Builder with(String name, Selectable as) {
            internalQuery.with(name, as);
            return this;
        }
    }

    private void with(String name, Selectable as) {
        ctes.add(new CTE(name, as));
    }

    static class CTE implements SQLLang {
        private final String name;
        private final Selectable as;

        CTE(String name, Selectable as) {
            this.name = name;
            this.as = as;
        }

        @Override
        public String getSQL() {
            return name + " AS (" + as.getSQL() + ")";
        }
    }

}
