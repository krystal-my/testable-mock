package com.alibaba.testable.demo;

import com.alibaba.testable.core.accessor.PrivateAccessor;
import com.alibaba.testable.processor.annotation.EnablePrivateAccess;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@EnablePrivateAccess
class DemoPrivateAccessTest {

    private DemoPrivateAccess demoPrivateAccess = new DemoPrivateAccess();

    @Test
    void should_able_to_access_private_method() throws Exception {
        assertEquals("hello - 1", demoPrivateAccess.privateFunc("hello", 1));
        assertEquals("hello - 1", PrivateAccessor.invoke(demoPrivateAccess, "privateFunc", "hello", 1));
    }

    @Test
    void should_able_to_access_private_field() throws Exception {
        demoPrivateAccess.count = 2;
        assertEquals("4", demoPrivateAccess.privateFieldAccessFunc());
        assertEquals(new Integer(4), demoPrivateAccess.count);

        PrivateAccessor.set(demoPrivateAccess, "count", 3);
        assertEquals("5", demoPrivateAccess.privateFieldAccessFunc());
        assertEquals(new Integer(5), PrivateAccessor.get(demoPrivateAccess, "count"));
    }

    @Test
    void should_able_to_access_private_static_method() throws Exception {
        //assertEquals("hello + 1", DemoPrivateAccess.privateStaticFunc("hello", 1));
        assertEquals("hello + 1", PrivateAccessor.invokeStatic(DemoPrivateAccess.class, "privateStaticFunc", "hello", 1));
    }

    @Test
    void should_able_to_access_private_static_field() throws Exception {
        //DemoPrivateAccess.staticCount = 2;
        //assertEquals("5", DemoPrivateAccess.privateStaticFieldAccessFunc());
        //assertEquals(new Integer(5), DemoPrivateAccess.staticCount);

        PrivateAccessor.setStatic(DemoPrivateAccess.class, "staticCount", 3);
        assertEquals("6", DemoPrivateAccess.privateStaticFieldAccessFunc());
        assertEquals(new Integer(6), PrivateAccessor.getStatic(DemoPrivateAccess.class, "staticCount"));
    }

    @Test
    void should_able_to_update_final_field() throws Exception {
        demoPrivateAccess.pi = 4.13;
        assertEquals(4.13, demoPrivateAccess.pi);
    }

}