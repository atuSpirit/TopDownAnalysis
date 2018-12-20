import org.junit.Test;

import java.util.Collections;
import java.util.Hashtable;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class AAMass {
    static final float H2O = 18.0106f;
    static final float N3H = 17.0265f;
    /* the one AA or two AA array and massArray*/
    static double[] aaMassArray = {57.02146, 71.03711, 87.03203, 97.05276, 99.06841, 101.04768, 103.00919, 113.08406, 114.04292, 114.04293, 115.02695, 128.05857, 128.05858, 128.09496, 129.04259, 131.0405, 137.05891, 144.05349, 147.0354, 147.0684, 154.07422, 156.08987, 156.1011, 158.06914, 160.03065, 161.0192, 163.06332, 170.10551999999998, 171.06439, 172.04841, 185.08004, 185.11641999999998, 186.06404999999998, 186.07932, 188.06196, 194.08037, 204.05686, 204.08986, 213.12256, 217.05211, 218.04066, 220.08478, 243.10078, 128.05857, 142.07422, 158.06914, 168.08987000000002, 170.10552, 172.08479, 174.0463, 184.12117, 185.08004, 186.06405999999998, 199.09569, 199.13207, 200.0797, 202.07761, 208.09602, 218.07251000000002, 218.10550999999998, 227.13821000000002, 231.06776000000002, 232.05631, 234.10043000000002, 257.11643, 144.05349, 158.06914, 174.06406, 184.08479, 186.10044, 188.07971, 190.04122, 200.11608999999999, 201.07496, 202.05898000000002, 215.09061000000003, 215.12698999999998, 216.07461999999998, 218.07253000000003, 224.09094, 234.06743, 234.10043000000002, 243.13313, 247.06268, 248.05123000000003, 250.09535, 273.11135, 154.07422, 168.08987000000002, 184.08479, 194.10552, 196.12117, 198.10044, 200.06195000000002, 210.13682, 211.09569, 212.07971, 225.11134, 225.14772, 226.09535, 228.09326000000001, 234.11167, 244.08816000000002, 244.12116, 253.15386, 257.08341, 258.07196, 260.11608, 283.13208, 156.08987, 170.10552, 186.10044, 196.12117, 198.13682, 200.11608999999999, 202.07760000000002, 212.15247, 213.11133999999998, 214.09536, 227.12699, 227.16337, 228.111, 230.10891, 236.12732, 246.10381, 246.13681, 255.16951, 259.09906, 260.08761000000004, 262.13173, 285.14773, 158.06914, 172.08479, 188.07971, 198.10044, 200.11608999999999, 202.09536, 204.05687, 214.13173999999998, 215.09061, 216.07463, 229.10626000000002, 229.14263999999997, 230.09026999999998, 232.08818000000002, 238.10658999999998, 248.08308, 248.11608, 257.14878, 261.07833, 262.06688, 264.111, 287.127, 160.03065, 174.0463, 190.04122, 200.06195000000002, 202.07760000000002, 204.05687, 206.01838, 216.09325, 217.05212, 218.03614, 231.06777, 231.10415, 232.05178, 234.04969, 240.06810000000002, 250.04459000000003, 250.07759, 259.11029, 263.03984, 264.02839, 266.07251, 289.08851, 170.10551999999998, 184.12117, 200.11608999999999, 210.13682, 212.15247, 214.13173999999998, 216.09325, 226.16812, 227.12698999999998, 228.11101, 241.14264, 241.17901999999998, 242.12664999999998, 244.12456, 250.14297, 260.11946, 260.15246, 269.18516, 273.11471, 274.10326, 276.14738, 299.16337999999996, 171.06439, 185.08004, 201.07496, 211.09569, 213.11133999999998, 215.09061, 217.05212, 227.12698999999998, 228.08586, 229.06988, 242.10151000000002, 242.13788999999997, 243.08551999999997, 245.08343000000002, 251.10183999999998, 261.07833, 261.11133, 270.14403, 274.07358, 275.06213, 277.10625, 300.12225, 172.04841, 186.06405999999998, 202.05898000000002, 212.07971, 214.09536, 216.07463, 218.03614, 228.11101, 229.06988, 230.0539, 243.08553, 243.12190999999999, 244.06954, 246.06745, 252.08586, 262.06235000000004, 262.09535, 271.12805000000003, 275.0576, 276.04615, 278.09027000000003, 301.10627, 185.08004, 199.09569, 215.09061000000003, 225.11134, 227.12699, 229.10626000000002, 231.06777, 241.14264, 242.10151000000002, 243.08553, 256.11716, 256.15354, 257.10117, 259.09908, 265.11749, 275.09398, 275.12698, 284.15968, 288.08923000000004, 289.07778, 291.1219, 314.1379, 185.11641999999998, 199.13207, 215.12698999999998, 225.14772, 227.16337, 229.14263999999997, 231.10415, 241.17901999999998, 242.13788999999997, 243.12190999999999, 256.15354, 256.18992, 257.13755, 259.13545999999997, 265.15387, 275.13036, 275.16336, 284.19606, 288.12561, 289.11415999999997, 291.15828, 314.17427999999995, 186.06404999999998, 200.0797, 216.07461999999998, 226.09535, 228.111, 230.09026999999998, 232.05178, 242.12664999999998, 243.08551999999997, 244.06954, 257.10117, 257.13755, 258.08518, 260.08308999999997, 266.1015, 276.07799, 276.11099, 285.14369, 289.07324, 290.06179, 292.10591, 315.12190999999996, 188.06196, 202.07761, 218.07253000000003, 228.09326000000001, 230.10891, 232.08818000000002, 234.04969, 244.12456, 245.08343000000002, 246.06745, 259.09908, 259.13545999999997, 260.08308999999997, 262.081, 268.09941000000003, 278.07590000000005, 278.1089, 287.14160000000004, 291.07115, 292.0597, 294.10382000000004, 317.11982, 194.08037, 208.09602, 224.09094, 234.11167, 236.12732, 238.10658999999998, 240.06810000000002, 250.14297, 251.10183999999998, 252.08586, 265.11749, 265.15387, 266.1015, 268.09941000000003, 274.11782, 284.09431, 284.12730999999997, 293.16001, 297.08956, 298.07811000000004, 300.12223, 323.13823, 204.05686, 218.07251000000002, 234.06743, 244.08816000000002, 246.10381, 248.08308, 250.04459000000003, 260.11946, 261.07833, 262.06235000000004, 275.09398, 275.13036, 276.07799, 278.07590000000005, 284.09431, 294.0708, 294.1038, 303.1365, 307.06605, 308.05460000000005, 310.09872, 333.11472000000003, 204.08986, 218.10550999999998, 234.10043000000002, 244.12116, 246.13681, 248.11608, 250.07759, 260.15246, 261.11133, 262.09535, 275.12698, 275.16336, 276.11099, 278.1089, 284.12730999999997, 294.1038, 294.1368, 303.16949999999997, 307.09905000000003, 308.0876, 310.13172, 333.14772, 213.12256, 227.13821000000002, 243.13313, 253.15386, 255.16951, 257.14878, 259.11029, 269.18516, 270.14403, 271.12805000000003, 284.15968, 284.19606, 285.14369, 287.14160000000004, 293.16001, 303.1365, 303.16949999999997, 312.2022, 316.13175, 317.12030000000004, 319.16442, 342.18042, 217.05211, 231.06776000000002, 247.06268, 257.08341, 259.09906, 261.07833, 263.03984, 273.11471, 274.07358, 275.0576, 288.08923000000004, 288.12561, 289.07324, 291.07115, 297.08956, 307.06605, 307.09905000000003, 316.13175, 320.0613, 321.04985, 323.09397, 346.10997, 218.04066, 232.05631, 248.05123000000003, 258.07196, 260.08761000000004, 262.06688, 264.02839, 274.10326, 275.06213, 276.04615, 289.07778, 289.11415999999997, 290.06179, 292.0597, 298.07811000000004, 308.05460000000005, 308.0876, 317.12030000000004, 321.04985, 322.0384, 324.08252000000005, 347.09852, 220.08478, 234.10043000000002, 250.09535, 260.11608, 262.13173, 264.111, 266.07251, 276.14738, 277.10625, 278.09027000000003, 291.1219, 291.15828, 292.10591, 294.10382000000004, 300.12223, 310.09872, 310.13172, 319.16442, 323.09397, 324.08252000000005, 326.12664, 349.14264000000003, 243.10078, 257.11643, 273.11135, 283.13208, 285.14773, 287.127, 289.08851, 299.16337999999996, 300.12225, 301.10627, 314.1379, 314.17427999999995, 315.12190999999996, 317.11982, 323.13823, 333.11472000000003, 333.14772, 342.18042, 346.10997, 347.09852, 349.14264000000003, 372.15864};
    static String[] aaArray = {"G", "A", "S", "P", "V", "T", "C", "I|L", "GG", "N", "Nmod|D", "GA", "Q", "K", "E|Qmod", "M", "H", "GS", "Mmod", "F", "GP", "GV", "R", "GT", "Cmod|GC", "Cmod58", "Y", "GI|GL", "GN", "GNmod|GD", "GQ", "GK", "GE|GQmod", "W", "GM", "GH", "GMmod", "GF", "GR", "GCmod", "GCmod58", "GY", "GW", "AG", "AA", "AS", "AP", "AV", "AT", "AC", "AI|AL", "AN", "ANmod|AD", "AQ", "AK", "AE|AQmod", "AM", "AH", "AMmod", "AF", "AR", "ACmod", "ACmod58", "AY", "AW", "SG", "SA", "SS", "SP", "SV", "ST", "SC", "SI|SL", "SN", "SNmod|SD", "SQ", "SK", "SE|SQmod", "SM", "SH", "SMmod", "SF", "SR", "SCmod", "SCmod58", "SY", "SW", "PG", "PA", "PS", "PP", "PV", "PT", "PC", "PI|PL", "PN", "PNmod|PD", "PQ", "PK", "PE|PQmod", "PM", "PH", "PMmod", "PF", "PR", "PCmod", "PCmod58", "PY", "PW", "VG", "VA", "VS", "VP", "VV", "VT", "VC", "VI|VL", "VN", "VNmod|VD", "VQ", "VK", "VE|VQmod", "VM", "VH", "VMmod", "VF", "VR", "VCmod", "VCmod58", "VY", "VW", "TG", "TA", "TS", "TP", "TV", "TT", "TC", "TI|TL", "TN", "TNmod|TD", "TQ", "TK", "TE|TQmod", "TM", "TH", "TMmod", "TF", "TR", "TCmod", "TCmod58", "TY", "TW", "CG", "CA", "CS", "CP", "CV", "CT", "CC", "CI|CL", "CN", "CNmod|CD", "CQ", "CK", "CE|CQmod", "CM", "CH", "CMmod", "CF", "CR", "CCmod", "CCmod58", "CY", "CW", "IG|LG", "IA|LA", "IS|LS", "IP|LP", "IV|LV", "IT|LT", "IC|LC", "II|IL|LI|LL", "IN|LN", "INmod|ID|LNmod|LD", "IQ|LQ", "IK|LK", "IE|IQmod|LE|LQmod", "IM|LM", "IH|LH", "IMmod|LMmod", "IF|LF", "IR|LR", "ICmod|LCmod", "ICmod58|LCmod58", "IY|LY", "IW|LW", "NG", "NA", "NS", "NP", "NV", "NT", "NC", "NI|NL", "NN", "NNmod|ND", "NQ", "NK", "NE|NQmod", "NM", "NH", "NMmod", "NF", "NR", "NCmod", "NCmod58", "NY", "NW", "NmodG|DG", "NmodA|DA", "NmodS|DS", "NmodP|DP", "NmodV|DV", "NmodT|DT", "NmodC|DC", "NmodI|NmodL|DI|DL", "NmodN|DN", "NmodNmod|NmodD|DNmod|DD", "NmodQ|DQ", "NmodK|DK", "NmodE|NmodQmod|DE|DQmod", "NmodM|DM", "NmodH|DH", "NmodMmod|DMmod", "NmodF|DF", "NmodR|DR", "NmodCmod|DCmod", "NmodCmod58|DCmod58", "NmodY|DY", "NmodW|DW", "QG", "QA", "QS", "QP", "QV", "QT", "QC", "QI|QL", "QN", "QNmod|QD", "QQ", "QK", "QE|QQmod", "QM", "QH", "QMmod", "QF", "QR", "QCmod", "QCmod58", "QY", "QW", "KG", "KA", "KS", "KP", "KV", "KT", "KC", "KI|KL", "KN", "KNmod|KD", "KQ", "KK", "KE|KQmod", "KM", "KH", "KMmod", "KF", "KR", "KCmod", "KCmod58", "KY", "KW", "EG|QmodG", "EA|QmodA", "ES|QmodS", "EP|QmodP", "EV|QmodV", "ET|QmodT", "EC|QmodC", "EI|EL|QmodI|QmodL", "EN|QmodN", "ENmod|ED|QmodNmod|QmodD", "EQ|QmodQ", "EK|QmodK", "EE|EQmod|QmodE|QmodQmod", "EM|QmodM", "EH|QmodH", "EMmod|QmodMmod", "EF|QmodF", "ER|QmodR", "ECmod|QmodCmod", "ECmod58|QmodCmod58", "EY|QmodY", "EW|QmodW", "MG", "MA", "MS", "MP", "MV", "MT", "MC", "MI|ML", "MN", "MNmod|MD", "MQ", "MK", "ME|MQmod", "MM", "MH", "MMmod", "MF", "MR", "MCmod", "MCmod58", "MY", "MW", "HG", "HA", "HS", "HP", "HV", "HT", "HC", "HI|HL", "HN", "HNmod|HD", "HQ", "HK", "HE|HQmod", "HM", "HH", "HMmod", "HF", "HR", "HCmod", "HCmod58", "HY", "HW", "MmodG", "MmodA", "MmodS", "MmodP", "MmodV", "MmodT", "MmodC", "MmodI|MmodL", "MmodN", "MmodNmod|MmodD", "MmodQ", "MmodK", "MmodE|MmodQmod", "MmodM", "MmodH", "MmodMmod", "MmodF", "MmodR", "MmodCmod", "MmodCmod58", "MmodY", "MmodW", "FG", "FA", "FS", "FP", "FV", "FT", "FC", "FI|FL", "FN", "FNmod|FD", "FQ", "FK", "FE|FQmod", "FM", "FH", "FMmod", "FF", "FR", "FCmod", "FCmod58", "FY", "FW", "RG", "RA", "RS", "RP", "RV", "RT", "RC", "RI|RL", "RN", "RNmod|RD", "RQ", "RK", "RE|RQmod", "RM", "RH", "RMmod", "RF", "RR", "RCmod", "RCmod58", "RY", "RW", "CmodG", "CmodA", "CmodS", "CmodP", "CmodV", "CmodT", "CmodC", "CmodI|CmodL", "CmodN", "CmodNmod|CmodD", "CmodQ", "CmodK", "CmodE|CmodQmod", "CmodM", "CmodH", "CmodMmod", "CmodF", "CmodR", "CmodCmod", "CmodCmod58", "CmodY", "CmodW", "Cmod58G", "Cmod58A", "Cmod58S", "Cmod58P", "Cmod58V", "Cmod58T", "Cmod58C", "Cmod58I|Cmod58L", "Cmod58N", "Cmod58Nmod|Cmod58D", "Cmod58Q", "Cmod58K", "Cmod58E|Cmod58Qmod", "Cmod58M", "Cmod58H", "Cmod58Mmod", "Cmod58F", "Cmod58R", "Cmod58Cmod", "Cmod58Cmod58", "Cmod58Y", "Cmod58W", "YG", "YA", "YS", "YP", "YV", "YT", "YC", "YI|YL", "YN", "YNmod|YD", "YQ", "YK", "YE|YQmod", "YM", "YH", "YMmod", "YF", "YR", "YCmod", "YCmod58", "YY", "YW", "WG", "WA", "WS", "WP", "WV", "WT", "WC", "WI|WL", "WN", "WNmod|WD", "WQ", "WK", "WE|WQmod", "WM", "WH", "WMmod", "WF", "WR", "WCmod", "WCmod58", "WY", "WW"};

/*  The one AA array and massArray
    static double[] aaMassArray = {57.02146, 71.03711, 87.03203, 97.05276, 99.06841, 101.04768, 103.00919, 113.08406,
            114.04293, 115.02695, 128.05858, 128.09496, 129.04259, 131.0405, 137.05891, 147.0354, 147.0684, 156.1011, 160.03065, 161.0192, 163.06332, 186.07932};
    static String[] aaArray = {"G", "A", "S", "P", "V", "T", "C", "I|L", "N", "Nmod|D", "Q", "K", "E|Qmod",
            "M", "H", "Mmod", "F", "R", "Cmod", "Cmod58", "Y", "W"};
           /* */
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

    private static void generateTwoAACombination() {
        int length = aaMassArray.length;
        ArrayList<Double> twoAAMassArray = new ArrayList<>(length * length);
        ArrayList<String> twoAAArray = new ArrayList<>(length * length);
        int index = 0;
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {

                twoAAMassArray.add(aaMassArray[i] + aaMassArray[j]);
                String twoAAs = null;
                //If the aa is a multiple choice, generate combination for AA
                if (aaArray[i].contains("|") || aaArray[j].contains("|")) {
                    String[] firstAAs;
                    String[] secondAAs;
                    ArrayList<String> twoAACombinationList = new ArrayList<>();
                    if (aaArray[i].contains("|")) {
                        firstAAs = aaArray[i].split("\\|");
                    } else {
                        firstAAs = new String[1];
                        firstAAs[0] = aaArray[i];
                    }
                    if (aaArray[j].contains("|")) {
                        secondAAs = aaArray[j].split("\\|");
                    } else {
                        secondAAs = new String[1];
                        secondAAs[0] = aaArray[j];
                    }
                    for (int s = 0; s < firstAAs.length; s++) {
                        for (int t = 0; t < secondAAs.length; t++) {
                            twoAACombinationList.add(firstAAs[s] + secondAAs[t]);
                        }
                    }
                    twoAAs = String.join("|", twoAACombinationList);
                } else {
                    twoAAs = aaArray[i] + aaArray[j];
                }

                twoAAArray.add(twoAAs);
            }
        }
        System.out.println(twoAAArray.toString());
        System.out.println(twoAAMassArray.toString());
        mergeTwoAAsToAAMassArray(twoAAArray, twoAAMassArray);
    }

    private static void mergeTwoAAsToAAMassArray(ArrayList<String> twoAAArray,
                                                 ArrayList<Double> twoAAMassArray) {
        ArrayList<String> newAAArray = new ArrayList<>();
        ArrayList<Double> newAAmassArray = new ArrayList<>();

        int aaLength = aaArray.length;
        int twoAALength = twoAAArray.size();

        int i = 0;
        int j = 0;

        while ((i < aaLength) && (j < twoAALength)) {
            double aaMass = aaMassArray[i];
            double twoAAMass = twoAAMassArray.get(j);

            if (Double.compare(aaMass, twoAAMass) == 0) {
                newAAmassArray.add(aaMass);
                newAAArray.add(aaArray[i] + "|" + twoAAArray.get(j));
                i += 1;
                j += 1;
            } else if (aaMass < twoAAMass) {
                newAAmassArray.add(aaMass);
                newAAArray.add(aaArray[i]);
                i += 1;
            } else {
                newAAmassArray.add(twoAAMass);
                newAAArray.add(twoAAArray.get(j));
                j += 1;
            }
        }

        while (i < aaLength) {
            newAAmassArray.add(aaMassArray[i]);
            newAAArray.add(aaArray[i]);
            i += 1;
        }

        while (j < twoAALength) {
            newAAmassArray.add(twoAAMassArray.get(j));
            newAAArray.add(twoAAArray.get(j));
            j += 1;
        }

        //Print the merged aa mass list
        for (String aa : newAAArray) {
            System.out.print("\"" + aa + "\", ");
        }
        System.out.println();
        System.out.println(newAAmassArray.toString());

    }

    public static void main(String[] args) {
        generateTwoAACombination();
    }


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

    /*
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
    */
}
