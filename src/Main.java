import java.util.List;

public class Main {
    public static void main(String[] args) {
        /*
        String mgfFile = "D:\\Hao\\data\\for_analysis\\mgf\\FAB_MAB_TCEP_ETD_msdeconv.mgf";
        mgfFile = "D:\\Hao\\data\\for_analysis\\mgf\\FAB_MAB_TCEP_HCD_110626213244_msdeconv.mgf";
        MGFReader mgfReader = new MGFReader();

        List<TopDownSpectrum> tdSpectrumList = mgfReader.loadMS2Spectra(mgfFile, "HCD");
*/
        String msalignFile = "D:\\Hao\\data\\for_analysis\\TopPICmsalign\\valuablePeaks.twoAA\\FAB_MAB_TCEP_ETD_ms2.msalign";

        MSAlignFileReader msAlignFileReader = new MSAlignFileReader();

        List<TopDownSpectrum> tdSpectrumList = msAlignFileReader.loadSpectra(msalignFile);

       // System.out.println(tdSpectrumList.get(200).toString());

        TopDownSpectraProcesser tdProcessor = new TopDownSpectraProcesser();
        System.out.println("Add auxiliary peaks.");
        tdProcessor.addAuxiliaryPeaks(tdSpectrumList);
        System.out.println(tdSpectrumList.get(0).toString());

        System.out.println("Merge close peaks.");
        tdProcessor.mergeClosePeaks(tdSpectrumList);
        System.out.println(tdSpectrumList.get(0).toString());

        System.out.println("Add reflecting Peaks.");
        tdProcessor.addReflectingPeaks(tdSpectrumList);
        System.out.println(tdSpectrumList.get(0).toString());

        System.out.println("Remove peaks belongs to no denovo tag.");
        DenovoTagFinder tagFinder = new DenovoTagFinder();
        //tagFinder.removeNoisePeaks(tdSpectrumList.get(0));

        System.out.println(tdSpectrumList.get(0).valueablePeakListToString());


        tdProcessor.removeNoisePeaks(tdSpectrumList);
        String outFile = msalignFile.replace(".msalign", "") + "_valublePeak.twoAA.msalign";
        MSAlignFileWriter msAlignFileWriter = new MSAlignFileWriter();
        msAlignFileWriter.writeValuablePeakListToMGF(outFile, tdSpectrumList);

    }

}
