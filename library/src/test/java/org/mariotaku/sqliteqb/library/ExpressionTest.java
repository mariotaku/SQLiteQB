package org.mariotaku.sqliteqb.library;

import org.junit.Before;
import org.junit.Test;
import org.mariotaku.sqliteqb.library.Columns.Column;

import static org.junit.Assert.assertEquals;

/**
 * Created by mariotaku on 16/2/3.
 */
public class ExpressionTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testAnd() throws Exception {

    }

    @Test
    public void testEquals() throws Exception {
        assertEquals(Expression.equals(new Column("a"), new Column("b")).getSQL(), "a = b");
    }

    @Test
    public void testEquals1() throws Exception {
        assertEquals(Expression.equals(new Column("a"), new Table("b")).getSQL(), "a = (b)");
    }

    @Test
    public void testEquals2() throws Exception {
        assertEquals(Expression.equals("a", new Table("b")).getSQL(), "a = (b)");
    }

    @Test
    public void testEquals3() throws Exception {

    }

    @Test
    public void testEquals4() throws Exception {

    }

    @Test
    public void testEquals5() throws Exception {

    }

    @Test
    public void testGreaterThan() throws Exception {

    }

    @Test
    public void testGreaterEquals() throws Exception {

    }

    @Test
    public void testLesserEquals() throws Exception {

    }

    @Test
    public void testLesserThan() throws Exception {

    }

    @Test
    public void testIn() throws Exception {

    }

    @Test
    public void testNotEquals() throws Exception {

    }

    @Test
    public void testNotEquals1() throws Exception {

    }

    @Test
    public void testIsNot() throws Exception {

    }

    @Test
    public void testIsNot1() throws Exception {

    }

    @Test
    public void testIsNotArgs() throws Exception {

    }

    @Test
    public void testNotIn() throws Exception {

    }

    @Test
    public void testNotNull() throws Exception {

    }

    @Test
    public void testOr() throws Exception {

    }

    @Test
    public void testEqualsArgs() throws Exception {

    }

    @Test
    public void testInArgs() throws Exception {

    }

    @Test
    public void testInArgs1() throws Exception {

    }

    @Test
    public void testIsNull() throws Exception {

    }

    @Test
    public void testGreaterThan1() throws Exception {

    }

    @Test
    public void testLikeRaw() throws Exception {

    }

    @Test
    public void testLike() throws Exception {

    }

    @Test
    public void testLikeRaw1() throws Exception {

    }
}