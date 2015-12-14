package org.mariotaku.sqliteqb.sample;

import org.mariotaku.sqliteqb.library.Columns;
import org.mariotaku.sqliteqb.library.SQLQueryBuilder;
import org.mariotaku.sqliteqb.library.Table;
import org.mariotaku.sqliteqb.library.query.SQLWithSelectQuery;

/**
 * Created by mariotaku on 15/12/14.
 */
public class Main {
    public static void main(String[] args) {
        SQLWithSelectQuery.Builder builder = SQLQueryBuilder.with("filtered_ids",
                SQLQueryBuilder.select(new Columns.Column("user_id"))
                        .from(new Table("filtered_users"))
                        .build());
        builder.select(new Columns.AllColumn());
        builder.from(new Table("test1"));
        final String sql = builder.buildSQL();
        System.out.println(sql);
    }
}
