import java.util.*;

public class RecordAPT {
    private final String SPLIT_REGEX_PATTERN = "(?<=\\(|\\))|(?=\\(|\\))";
    private final String REPLACE_REGEX_PATTERN = "(\\s+)|(\\$+)";

    public Value getComplexValueFromString(String record) {
        List<String> listOfRecordStrings = splitRecordToTableOfStrings(record);
        ListIterator<String> iterator = listOfRecordStrings.listIterator();

        Value apt = new ComplexValue();
        convertRecordToComplexValue(iterator, apt);

        return apt;
    }

    private Value convertRecordToComplexValue(ListIterator<String> iterator, Value mainValue) {
        String paramWord = null;

        while (iterator.hasNext()) {
            String next = iterator.next();

            if (next.equals("(")) {
                Value parameter = convertRecordToComplexValue(iterator, new ComplexValue());
                mainValue.addParameter(parameter);
            } else if (next.equals(")")) {
                ((ComplexValue) mainValue).setParametersStack();
                return mainValue;
            } else if (next.isEmpty()) {
                mainValue.addParameter(null);
            } else {
                paramWord = assignAppropriateValues(next, mainValue, paramWord);
            }
        }
        ((ComplexValue) mainValue).setParametersStack();

        return mainValue;
    }

    private String assignAppropriateValues(String next, Value mainValue, String parameterWord) {

        if (isWord(next)) {
            if (mainValue.hasWord() && mainValue.isComplexValue()) {
                Value basicStringValue = new BasicStringValue(next).setMainWord(parameterWord);
                mainValue.addParameter(basicStringValue);
            } else {
                mainValue.setMainWord(next);
            }

            parameterWord = next;

        } else if (isDecimal(next)) {
            Value basicDoubleValue = new BasicDoubleValue(next).setMainWord(parameterWord);
            mainValue.addParameter(basicDoubleValue);
        } else if (isInt(next)) {
            Value basicIntValue = new BasicIntValue(next).setMainWord(parameterWord);
            mainValue.addParameter(basicIntValue);
        }

        return parameterWord;
    }

    private List<String> splitRecordToTableOfStrings(String apt) {
        List<String> recordList = new LinkedList<>();
        String[] split = apt.split(",|/");

        for (String part : split)
            recordList.addAll(splitOnePieceOfRecord(part));

        return recordList;
    }

    private List<String> splitOnePieceOfRecord(String part) {
        String a = part.replaceAll(REPLACE_REGEX_PATTERN, "");
        List<String> strings = Arrays.asList(a.split(SPLIT_REGEX_PATTERN));

        return strings;
    }

    private boolean isDecimal(String word) {
        return word.matches("[-]?[0-9]+[.]+[0-9]+");
    }

    private boolean isInt(String word) {
        return word.matches("[-]?[0-9]*");
    }

    private boolean isWord(String word) {
        return word.matches("[A-Z]*");
    }

    /*****/
    public static void main(String[] args) {
        String txt = "TLON,GOFWD/  1,2,3,    (CIRCLE/     -5.00000,     65.00000,     50.00000,$\n" +
                "      15.00000),ON,(LINE/     -5.00000,     65.00000,     50.00000,$\n" +
                "                              -5.00000, ,51.00000,     50.00000)";
        RecordAPT r = new RecordAPT();
        Value record = r.getComplexValueFromString(txt);
        String s = record.getMainWord() + "::\n" + drawRecord(((ComplexValue) record), "");
        System.out.println(s);
    }

    static String drawRecord(ComplexValue record, String tabs) {
        StringBuilder output = new StringBuilder();
        List<Value> parameters = record.getParameters();

        tabs += "\t";
        for (Value myRecord : parameters) {
            if (myRecord != null && myRecord.isComplexValue()) {
                output.append(tabs + "-" + myRecord.getMainWord() + " : \n");
                String s = drawRecord((ComplexValue) myRecord, tabs);
                output.append(s);
            } else if (myRecord == null) {
                output.append(tabs + "none---\n");
            } else {
                if (myRecord.hasWord() && !myRecord.hasBasicValue()) {
                    output.append(myRecord.getMainWord() + " : \n");
                } else if (!(myRecord instanceof ComplexValue)) {
                    output.append(tabs + "*" + myRecord.toString() + " \n");
                }
            }
        }

        return output.toString();
    }
}
