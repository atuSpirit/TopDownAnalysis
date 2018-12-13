import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MSAlignFileWriter {
    public void writeValuablePeakListToMGF(String msalignFile, List<TopDownSpectrum> tdSpectrumList) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(msalignFile))) {
            for (TopDownSpectrum tdSpectrum : tdSpectrumList) {
                if (tdSpectrum.getValuablePeakList().size() == 0) {
                    continue;
                }
                bw.write("BEGIN IONS\n");
                bw.write("ID=0\n");
                bw.write("SCANS=" + tdSpectrum.getScan() + "\n");
//                bw.write("RETENTION_TIME=0\n");
                bw.write("ACTIVATION=" + tdSpectrum.getType() + "\n");
//                bw.write("MS_ONE_ID=0\n");
//                bw.write("MS_ONE_SCAN=1\n");
                bw.write("PRECURSOR_MZ=" + tdSpectrum.getPepMz() + "\n");
                bw.write("PRECURSOR_CHARGE=" + tdSpectrum.getZ() + "\n");
                bw.write("PRECURSOR_MASS=" + tdSpectrum.getPepMass() + "\n");
//                bw.write("PRECURSOR_INTENSITY=0\n");
                bw.write(tdSpectrum.valueablePeakListToString());
                bw.write("END IONS\n\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
