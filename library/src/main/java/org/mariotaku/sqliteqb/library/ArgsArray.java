package org.mariotaku.sqliteqb.library;

/**
 * Created by mariotaku on 15/8/12.
 */
public class ArgsArray implements Selectable {
    private final String sql;

    public ArgsArray(int size) {
        sql = Utils.toStringForSQL(size);
    }

    @Override
    public String getSQL() {
        return sql;
    }
}
