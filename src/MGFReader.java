import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * File reader for MGF file produced by Deconv.
 */
public class MGFReader {
    /**
     * Load in the ms2 spectra in a mgf file produced by Deconv.
     * The mS1 spectra will be discarded.
     * @param mgfFileName
     * @return a list of Spectra
     */
    public List<TopDownSpectrum> loadMS2Spectra(String mgfFileName, String FragmentType) {
        ArrayList<TopDownSpectrum> spectraList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(mgfFileName))) {
            String line;
            TopDownSpectrum spectrum = null;
            while ((line = br.readLine()) != null) {
                line.trim();
                if (line.equals("BEGIN IONS")) {
                    spectrum = readOneTopDownSpectrum(br);
                    if (spectrum != null) {
                        if (spectrum.getMslevel() == 2) {
                            spectrum.setType(FragmentType);
                            spectraList.add(spectrum);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return spectraList;
    }

    /**
     * If there is one TopDownSpectrum, read the information of the TopDownSpectrum.
     * @param br  BufferReader handle
     * @return One TopDownSpectrum.
     * @throws IOException
     */
    private TopDownSpectrum readOneTopDownSpectrum(BufferedReader br) throws IOException {
        TopDownSpectrum spectrum = null;
        int scan = 0;
        float pepMass = 0;
        int mslevel = 1;
        int z = 0;
        List<Peak> peakList = new ArrayList<>();

        String line = br.readLine();
        Pattern p = Pattern.compile("^TITLE=Scan_(\\d+)");
        Matcher m = p.matcher(line);
        if (m.find()) {
            scan = Integer.valueOf(m.group(1));
        }

        line = br.readLine();   //skip the MSLEVEL line
        p = Pattern.compile("MSLEVEL=(\\d+)");
        m = p.matcher(line);
        if (m.find()) {
            mslevel = Integer.valueOf(m.group(1));
        }

        line = br.readLine();
        p = Pattern.compile("PEPMASS=(\\S+)");
        m = p.matcher(line);
        if (m.find()) {
            pepMass = Float.valueOf(m.group(1));
        }


        line = br.readLine();
        p = Pattern.compile("CHARGE=(\\d+)\\+");
        m = p.matcher(line);
        if (m.find()) {
            z = Integer.valueOf(m.group(1));
        }

        /* Load the peak list */
        while ((line = br.readLine()) != null) {
            line.trim();
            if (line.equals("END IONS")) {
            //    if (peakList == null) {
                if (peakList.size() == 0) {
                    return null;
                }
                spectrum = new TopDownSpectrum(scan, pepMass, mslevel, z, peakList);
                return spectrum;
            } else {
                String[] peakString = line.split("\\s+");
                Peak peak = new Peak(Float.valueOf(peakString[0]), Float.valueOf(peakString[1]));
                peakList.add(peak);
            }
        }
        return spectrum;
    }


    public static void main(String[] args) {
        String mgfFile = "D:\\Hao\\data\\for_analysis\\mgf\\FAB_MAB_TCEP_ETD_msdeconv.mgf";
        MGFReader mgfReader = new MGFReader();

        List<TopDownSpectrum> spectrumList = mgfReader.loadMS2Spectra(mgfFile, "ETD");
        System.out.println("There are " + spectrumList.size() + " spectra are loaded.");

    }


}
