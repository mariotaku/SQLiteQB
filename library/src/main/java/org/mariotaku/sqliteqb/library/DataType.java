package org.mariotaku.sqliteqb.library;

/**
 * Created by mariotaku on 15/7/16.
 */
public abstract class DataType implements SQLLang {

    public static final String NULL = NULL().getSQL();
    public static final String REAL = REAL().getSQL();
    public static final String INTEGER = INTEGER().getSQL();
    public static final String TEXT = TEXT().getSQL();
    public static final String BLOB = BLOB().getSQL();

    public static DataType NULL() {
        return new DataType() {
            @Override
            public String getSQL() {
                return "NULL";
            }
        };
    }

    public static DataType REAL() {
        return new DataType() {
            @Override
            public String getSQL() {
                return "REAL";
            }
        };
    }

    public static DataType INTEGER() {
        return new DataType() {
            @Override
            public String getSQL() {
                return "INTEGER";
            }
        };
    }

    public static DataType TEXT() {
        return new DataType() {
            @Override
            public String getSQL() {
                return "TEXT";
            }
        };
    }

    public static DataType BLOB() {
        return new DataType() {
            @Override
            public String getSQL() {
                return "BLOB";
            }
        };
    }


}
