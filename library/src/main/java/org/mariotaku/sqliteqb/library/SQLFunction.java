package org.mariotaku.sqliteqb.library;

/**
 * Created by mariotaku on 16/2/25.
 */
public class SQLFunction implements SQLLang, Selectable {
    private final String sql;

    public SQLFunction(String sql) {
        this.sql = sql;
    }

    @Override
    public String getSQL() {
        return sql;
    }
}
