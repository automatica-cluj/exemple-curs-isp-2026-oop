package utcluj.aut;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainTest {
    @Test
    public void testAdd() {
        assertEquals(5, Main.add(2, 3));
    }
}
