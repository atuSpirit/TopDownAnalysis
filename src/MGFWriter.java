import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
/**
 * File writer to produce MGF file.
 */
public class MGFWriter {
    public void writeValuablePeakListToMGF(String mgfFile, List<TopDownSpectrum> tdSpectrumList) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(mgfFile))) {
            for (TopDownSpectrum tdSpectrum : tdSpectrumList) {
                if (tdSpectrum.getValuablePeakList().size() == 0) {
                    continue;
                }
                bw.write("BEGIN IONS\n");
                bw.write("TITLE=Scan_" + tdSpectrum.getScan() + "\n");
                bw.write("MSLEVEL=2\n");
                bw.write("PEPMASS=" + tdSpectrum.getPepMass() + "\n");
                bw.write("CHARGE=" + tdSpectrum.getZ() + "+\n");
                bw.write(tdSpectrum.valueablePeakListToString());
                bw.write("END IONS\n\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
