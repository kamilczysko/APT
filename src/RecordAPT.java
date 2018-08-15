import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class RecordAPT {


    public List<String> splitRecord(String apt){

        String inputRecord = apt.replaceAll("(\\s+)|(\\$+)","");
        String[] split = inputRecord.split("(,)|(?<=/|\\(|\\))|(?=/|\\(|\\))");

        for(String a : split)
            System.out.println(a);
        return Arrays.asList(split);
    }

    protected static boolean isDecimal(String word) {
        if (word.matches("[-]?[0-9]+[.]+[0-9]+")) {
            return true;
        } else
            return false;
    }

    protected static boolean isInt(String word) {
        if (word.matches("[-]?[0-9]*")) {
            return true;
        } else
            return false;
    }

    // ********interfejs dla tablicy


    public static void main(String[] args) {
        String txt = "TLON,GOFWD/      (CIRCLE/     -5.00000,     65.00000,     50.00000,$\n" +
                "      15.00000),ON,(LINE/     -5.00000,     65.00000,     50.00000,$\n" +
                "                              -5.00000,     50.00000,     50.00000)";
        System.out.println(new RecordAPT().splitRecord(txt));


    }

}
