import java.util.*;

public class DenovoTagFinder {

    /**
     * Build a 2D matrix of mass difference between any two peak
     * in a spectrum.
     * @param spectrum A spectrum with a peak list sorted by mass ascending.
     * @return
     */
    public float[][] buildMassDiffMatrix(Spectrum spectrum) {
        int size = spectrum.getPeakList().size();
        float[][] massDiffMatrix = new float[size][size];
        List<Peak> peakList = spectrum.getPeakList();

        /*Initialize the matrix */
        for (int i = 0; i < size; i += 1) {
            for (int j = 0; j < size; j += 1) {
                massDiffMatrix[i][j] = 0.0f;
            }
        }

        /* Set the bottom triangle matrix */
        for (int i = 0; i < size; i += 1) {
            for (int j = i + 1; j < size; j += 1) {
                massDiffMatrix[i][j] = peakList.get(j).getMonoMass() - peakList.get(i).getMonoMass();
            }
        }

        return massDiffMatrix;
    }

    /**
     * Build AA peak map in a bottom triangle matrix.
     * If the difference of monoMass of peaks i and j are one AA mass,
     * m(i, j) = AA, otherwise m(i,j) = 0
     */
    public String[][] buildOneAAMatrix(float[][] massDiffMatrix) {
        int size = massDiffMatrix.length;
        String[][] oneAAMatrix = new String[size][size];

        for (int i = 0; i < size; i += 1) {
            for (int j = i + 1; j < size; j += 1) {
                //System.out.println(massDiffMatrix[i][j]);
                String aa = AAMass.lookUpMassTable(massDiffMatrix[i][j]);
                if (aa != null) {
                    oneAAMatrix[i][j] = aa;
//                    System.out.printf("i: %d, j: %d, massDiff: %f, aa: %s\n", i, j, massDiffMatrix[i][j], aa);
                } else {
                    oneAAMatrix[i][j] = "";
                }
            }
        }
        return oneAAMatrix;
    }

    /**
     * Extract peaks which could generate AA tags from oneAAMatrix.
     */
    public void setValueablePeakList(TopDownSpectrum tdSpectrum, float[][] massDiffMatrix, String[][] oneAAMatrix) {
        List<Peak> peakList = tdSpectrum.getPeakList();
        List<Peak> valuePeakList = new ArrayList<Peak>();
        Set<Integer> valueIndexSet = new HashSet<>();

        int size = peakList.size();

        for (int i = 0; i < size; i += 1) {
            for (int j = i + 1; j < size; j += 1) {
                if (oneAAMatrix[i][j] != "") {
                    valueIndexSet.add(i);
                    valueIndexSet.add(j);
                }
            }
        }
        for (Integer index : valueIndexSet) {
            valuePeakList.add(peakList.get(index));
        }
        Collections.sort(valuePeakList, Peak.getMassComparator());

        tdSpectrum.setValuablePeakList(valuePeakList);
    }

    /**
     * Build transitive closure matrix through one step matrix. The one step matrix
     * is a symmetric matrix only containing
     * If oneStepMatrix[i][j] is 1, means peak_i and Peak_j has an AA in between.
     * 0 means there is no one AA between these two peaks.  Multiple the matrix for several times until no
     * nonzero items increased. If closure matrix[i][j] = N, means there
     * is a N-length tag between peak i and peak j.
     */
    public int[][] buildClosureMatrix(int[][] oneStepMatrix) {
        return null;
    }

    public void removeNoisePeaks(TopDownSpectrum tdSpectrum) {
        float[][] massDiffMatrix = buildMassDiffMatrix(tdSpectrum);
        String[][] oneAAMatrix = buildOneAAMatrix(massDiffMatrix);
        setValueablePeakList(tdSpectrum, massDiffMatrix, oneAAMatrix);
        //System.out.println(spectrum.valueablePeakListToString());
        System.out.println(tdSpectrum.getPeakList().size() + " filtered to " + tdSpectrum.getValuablePeakList().size());

    }
}
