package org.mariotaku.sqliteqb.library.query;

public class SQLDropTriggerQuery extends SQLDropQuery {

    public SQLDropTriggerQuery(final boolean dropIfExists, final String table) {
        super(dropIfExists, "TRIGGER", table);
    }

}
