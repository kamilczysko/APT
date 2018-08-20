import java.util.*;

public class RecordAPT {


    public List<String> getStackFromRecord(String apt){

        List<String> strings = Arrays.asList(splitRecord(apt));
        return strings;
    }

    private String[] splitRecord(String apt){

        String inputRecord = apt.replaceAll("(\\s+)|(\\$+)","");
        String[] split = inputRecord.split("(,)|(?<=/|\\(|\\))|(?=/|\\(|\\))");

        return split;
    }


    private boolean paramatersTypeHasChanged(Object params, Object instance){
        return params!=null && !(params.getClass().isAssignableFrom(instance.getClass()));
    }
    
    protected static boolean isDecimal(String word) {
        return word.matches("[-]?[0-9]+[.]+[0-9]+");
    }

    protected static boolean isInt(String word) {
        return word.matches("[-]?[0-9]*");
    }

    private boolean isWord(String word){
        return word.matches("[A-Z]*");
    }

    public static void main(String[] args) {
        String txt ="TLON,GOFWD/      (CIRCLE/     -5.00000,     65.00000,     50.00000,$\n" +
                "      15.00000),ON,(LINE/     -5.00000,     65.00000,     50.00000,$\n" +
                "                              -5.00000,     50.00000,     50.00000)";

//                String txt = "CIRCLE/     -5.00000,     65.00000,     50.00000, 1 , 1.4, A, 1, (B, 1,2,C, 2,3,(D,2.34)),BC,2,5,6,5.56,4.77";

        Integer i = 19;
        System.out.println(i);

    }

}
