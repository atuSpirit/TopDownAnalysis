import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MSAlignFileReader {
    public List<TopDownSpectrum> loadSpectra(String msalignFileName) {
        List<TopDownSpectrum> spectraList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(msalignFileName))) {
            String line;
            /* Skip the file header part of msalign file */
            while ((line = br.readLine()) != null) {
                if (line.startsWith("#")) {
                    continue;
                } else {
                    break;
                }
            }
            TopDownSpectrum spectrum = null;
            while (line != null) {
                line.trim();
                if (line.equals("BEGIN IONS")) {
                    spectrum = readOneTopDownSpectrum(br);
                    if (spectrum != null) {
                        spectraList.add(spectrum);
                    }
                }
                line = br.readLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return spectraList;
    }

    private TopDownSpectrum readOneTopDownSpectrum(BufferedReader br) throws IOException {
        TopDownSpectrum spectrum = null;
        int scan = 0;
        float pepMz = 0;
        float pepMass = 0;
        int z = 0;
        String fragmentType = "";
        List<Peak> peakList = new ArrayList<>();

        String line = br.readLine();    //Skip ID

        line = br.readLine();
        Pattern p = Pattern.compile("^SCANS=(\\d+)");
        Matcher m = p.matcher(line);
        if (m.find()) {
            scan = Integer.valueOf(m.group(1));
        }

        line = br.readLine();   //Skip RETENTION_TIME

        line = br.readLine();
        p = Pattern.compile("^ACTIVATION=(\\S+)");
        m = p.matcher(line);
        if (m.find()) {
            fragmentType = m.group(1);
        }

        line = br.readLine();   //Skip MS_ONE_ID
        line = br.readLine();   //Skip MS_ONE_SCAN

        line = br.readLine();
        p = Pattern.compile("PRECURSOR_MZ=(\\S+)");
        m = p.matcher(line);
        if (m.find()) {
            pepMass = Float.valueOf(m.group(1));
        }

        line = br.readLine();
        p = Pattern.compile("PRECURSOR_CHARGE=(\\d+)");
        m = p.matcher(line);
        if (m.find()) {
            z = Integer.valueOf(m.group(1));
        }

        line = br.readLine();
        p = Pattern.compile("PRECURSOR_MASS=(\\S+)");
        m = p.matcher(line);
        if (m.find()) {
            pepMass = Float.valueOf(m.group(1));
        }

        line = br.readLine();   //Skip PRECURSOR_INTENSITY

        /* Load the peak list */
        while ((line = br.readLine()) != null) {
            line.trim();
            if (line.equals("END IONS")) {
                if (peakList.size() == 0) {
                    return null;
                }
                spectrum = new TopDownSpectrum(scan, pepMass, pepMz, z, fragmentType, peakList);
                return spectrum;
            } else {
            //    System.out.println(line);
                String[] peakString = line.split("\\s+");
                Peak peak = new Peak(Float.valueOf(peakString[0]), Float.valueOf(peakString[1]),
                        Integer.valueOf(peakString[2]));
                peakList.add(peak);
            }
        }
        return spectrum;
    }

    public static void main(String[] args) {
        String msalignFile = "D:\\Hao\\data\\for_analysis\\TopPICmsalign\\FAB_MAB_TCEP_HCD_110626213244_ms2.msalign";
        MSAlignFileReader msAlignFileReader = new MSAlignFileReader();

        List<TopDownSpectrum> spectrumList = msAlignFileReader.loadSpectra(msalignFile);
        System.out.println("There are " + spectrumList.size() + " spectra are loaded.");
        System.out.println(spectrumList.get(4816));

    }
}
