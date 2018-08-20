import java.util.*;

public class RecordAPT {



    public Value createRecord(String record){
        List<String> tableOfRecordItems = getTableOfRecordItems(record);
        ListIterator<String> iterator = tableOfRecordItems.listIterator();
        Value apt = new ComplexValue();
        processListOfParameters(iterator, apt);
        return apt;
    }

    private Value processListOfParameters(ListIterator<String> iterator, Value mainValue){

        while(iterator.hasNext()){
            String next = iterator.next();

            if(next.equals("("))
                mainValue.addParameter(processListOfParameters(iterator, new ComplexValue()));
            else if(next.equals(")"))
                return mainValue;
            else
                if(isWord(next)) {
                    if (mainValue.hasWord()) {
                        mainValue.addParameter(new BasicStringValue(next));
                    } else
                        mainValue.setWord(next);
                } else if (isDecimal(next))
                    mainValue.addParameter(new BasicDoubleValue(next));
                else if(isInt(next))
                    mainValue.addParameter(new BasicIntValue(next));
        }
        return mainValue;
    }

    private List<String> getTableOfRecordItems(String apt){

        List<String> recordList = new LinkedList<>();

        String[] split = apt.split(",|/");

        for(String part : split)
            recordList.addAll(splitRecordPart(part));

        return recordList;
    }

    private List<String> splitRecordPart(String part){
        String a = part.replaceAll("(\\s+)|(\\$+)","");
        return Arrays.asList(a.split("(?<=\\(|\\))|(?=\\(|\\))"));
    }

    private boolean paramatersTypeHasChanged(Object params, Object instance){
        return params!=null && !(params.getClass().isAssignableFrom(instance.getClass()));
    }

    private static boolean isDecimal(String word) {
        return word.matches("[-]?[0-9]+[.]+[0-9]+");
    }

    private static boolean isInt(String word) {
        return word.matches("[-]?[0-9]*");
    }

    private boolean isWord(String word){
        return word.matches("[A-Z]*");
    }

    public static void main(String[] args) {
//        String txt ="TLON,GOFWD/      (CIRCLE/     -5.00000,     65.00000,     50.00000,$\n" +
//                "      15.00000),ON,(LINE/     -5.00000,     65.00000,     50.00000,$\n" +
//                "                              -5.00000,     50.00000,     50.00000)";

                String txt = "CIRCLE/     -5.00000,     65.00000,     50.00000, 1 , 1.4, A, 1, (B, 1,2,C, 2,3,(D,2.34)),BC,2,5,6,5.56,4.77";
//
//        RecordAPT r = new RecordAPT();
//        Value record = r.createRecord(txt);
//
//        System.out.println(record);

        Stack stack = new Stack();

        stack.add(1);
        stack.add(2);
        stack.add(3);
        stack.add(4);


        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());



        stack.push(2);
        stack.push(3);
        stack.push(4);


        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());


    }

}
