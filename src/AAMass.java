import org.junit.Test;

import java.util.Collections;
import java.util.Hashtable;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class AAMass {
    static final float H2O = 18.0106f;
    static final float N3H = 17.0265f;
    static double[] aaMassArray = {57.02146, 71.03711, 87.03203, 97.05276, 99.06841, 101.04768, 103.00919, 113.08406,
            114.04293, 115.02695, 128.05858, 128.09496, 129.04259, 131.0405, 137.05891, 147.0354, 147.0684, 156.1011, 160.03065, 161.0192, 163.06332, 186.07932};
    static String[] aaArray = {"G", "A", "S", "P", "V", "T", "C", "I|L", "N", "Nmod|D", "Q", "K", "E|Qmod",
            "M", "H", "Mmod", "F", "R", "Cmod", "Cmod58", "Y", "W"};
    static final float MASS_TOLERANCE = 0.1f;

    static final Hashtable<String, Float> AA_MASS_TABLE = new Hashtable<String, Float>() {
        {
            put("A", 71.03711f);
            put("R", 156.10111f);
            put("N", 114.04293f);
            put("Nmod", 115.02695f);
            put("D", 115.02694f);
            put("C", 103.00919f);
            put("Cmod", 160.03065f); // C(+57.02f)
            put("Cmod58", 161.01919f); // C(+58.01f) // orbi
            put("E", 129.04259f);
            put("Q", 128.05858f);
            put("Qmod", 129.0426f);
            put("G", 57.02146f);
            put("H", 137.05891f);
            put("I", 113.08406f);
            put("L", 113.08406f);
            put("K", 128.09496f);
            put("M", 131.04049f);
            put("Mmod", 147.0354f);
            put("F", 147.06841f);
            put("P", 97.05276f);
            put("S", 87.03203f);
            put("T", 101.04768f);
            put("W", 186.07931f);
            put("Y", 163.06333f);
            put("V", 99.06841f);
        }
    };

    static final Hashtable<Float, String> AA_REVERSE_MASS = new Hashtable<Float, String>() {
        {
            put(129.0426f, "E|Qmod");
            put(163.06332f, "Y");
            put(186.07932f, "W");
            put(99.06841f, "V");
            put(101.04768f, "T");
            put(87.03203f, "S");
            put(156.1011f, "R");
            put(128.05858f, "Q");
            put(97.05276f, "P");
            put(114.04293f, "N");
            put(131.0405f, "M");
            put(113.08406f, "L|I");
            put(128.09496f, "K");
            put(147.0354f, "Mmod");
            put(137.05891f, "H");
            put(57.02146f, "G");
            put(147.0684f, "F");
            put(161.0192f, "Cmod58");
            put(115.02694f, "D|Nmod");
            put(103.00919f, "C");
            put(71.03711f, "A");
            put(160.03065f, "Cmod");
        }
    };


/*
    //printReverseMassTable()
    // Print the mass list ascendingly
    public static void main(String[] args) {
        ArrayList<Float> AAs = new ArrayList<Float>(AA_MASS_TABLE.values());
        Collections.sort(AAs);
        for (double aa : AAs) {
            System.out.println(AA_REVERSE_MASS.get(aa));
        }



    }
*/

    /**
     * Look up a mass in the mass table. If there is a match, return the
     * corresponding AA.
     * @param mass
     * @return
     */
    static public String lookUpMassTable(float mass) {
        int matchIndex = binarySearch(0, aaMassArray.length -1 , mass);
        if (matchIndex < 0) {
            return null;
        } else {
            return aaArray[matchIndex];
        }
    }

    /**
     * If mass is close to a AA mass within a tolerance, return the AA index.
     * @param mass
     * @return
     */
    static private int binarySearch(int start, int end, double mass) {
        if (start > end) {
            return -1;
        }
        int index = (start + end) / 2;

        if (Math.abs(mass - aaMassArray[index]) < MASS_TOLERANCE) {
            return index;
        } else if (mass < aaMassArray[index]) {
            return binarySearch(start, index - 1, mass);
        } else {
            return binarySearch(index + 1, end, mass);
        }
    }

    @Test
    public void testBinarySearch() {
        int aaArrayEnd = aaMassArray.length - 1;
        double mass1 = 57.02146;
        String AA1 = "G";
        assertEquals(AA1, aaArray[binarySearch(0, aaArrayEnd, mass1)]);

        double mass2 = 186.07932;
        String AA2 = "W";
        assertEquals(AA2, aaArray[binarySearch(0, aaArrayEnd, mass2)]);

        double mass3 = 160.03065;
        String AA3 = "Cmod";
        assertEquals(AA3, aaArray[binarySearch(0, aaArrayEnd, mass3)]);

        double mass4 = 25;
        assertEquals(-1, binarySearch(0, aaArrayEnd, mass4));
    }
}
