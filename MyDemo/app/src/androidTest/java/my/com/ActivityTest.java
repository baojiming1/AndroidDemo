package my.com;

import android.test.AndroidTestCase;

/**
 * Created by 鲍建明 on 2015/3/27.
 */
public class ActivityTest extends AndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    public void testString() throws  Exception{
        assertEquals("123", "123");
    }

}
