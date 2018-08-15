import java.io.Serializable;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RecordAPT {


    private static Pattern Patt = Pattern.compile("[\\s]*([(]?[-]?[\\s]*[a-zA-Z0-9]*[.]?[a-zA-Z0-9]*)[\\s]*([,/]?)(.*)"); // og�lny pattern do rozbijania rekordu

    private static Pattern WordPatt = Pattern.compile("[\\s]*([(]?[A-Z]+)"); //wz�r s�owa

    Matcher m;
    static Matcher mw;

    String arg = null, sep = null, rest = null;
    boolean blind = true;// gdy ko�czy si� rekord nawiasem zamykaj�cym to
    // zmienna jest false; powoduje to �e nie jest potem
    // dodawany pusty argument(null) do rekordu

    public ArrayList selekcja(String linia) {

        blind = true;

        // zmienne przechowuja polecenie podzielone na czesci: argument, separator(, lub /), i reszta polecenia
        ArrayList rekord, word, args, segment;// rekord - przechowuje ca�y rekord (zawiera ArrayList word i args);
        //word - zawiera minor albo major word;
        //args - przechowuje argumenty

        rekord = new ArrayList();
        word = new ArrayList();
        args = new ArrayList();
        segment = new ArrayList();

        rest = linia;
        while (!rest.equals("")) {
            m = Patt.matcher(rest);
            if (m.find()) {
                arg = m.group(1);

                sep = m.group(2);
                rest = m.group(3);
                rest = rest.trim();
            }

            // je�li argument jest s�owem
            if (isWord(arg)) {

                if (arg.equals("TPRINT") || arg.equals("PPRINT")
                        || arg.equals("INSERT") || arg.equals("PARTNO")) {
                    word.add(arg);
                    args.add(rest);
                    segment.add(word);
                    segment.add(args);
                    rekord.add(segment);
                    break;
                }

                if ((word.isEmpty() || sep.equals("/"))) {
                    word.add(arg);
                } else {
                    segment.add(word);

                    if (args.isEmpty())
                        args.add(null);
                    segment.add(args);
                    rekord.add(segment);

                    word = new ArrayList();
                    args = new ArrayList();
                    segment = new ArrayList();

                    word.add(arg);

                }
            } else {// gdy argument nie jest s�owem
                if (!arg.equals("") & blind)
                    args.add(arg);
                else if ((sep.equals(",") || (rest.equals(",") && sep.equals(","))/* || (rest.equals("") && sep.equals(","))*/) && blind) {
                    args.add(null);

                }/* else if (blind) {
					args.add(arg);
				}//*/
                blind = true;
            }

            if (rest.startsWith("(")) {
                rest = rest.substring(1);
                args.add(selekcja(rest));
            }

            if (rest.startsWith(")")) {
                blind = false;
                rest = rest.substring(1);
                rest = rest.trim();

                segment.add(word);
                if (args.isEmpty() || sep.equals(","))
                    args.add(null);
                segment.add(args);
                rekord.add(segment);
                return rekord;
            }
            if (rest.equals("")) {
                segment.add(word);
                if (args.isEmpty() || sep.equals(","))
                    args.add(null);
                segment.add(args);
                rekord.add(segment);
                return rekord;
            }

        }
        return rekord;
    }

    protected boolean isWord(String word) {
        mw = WordPatt.matcher(word);
        if (mw.find()) {
            return true;
        } else
            return false;

    }

    protected static boolean isDoubleNumber(String word) {
        if (word.matches("[-]?[0-9]+[.]+[0-9]+")) {
            return true;
        } else
            return false;
    }

    protected static boolean isIntNumber(String word) {
        if (word.matches("[-]?[0-9]*")) {
            return true;
        } else
            return false;
    }

    // ********interfejs dla tablicy

    public ArrayList MajWords(ArrayList al) {// wypisuje wszystkie s�owa
        ArrayList l, lt;
        ArrayList words = new ArrayList();
        for (int i = 0; i < al.size(); i++) {
            l = (ArrayList) al.get(i);
            lt = (ArrayList) l.get(0);
            for (int j = 0; j < lt.size(); j++) {
                words.add(lt.get(j));
            }
        }
        return words;
    }

    public ArrayList MajWords(String rek) {// wypisuje wszystkie s�owa
        if (rek != null) {
            ArrayList l, lt, al;
            ArrayList words = new ArrayList();

            al = selekcja(rek);
            for (int i = 0; i < al.size(); i++) {
                l = (ArrayList) al.get(i);
                lt = (ArrayList) l.get(0);
                for (int j = 0; j < lt.size(); j++) {
                    words.add(lt.get(j));
                }
            }
            return words;
        }
        return null;
    }

    ArrayList words;

    private void AllMajWords(ArrayList al) {// wypisuje wszystkie s�owa nawet z podrekord�w
        ArrayList l, lt;

        for (int i = 0; i < al.size(); i++) {
            l = (ArrayList) al.get(i);
            lt = (ArrayList) l.get(0);
            for (int j = 0; j < lt.size(); j++)
                words.add(lt.get(j));

            lt = (ArrayList) l.get(1);
            for (int y = 0; y < lt.size(); y++) {
                if (lt.get(y) != null) {
                    if (lt.get(y).getClass().getCanonicalName().equals("java.util.ArrayList")) {
                        AllMajWords((ArrayList) lt.get(y));
                    }
                }
            }
        }
        System.out.println(words);
    }

    public ArrayList getAllWords(String rek) { // zwraca tablice ze wszystki s�owami rekordu (korzysta z AllMajWords)
        words = new ArrayList<Object>();
        if (rek != null) {
            AllMajWords(selekcja(rek));
            return words;
        } else
            return null;
    }


    public static void main(String[] args) {
        String txt = "TPRINT/mill 10";
        ArrayList rek = (new RecordAPT().selekcja(txt));
        System.out.println(rek);

    }

}
