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

import java.util.Locale;

/**
 * Created by mariotaku on 14-8-7.
 */
public class SetValue implements SQLLang {

    private final Columns.Column column;
    private final SQLLang expression;

    public SetValue(Columns.Column column, SQLLang expression) {
        this.column = column;
        this.expression = expression;
    }

    public SetValue(String column, SQLLang expression) {
        this(new Columns.Column(column), expression);
    }


    @Override
    public String getSQL() {
        return String.format(Locale.ROOT, "%s = %s", column.getSQL(), expression.getSQL());
    }
}
