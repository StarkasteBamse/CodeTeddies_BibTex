package wrapper;

import domain.*;
import java.util.List;
import java.util.Set;

public class Wrapper {

    private final int idLength = 3;

    public String wrap(Reference reference) { // palauttaa bibtex-Stringinä
        String type = reference.toString();
        String key = reference.getField("title").substring(0, idLength) 
                     + reference.getID().substring(idLength);
        String n = System.getProperty("line.separator");
        String bib = "@" + type + "{" + key + "," + n;

        // Wrap fields that have been initiated
        Set<String> keys = reference.getFieldsMap().keySet();
        int count = 0;
        for (String fieldName : keys) {
            String fieldValue = handleScandic(reference.getField(fieldName));
            count++;
            bib += "\t" + fieldName + " = {" + fieldValue + "}";
            if (keys.size() != count) {
                bib += ",";
            }
            bib += n;
        }
        bib += "}";

        return bib;
    }

    private String handleScandic(String value) {
        String result = value.replaceAll("ä", "\\\\\"{a}")
                             .replaceAll("Ä", "\\\\\"{A}")
                             .replaceAll("ö", "\\\\\"{o}")
                             .replaceAll("Ö", "\\\\\"{O}")
                             .replaceAll("å", "\\\\aa{}")
                             .replaceAll("Å", "\\\\AA{}");
        return result;
    }
}
