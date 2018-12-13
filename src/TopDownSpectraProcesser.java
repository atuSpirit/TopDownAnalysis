import java.util.List;

/**
 * Pre-process top down spectra.
 */
public class TopDownSpectraProcesser {
    /**
     * Add auxiliary peaks to each spectrum.
     * For CID/HCD spectrum, 0, pepMass, m(H2O), pepMass - m(H2O) are added.
     * For ETD spectrum, 0, pepMass, m(H2O) - m(N3H), pepMass - (m(H2O) - m(N3H)) are added.
     * @param tdSpectra The top down spectra set
     */
    public void addAuxiliaryPeaks(List<TopDownSpectrum> tdSpectra) {
        for (TopDownSpectrum tdSpectrum : tdSpectra) {
            tdSpectrum.addAuxiliaryPeaks();
        }
    }

    /**
     * For each spectrum, merge the close peaks in the spectrum.
     * @param tdSpectra The top down spectra set
     */
    public void mergeClosePeaks(List<TopDownSpectrum> tdSpectra) {
        for (TopDownSpectrum tdSpectrum : tdSpectra) {
            tdSpectrum.mergeClosePeaks();
        }
    }

    /**
     * For For each m(p) in the peakList, add its complementary peak pepMass(p) - m(p)
     *   to the peakList.
     */
    public void addReflectingPeaks(List<TopDownSpectrum> tdSpectra) {
        for (TopDownSpectrum tdSpectrum : tdSpectra) {
            tdSpectrum.addReflectingPeaks();
        }

    }

    /**
     * For each spectrum, remove noise peaks.
     */
    public void removeNoisePeaks(List<TopDownSpectrum> tdSpectra) {
        DenovoTagFinder tagFinder = new DenovoTagFinder();

        for (TopDownSpectrum tdSpectrum : tdSpectra) {
            tagFinder.removeNoisePeaks(tdSpectrum);
        }
    }



}
