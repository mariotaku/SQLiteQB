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


public class OrderBy implements SQLLang {

    private final String[] orderBy;
    private final boolean[] ascending;

    public OrderBy(final String[] orderBy, final boolean[] ascending) {
        this.orderBy = orderBy;
        this.ascending = ascending;
    }

    public OrderBy(final String... orderBy) {
        this(orderBy, null);
    }

    public OrderBy(final String orderBy, final boolean ascending) {
        this.orderBy = new String[]{orderBy};
        this.ascending = new boolean[]{ascending};
    }

    @Override
    public String getSQL() {
        final StringBuilder sb = new StringBuilder();
        for (int i = 0, j = orderBy.length; i < j; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(orderBy[i]);
            if (ascending != null) {
                sb.append(ascending[i] ? " ASC" : " DESC");
            }
        }
        return sb.toString();
    }

}
