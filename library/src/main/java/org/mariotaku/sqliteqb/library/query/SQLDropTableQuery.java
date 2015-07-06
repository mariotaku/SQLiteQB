package org.mariotaku.sqliteqb.library.query;

public class SQLDropTableQuery extends SQLDropQuery {

    public SQLDropTableQuery(final boolean dropIfExists, final String table) {
        super(dropIfExists, "TABLE", table);
    }

}
