package com.hyhomelab.jexpression.utils;

import junit.framework.TestCase;

/**
 * @author hyhomelab
 * @email hyhomelab@hotmail.com
 * @date 2024/12/31 17:31
 */
public class WeakCacheTest extends TestCase {

    public void testGet() throws InterruptedException {
        var cache = new WeakCache<String>(1, 1000);
        cache.put("key", "abc");
        assertEquals("abc", cache.get("key"));
        Thread.sleep(2000);
        assertNull(cache.get("key"));
    }
}