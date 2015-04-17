import static org.junit.Assert.assertEquals;

import java.awt.Color;

import org.junit.Before;
import org.junit.Test;


public class SeamCarverTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testEnergy() {
        Picture im = new Picture(3, 4);
        im.set(0, 0, new Color(255, 101, 51)); im.set(1, 0, new Color(255, 101, 153)); im.set(2, 0, new Color(255, 101, 255));
        im.set(0, 1, new Color(255, 153, 51)); im.set(1, 1, new Color(255, 153, 153)); im.set(2, 1, new Color(255, 153, 255));
        im.set(0, 2, new Color(255, 203, 51)); im.set(1, 2, new Color(255, 204, 153)); im.set(2, 2, new Color(255, 205, 255));
        im.set(0, 3, new Color(255, 255, 51)); im.set(1, 3, new Color(255, 255, 153)); im.set(2, 3, new Color(255, 255, 255));

        SeamCarver carver = new SeamCarver(im);

        assertEquals(52225, (int) carver.energy(1, 1));
        assertEquals(52024, (int) carver.energy(1, 2));
        assertEquals(195075, (int) carver.energy(1, 0));
        assertEquals(195075, (int) carver.energy(0, 1));
        assertEquals(195075, (int) carver.energy(1, 3));
    }

}
