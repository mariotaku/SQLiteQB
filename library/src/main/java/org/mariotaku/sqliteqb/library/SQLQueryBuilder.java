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

package org.mariotaku.sqliteqb.library;

import org.mariotaku.sqliteqb.library.query.*;

public class SQLQueryBuilder {

    private SQLQueryBuilder() {
        throw new AssertionError("You can't create instance for this class");
    }

    public static SQLAlterTableQuery.Builder alterTable(final String table) {
        return new SQLAlterTableQuery.Builder().alterTable(table);
    }

    public static SQLCreateTableQuery.Builder createTable(final boolean temporary, final boolean createIfNotExists,
                                                          final String name) {
        return new SQLCreateTableQuery.Builder().createTable(temporary, createIfNotExists, name);
    }

    public static SQLCreateTableQuery.Builder createTable(final boolean createIfNotExists, final String name) {
        return createTable(false, createIfNotExists, name);
    }

    public static SQLCreateTableQuery.Builder createTable(final String name) {
        return createTable(false, false, name);
    }

    public static SQLCreateViewQuery.Builder createView(final boolean temporary, final boolean createIfNotExists,
                                                        final String name) {
        return new SQLCreateViewQuery.Builder().createView(temporary, createIfNotExists, name);
    }

    public static SQLCreateIndexQuery.Builder createIndex(final boolean unique, final boolean createIfNotExists) {
        return new SQLCreateIndexQuery.Builder().createIndex(unique, createIfNotExists);
    }


    public static SQLCreateTriggerQuery.Builder createTrigger(final boolean temporary, final boolean createIfNotExists,
                                                              final String name) {
        return new SQLCreateTriggerQuery.Builder().createTrigger(temporary, createIfNotExists, name);
    }

    public static SQLCreateViewQuery.Builder createView(final boolean createIfNotExists, final String name) {
        return createView(false, createIfNotExists, name);
    }

    public static SQLCreateViewQuery.Builder createView(final String name) {
        return createView(false, false, name);
    }

    public static SQLDeleteQuery.Builder deleteFrom(Table table) {
        return new SQLDeleteQuery.Builder().from(table);
    }

    public static SQLDropTableQuery dropTable(final boolean dropIfExists, final String table) {
        return new SQLDropTableQuery(dropIfExists, table);
    }

    public static SQLDropViewQuery dropView(final boolean dropIfExists, final String table) {
        return new SQLDropViewQuery(dropIfExists, table);
    }

    public static SQLDropTriggerQuery dropTrigger(final boolean dropIfExists, final String table) {
        return new SQLDropTriggerQuery(dropIfExists, table);
    }

    public static SQLInsertQuery.Builder insertInto(final OnConflict onConflict, final String table) {
        return new SQLInsertQuery.Builder().insertInto(onConflict, table);
    }

    public static SQLUpdateQuery.Builder update(final OnConflict onConflict, final Table table) {
        return new SQLUpdateQuery.Builder().update(onConflict, table);
    }

    public static SQLUpdateQuery.Builder update(final OnConflict onConflict, final String table) {
        return update(onConflict, new Table(table));
    }

    public static SQLInsertQuery.Builder insertInto(final String table) {
        return insertInto(null, table);
    }

    public static SQLSelectQuery.Builder select(final boolean distinct, final Selectable select) {
        return new SQLSelectQuery.Builder().select(distinct, select);
    }

    public static SQLSelectQuery.Builder select(final Selectable select) {
        return select(false, select);
    }

    public static SQLWithSelectQuery.Builder with(String name, Selectable as) {
        return new SQLWithSelectQuery.Builder().with(name, as);
    }
}
