import java.util.List;
import java.util.ArrayList;

public class Spectrum {
    int scan;
    float pepMass;
    float pepMz;
    int mslevel;
    int z;
    List<Peak> peakList;
    String fragType;

    /* Constructor for the spectrum read from mgf file */
    public Spectrum(int scan, float pepMass, int mslevel, int z, List<Peak> peakList) {
        this.scan = scan;
        this.pepMass = pepMass;
        this.mslevel = mslevel;
        this.z = z;
        this.peakList = peakList;

    }

    /* Constructor for the spectrum read from mgf file */
    public Spectrum(int scan, float pepMass, int mslevel, int z, String fragType, List<Peak> peakList) {
        this.scan = scan;
        this.pepMass = pepMass;
        this.mslevel = mslevel;
        this.z = z;
        this.peakList = peakList;

        this.fragType = fragType;
    }

    /* Constructor for the spectrum read from msalign file */
    public Spectrum(int scan, float pepMass, float pepMz, int z, String fragType, List<Peak> peakList) {
        this.scan = scan;
        this.pepMass = pepMass;
        this.pepMz = pepMz;
        this.z = z;
        this.fragType = fragType;
        this.peakList = peakList;
    }

    public int getScan() {
        return scan;
    }

    public void setScan(int scan) {
        this.scan = scan;
    }

    public float getPepMass() {
        return pepMass;
    }

    public void setPepMass(float pepMass) {
        this.pepMass = pepMass;
    }

    public float getPepMz() {
        return pepMz;
    }

    public void setPepMz(float pepMz) {
        this.pepMz = pepMz;
    }


    public int getMslevel() {
        return mslevel;
    }

    public void setMslevel(int mslevel) {
        this.mslevel = mslevel;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public List<Peak> getPeakList() {
        return peakList;
    }

    public void setPeakList(List<Peak> peakList) {
        this.peakList = peakList;
    }



    public String getType() {
        return fragType;
    }

    public void setType(String fragType) {
        this.fragType = fragType;
    }

    public float getMaxIntensity() {
        float maxInten = 0;
        for (Peak peak : peakList) {
            if (maxInten < peak.intensity) {
                maxInten = peak.intensity;
            }
        }
        return maxInten;
    }

    @Override
    public String toString() {
        String string = "Scan: " + scan + "\n"
                + "pepMass: " + pepMass + "\n"
                + "charge: " + z + "\n";
        for (Peak peak : peakList) {
            string += peak.toString() + "\n";
        }

        return string;
    }


}
