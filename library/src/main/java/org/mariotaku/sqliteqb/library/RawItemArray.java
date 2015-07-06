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

public class RawItemArray implements Selectable {

    private final Object[] array;

    public RawItemArray(final long[] array) {
        final Long[] converted = new Long[array.length];
        for (int i = 0, j = array.length; i < j; i++) {
            converted[i] = array[i];
        }
        this.array = converted;
    }

    public RawItemArray(final Object[] array) {
        this.array = array;
    }

    public RawItemArray(final int[] array) {
        final Integer[] converted = new Integer[array.length];
        for (int i = 0, j = array.length; i < j; i++) {
            converted[i] = array[i];
        }
        this.array = converted;
    }

    @Override
    public String getSQL() {
        return Utils.toString(array, ',', true);
    }

}
