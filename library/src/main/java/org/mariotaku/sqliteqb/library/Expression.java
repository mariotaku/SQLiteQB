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

import org.mariotaku.sqliteqb.library.Columns.Column;

public class Expression implements SQLLang {
    private final String expr;

    public Expression(final String expr) {
        this.expr = expr;
    }

    public Expression(SQLLang lang) {
        this(lang.getSQL());
    }

    public static Expression and(final Expression... expressions) {
        return new Expression(toExpr(expressions, "AND"));
    }

    public static Expression equals(final Column l, final Column r) {
        return new Expression(Utils.format("%s = %s", l.getSQL(), r.getSQL()));
    }

    public static Expression equals(final Column l, final Selectable r) {
        return new Expression(Utils.format("%s = (%s)", l.getSQL(), r.getSQL()));
    }

    public static Expression equals(final String l, final Selectable r) {
        return new Expression(Utils.format("%s = (%s)", l, r.getSQL()));
    }

    public static Expression equals(final Column l, final Number r) {
        return new Expression(Utils.format("%s = %d", l.getSQL(), r));
    }

    public static Expression equals(final Column l, final String r) {
        return new Expression(Utils.format("%s = '%s'", l.getSQL(), r));
    }

    public static Expression equals(final String l, final Number r) {
        return new Expression(Utils.format("%s = %d", l, r));
    }

    public static Expression greaterThan(final String l, final Number r) {
        return new Expression(Utils.format("%s > %d", l, r));
    }


    public static Expression greaterEquals(final String l, final Number r) {
        return new Expression(Utils.format("%s >= %d", l, r));
    }

    public static Expression lesserEquals(final String l, final Number r) {
        return new Expression(Utils.format("%s <= %d", l, r));
    }

    public static Expression lesserThan(final String l, final Number r) {
        return new Expression(Utils.format("%s < %d", l, r));
    }

    public static Expression in(final Column column, final Selectable in) {
        return new Expression(String.format("%s IN(%s)", column.getSQL(), in.getSQL()));
    }

    public static Expression notEquals(final String l, final Number r) {
        return new Expression(Utils.format("%s != %d", l, r));
    }

    public static Expression notEquals(final String l, final String r) {
        return new Expression(String.format("%s != %s", l, r));
    }

    public static Expression isNot(final String l, final String r) {
        return new Expression(String.format("%s IS NOT %s", l, r));
    }

    public static Expression isNot(final String l, final Number r) {
        return new Expression(Utils.format("%s IS NOT %d", l, r));
    }

    public static Expression notIn(final Column column, final Selectable in) {
        return new Expression(String.format("%s NOT IN(%s)", column.getSQL(), in.getSQL()));
    }

    public static Expression notNull(final Column column) {
        return new Expression(String.format("%s NOT NULL", column.getSQL()));
    }

    public static Expression or(final Expression... expressions) {
        return new Expression(toExpr(expressions, "OR"));
    }

    private static String toExpr(final Expression[] array, final String token) {
        final StringBuilder builder = new StringBuilder();
        builder.append('(');
        final int length = array.length;
        for (int i = 0; i < length; i++) {
            if (i > 0) {
                builder.append(String.format(" %s ", token));
            }
            builder.append(array[i].getSQL());
        }
        builder.append(')');
        return builder.toString();
    }

    public static Expression equalsArgs(String l) {
        return new Expression(Utils.format("%s = ?", l));
    }

    public static Expression inArgs(String l, int argsSize) {
        return inArgs(new Column(l), argsSize);
    }

    public static Expression inArgs(Column c, int argsSize) {
        return in(c, new ArgsArray(argsSize));
    }

    public static Expression isNull(Column column) {
        return new Expression(Utils.format("%s IS NULL", column.getSQL()));
    }

    public static Expression greaterThan(Column column1, Column column2) {
        return new Expression(Utils.format("%s > %s", column1.getSQL(), column2.getSQL()));
    }

    public static Expression likeRaw(final Column column, final String pattern, final String escape) {
        return new Expression(Utils.format("%s LIKE %s ESCAPE '%s'", column.getSQL(), pattern, escape));
    }


    public static Expression like(final Column column, final SQLLang expression) {
        return new Expression(Utils.format("%s LIKE %s", column.getSQL(), expression.getSQL()));
    }


    public static Expression likeRaw(final Column column, final String pattern) {
        return new Expression(Utils.format("%s LIKE %s", column.getSQL(), pattern));
    }


    @Override
    public String getSQL() {
        return expr;
    }
}
