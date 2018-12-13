import java.util.Comparator;

/* For now, we deal with mgf file exported by Deconv.
   We will deal with .msalign file later
 */
public class Peak {
    float monoMass;
    float intensity;
    int z;

    public Peak(float monoMass, float intensity) {
        this.monoMass = monoMass;
        this.intensity = intensity;
        this.z = 0;
    }

    public Peak(float monoMass, float intensity, int z) {
        this.monoMass = monoMass;
        this.intensity = intensity;
        this.z = z;
    }

    float getMonoMass() {
        return monoMass;
    }

    void setMonoMass(float monoMass) {
        this.monoMass = monoMass;
    }

    float getIntensity() {
        return intensity;
    }

    void setIntensity(float intensity) {
        this.intensity = intensity;
    }

    int getZ() {
        return z;
    }

    @Override
    public String toString() {
        if (0 == z) {
            return monoMass + "\t" + intensity;
        } else {
            return monoMass + "\t" + intensity + "\t" + z;
        }
    }

    private static class MassComparator implements Comparator<Peak> {
        public int compare(Peak p1, Peak p2) {
            return Float.compare(p1.monoMass, p2.monoMass);
        }
    }

    public static Comparator<Peak> getMassComparator() {
        return new MassComparator();
    }

}
