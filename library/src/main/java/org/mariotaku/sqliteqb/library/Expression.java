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
        return new Expression(l.getSQL() + " = " + r.getSQL());
    }

    public static Expression equals(final Column l, final Selectable r) {
        return new Expression(l.getSQL() + " = (" + r.getSQL() + ")");
    }

    public static Expression equals(final String l, final Selectable r) {
        return new Expression(l + " = (" + r.getSQL() + ")");
    }

    public static Expression equals(final Column l, final Number r) {
        return new Expression(l.getSQL() + " = " + r);
    }

    public static Expression equals(final Column l, final String r) {
        return new Expression(l.getSQL() + " = " + r);
    }

    public static Expression equals(final String l, final Number r) {
        return new Expression(l + " = " + r);
    }

    public static Expression greaterThan(final String l, final Number r) {
        return new Expression(l + " > " + r);
    }

    public static Expression greaterThan(final SQLLang l, final SQLLang r) {
        return new Expression(l.getSQL() + " > (" + r.getSQL() + ")");
    }

    public static Expression greaterEquals(final String l, final Number r) {
        return new Expression(l + " >= " + r);
    }

    public static Expression lesserEquals(final String l, final Number r) {
        return new Expression(l + " <= " + r);
    }

    public static Expression lesserThan(final String l, final Number r) {
        return new Expression(l + " < " + r);
    }

    public static Expression lesserThan(final SQLLang l, final SQLLang r) {
        return new Expression(l.getSQL() + " < (" + r.getSQL() + ")");
    }

    public static Expression in(final Column column, final Selectable in) {
        return new Expression(column.getSQL() + " IN(" + in.getSQL() + ")");
    }

    public static Expression notEquals(final String l, final Number r) {
        return new Expression(l + " != " + r);
    }

    public static Expression notEquals(final String l, final String r) {
        return new Expression(l + " != " + r);
    }

    public static Expression isNot(final String l, final String r) {
        return new Expression(l + " IS NOT " + r);
    }

    public static Expression isNot(final String l, final Number r) {
        return new Expression(l + " IS NOT " + r);
    }

    public static Expression isNotArgs(final String l) {
        return new Expression(l + " IS NOT ?");
    }

    public static Expression notIn(final Column column, final Selectable in) {
        return new Expression(column.getSQL() + " NOT IN(" + in.getSQL() + ")");
    }

    public static Expression notNull(final Column column) {
        return new Expression(column.getSQL() + " NOT NULL");
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
                builder.append(" ");
                builder.append(token);
                builder.append(" ");
            }
            builder.append(array[i].getSQL());
        }
        builder.append(')');
        return builder.toString();
    }

    public static Expression equalsArgs(String l) {
        return new Expression(l + " = ?");
    }

    public static Expression inArgs(String l, int argsSize) {
        return inArgs(new Column(l), argsSize);
    }

    public static Expression inArgs(Column c, int argsSize) {
        return in(c, new ArgsArray(argsSize));
    }

    public static Expression isNull(Column column) {
        return new Expression(column.getSQL() + " IS NULL");
    }

    public static Expression greaterThan(Column column1, Column column2) {
        return new Expression(column1.getSQL() + " > " + column2.getSQL());
    }

    public static Expression likeRaw(final Column column, final String pattern, final String escape) {
        return new Expression(column.getSQL() + " LIKE " + pattern + " ESCAPE '" + escape + "'");
    }


    public static Expression like(final Column column, final SQLLang expression) {
        return new Expression(column.getSQL() + " LIKE " + expression.getSQL());
    }


    public static Expression likeRaw(final Column column, final String pattern) {
        return new Expression(column.getSQL() + " LIKE " + pattern);
    }


    @Override
    public String getSQL() {
        return expr;
    }
}
