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

public class SQLFunctions {

    public static String SUM(final String val) {
        return "SUM (" + val + ")";
    }

    public static SQLFunction SUM(final Selectable val) {
        return new SQLFunction("SUM (" + val.getSQL() + ")");
    }

    public static String MAX(final String val) {
        return "MAX (" + val + ")";
    }

    public static SQLFunction MAX(final Selectable val) {
        return new SQLFunction("MAX (" + val.getSQL() + ")");
    }

    public static String MIN(final String val) {
        return "MIN (" + val + ")";
    }

    public static SQLFunction MIN(final Selectable val) {
        return new SQLFunction("MIN (" + val.getSQL() + ")");
    }

    public static String COUNT() {
        return COUNT("*");
    }

    public static String COUNT(final String val) {
        return "COUNT (" + val + ")";
    }

}
