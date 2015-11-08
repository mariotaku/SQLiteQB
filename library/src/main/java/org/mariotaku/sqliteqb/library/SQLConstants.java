package org.mariotaku.sqliteqb.library;

/**
 * Created by mariotaku on 15/11/8.
 */
public class SQLConstants {

    public SQLLang NULL() {
        return new RawSQLLang("NULL");
    }

    public SQLLang EMPTY() {
        return new RawSQLLang("''");
    }
}
