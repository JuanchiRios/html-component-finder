package finder;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

public class MapComparatorTest {
    private MapComparator comparator;
    private HashMap<Integer, String> controlMap;
    private static final Float DELTA = 0.00001f;

    @Before
    public void setUp(){
        this.comparator = new MapComparator();
        this.controlMap = new HashMap<Integer, String>() {
            {
                put(1, "a");
                put(2, "b");
                put(3, "c");
            }
        };

    }

    @Test
    public void comparingSameMapShouldReturn100() {
        HashMap<Integer, String> testmap = new HashMap<Integer, String>() {
            {
                put(1, "a");
                put(2, "b");
                put(3, "c");
            }
        };


        Assert.assertEquals(100, comparator.compareMap(this.controlMap, testmap), DELTA);

    }


    @Test
    public void compareEmptyMapShouldReturn100percentage() {
        Assert.assertEquals(100, comparator.compareMap(new HashMap<>(), new HashMap<>()), DELTA);

    }

    @Test
    public void comparingMapTwoThirds() {
        HashMap<Integer, String> testmap = new HashMap<Integer, String>() {
            {
                put(1, "a");
                put(2, "b");
            }
        };


        Assert.assertEquals(100*2/3f, comparator.compareMap(this.controlMap, testmap), DELTA);

    }


    @Test
    public void comparingMapTwoThirdsAreCommutative() {
        HashMap<Integer, String> testmap = new HashMap<Integer, String>() {
            {
                put(1, "a");
                put(2, "b");
            }
        };


        Assert.assertEquals(100*2/3f, comparator.compareMap(testmap, this.controlMap), DELTA);

    }

    @Test
    public void comparingMapAllDifferentEntriesShouldReturn0() {
        HashMap<Integer, String> testmap = new HashMap<Integer, String>() {
            {
                put(1, "n");
            }
        };


        Assert.assertEquals(0, comparator.compareMap(this.controlMap, testmap), DELTA);

    }

    @Test
    public void comparingMapWithOnlyOneEqualEntryShouldReturnOneThird() {
        HashMap<Integer, String> testmap = new HashMap<Integer, String>() {
            {
                put(1, "a");
            }
        };


        Assert.assertEquals(100/3f, comparator.compareMap(this.controlMap, testmap), DELTA);

    }

    @Test
    public void comparingMapWithOnlyOneEqualEntryAndOtherDifferentShouldReturnOneThird() {
        HashMap<Integer, String> testmap = new HashMap<Integer, String>() {
            {
                put(1, "a");
                put(2, "z");
            }
        };


        Assert.assertEquals(100/3f, comparator.compareMap(this.controlMap, testmap), DELTA);

    }

    @Test
    public void comparingMapWithOnlySameValuesButWith2ExtrasValuesShouldReturnFifths() {
        HashMap<Integer, String> testmap = new HashMap<Integer, String>() {
            {
                put(1, "a");
                put(2, "b");
                put(3, "c");
                put(4, "f");
                put(5, "h");
            }
        };


        Assert.assertEquals(60, comparator.compareMap(this.controlMap, testmap), DELTA);

    }

}
