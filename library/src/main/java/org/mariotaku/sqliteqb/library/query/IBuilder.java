package org.mariotaku.sqliteqb.library.query;

import org.mariotaku.sqliteqb.library.SQLLang;

public interface IBuilder<T extends SQLLang> {

    public T build();

    /**
     * Equivalent to {@link #build()}.{@link SQLLang#getSQL()}
     *
     * @return
     */
    public String buildSQL();

}
