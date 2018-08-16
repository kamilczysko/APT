import java.util.*;

public class RecordAPT {


    public List<String> getStackFromRecord(String apt){

        List<String> strings = Arrays.asList(splitRecord(apt));
        System.out.println(strings);
        return strings;
    }

    private String[] splitRecord(String apt){

        String inputRecord = apt.replaceAll("(\\s+)|(\\$+)","");
        String[] split = inputRecord.split("(,)|(?<=/|\\(|\\))|(?=/|\\(|\\))");

        return split;
    }

    private Record generateRecord(String apt){
        List<String> stackFromRecord = getStackFromRecord(apt);
        ListIterator<String> stringListIterator = stackFromRecord.listIterator();

        Record aptRecord = null;

        aptRecord = new BasicInstruction();

        if(stringListIterator.hasNext()) {

            String next = stringListIterator.next();

            if (isWord(next)){
                aptRecord.setMainWord(next);
                substractParameters(stringListIterator, aptRecord);
            }

        }
        return aptRecord;
    }


    private void substractParameters(ListIterator<String> recordIterator, Record mainRecord){
        BasicParameters parameters = null;
        do{
            String next = recordIterator.next();

            if(next.matches("/|\\(|\\)"))
                if(recordIterator.hasNext()) {
//                    next = recordIterator.next();
                    continue;
                }else {
                    ((BasicInstruction) mainRecord).addParameter(parameters);
                    break;
                }

            System.out.println(next);

            if(next.equals("LINE"))
                System.out.println("dupa");

            if(isDecimal(next)) {
                if(paramatersTypeHasChanged(parameters, new BasicDecimalParameters())) {
                    ((BasicInstruction)mainRecord).addParameter(parameters);
                    parameters = new BasicDecimalParameters();
                }if(parameters == null)
                    parameters = new BasicDecimalParameters();
            }else if(isWord(next)) {
                if(paramatersTypeHasChanged(parameters, new BasicStringParameter())) {
                    ((BasicInstruction) mainRecord).addParameter(parameters);
                    parameters = new BasicStringParameter();
                }
                if(parameters == null)
                    parameters = new BasicStringParameter();
            }else if(isInt(next)) {
                if (paramatersTypeHasChanged(parameters, new BasicIntParameters())) {
                    ((BasicInstruction) mainRecord).addParameter(parameters);
                    parameters = new BasicIntParameters();
                }
                if(parameters == null)
                    parameters = new BasicIntParameters();
            }

            parameters.addParameter(next);

//            if(!recordIterator.hasNext())
//                ((BasicInstruction) mainRecord).addParameter(parameters);

        }while (recordIterator.hasNext());

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

    // ********interfejs dla tablicy


    public static void main(String[] args) {
        String txt ="TLON,GOFWD/      (CIRCLE/     -5.00000,     65.00000,     50.00000,$\n" +
                "      15.00000),ON,(LINE/     -5.00000,     65.00000,     50.00000,$\n" +
                "                              -5.00000,     50.00000,     50.00000)";

                //"CIRCLE/     -5.00000,     65.00000,     50.00000, BENIZ , 1 , 2, 4.454545,33.4545454";


        RecordAPT r = new RecordAPT();
        Record record = r.generateRecord(txt);
        System.out.println(record.getMainWord());
        List parameters = record.getParameters();
        System.out.println(parameters);


    }

}
