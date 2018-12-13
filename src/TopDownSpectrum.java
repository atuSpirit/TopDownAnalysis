import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TopDownSpectrum extends Spectrum {
    private static final float NEIBHOUR_THRE = 0.004f;
    List<Peak> valuablePeakList;

    /* Constructor for the spectrum read from mgf file */
    public TopDownSpectrum(int scan, float pepMass, int mslevel, int z, List<Peak> peakList) {
        super(scan, pepMass, mslevel, z, peakList);
        this.valuablePeakList = new ArrayList<Peak>();
    }

    /* Constructor for the spectrum read from mgf file */
    public TopDownSpectrum(int scan, float pepMass, int mslevel, int z, String fragType, List<Peak> peakList) {
        super(scan, pepMass, mslevel, z, fragType, peakList);
        this.valuablePeakList = new ArrayList<Peak>();
    }

    /* Constructor for the spectrum read from msalign file */
    public TopDownSpectrum(int scan, float pepMass, float pepMz, int z,  String fragType, List<Peak> peakList) {
        super(scan, pepMass, pepMz, z, fragType, peakList);
        this.valuablePeakList = new ArrayList<Peak>();
    }


    public List<Peak> getValuablePeakList() {
        return valuablePeakList;
    }

    public void setValuablePeakList(List<Peak> valuablePeakList) {
        this.valuablePeakList = valuablePeakList;
    }

    /**
     * Add auxiliary peaks to the peakList of the spectrum.
     * For CID/HCD spectrum, 0, pepMass, m(H2O), pepMass - m(H2O) are added.
     * For ETD spectrum, 0, pepMass, m(H2O) - m(N3H), pepMass - (m(H2O) - m(N3H)) are added.
     *
     * */
    void addAuxiliaryPeaks() {
        float pepMass = this.getPepMass();
        List<Peak> newPeakList = new ArrayList<>();
        float maxIntensity = this.getMaxIntensity();
        int z = 1;  //Set all auxiliary peak charge to 1

        //Add an auxiliary peak at zero
        newPeakList.add(new Peak(0, maxIntensity, z));


        if (this.getType().equals("CID") || this.getType().equals("HCD")) {
            //Add m(H2O) for CID or HCD spectrum
            newPeakList.add(new Peak(AAMass.H2O, maxIntensity, z));
        } else if (this.getType().equals("ETD")) {
            //Add m(H2O)-m(N3H) and m(N3H) for ETD data
            newPeakList.add(new Peak((AAMass.H2O - AAMass.N3H), maxIntensity, z));
        }

        newPeakList.addAll(this.getPeakList());

        if (this.getType().equals("CID") || this.getType().equals("HCD")) {
            //Add pepMass - m(H2O) for CID or HCD spectrum
            if (pepMass > AAMass.H2O) {
                newPeakList.add(new Peak((pepMass - AAMass.H2O), maxIntensity, z));
            }
        } else if (this.getType().equals("ETD")) {
            //Add pepMass - (m(H2O)-m(N3H)) for ETD data
            if (pepMass > (AAMass.H2O - AAMass.N3H)) {
                newPeakList.add(new Peak((pepMass - AAMass.H2O + AAMass.N3H), maxIntensity, z));
            }
        }

        //Add an auxiliary peak at pepMass
        newPeakList.add(new Peak(pepMass, maxIntensity, z));

        Collections.sort(newPeakList, Peak.getMassComparator());
        this.setPeakList(newPeakList);
    }

    /**
     * Merge nearby peaks into a peak with highest intensity in the peakList of spectrum.
     */
    void mergeClosePeaks() {
        int peakNum = this.peakList.size();
        ArrayList<Peak> mergedPeakList = new ArrayList<>();


        Peak peak = this.peakList.get(0);
        Peak nextPeak;
        ArrayList<Peak> peakCluster = new ArrayList<>();
        int i = 1;
        while (i < peakNum) {
            nextPeak = this.peakList.get(i);
            if (Math.abs(nextPeak.getMonoMass() - peak.getMonoMass()) < NEIBHOUR_THRE) {
                if (nextPeak.getZ() != peak.getZ()) {
                    System.out.println("Warning: close peaks have difference charge!");
                    System.out.println(peak.toString());
                    System.out.println(nextPeak.toString());
                }
                peakCluster.add(peak);
            } else {
                if (peakCluster.size() > 0) {
                    peakCluster.add(peak);
                    mergedPeakList.add(mergePeaks(peakCluster));
                } else {
                    mergedPeakList.add(peak);
                }
                peakCluster = new ArrayList<>();
            }
            peak = nextPeak;

            i += 1;
        }
        if (peakCluster.size() > 0) {
            mergedPeakList.add(mergePeaks(peakCluster));
        } else {
            mergedPeakList.add(peak);
        }

        this.setPeakList(mergedPeakList);
    }

    /**
     * For each m(p) in the peakList, add its complementary peak pepMass(p) - m(p)
     * to the peakList.
     */
    void addReflectingPeaks() {
        ArrayList<Peak> newPeakList = new ArrayList<>();

        for (Peak peak : this.peakList) {
            newPeakList.add(peak);
            float mass = this.pepMass - peak.getMonoMass();
            if (mass >= 0) {
                Peak reflectingPeak = new Peak(mass, peak.getIntensity(), peak.getZ());
                newPeakList.add(reflectingPeak);
            }
        }


        Collections.sort(newPeakList, Peak.getMassComparator());
        this.setPeakList(newPeakList);
        //Merge close peak after adding auxiliary peaks
        mergeClosePeaks();
    }

    /**
     * All peaks in a peakCluster are merged to the peak with highest intensity.
     * @param peakCluster A cluster of peak with similar monoMass
     * @return one peak whose intensity is the sum of the cluster
     */
    private Peak mergePeaks(ArrayList<Peak> peakCluster) {
        float mass = peakCluster.get(0).getMonoMass();
        float maxIntensity = peakCluster.get(0).getIntensity();
        float sumIntensity = 0;

        for (Peak peak : peakCluster) {
//            System.out.println(peak.getMonoMass() + " " + peak.getIntensity());
            if (peak.getIntensity() > maxIntensity) {
                mass = peak.getMonoMass();
            }
            sumIntensity += peak.getIntensity();
        }
//        System.out.println("mass: " + mass + " sum of intensity: " + sumIntensity);
        return new Peak(mass, sumIntensity, peakCluster.get(0).getZ());
    }

    public String valueablePeakListToString() {
        String str = "";
        for (Peak peak: valuablePeakList) {
            str += peak.toString() + "\n";
        }
        return str;
    }
}
