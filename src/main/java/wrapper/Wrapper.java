package wrapper;

import domain.Reference;
import domain.Article;
import domain.Book;
import domain.Inproceedings;
import java.util.List;
import java.util.Set;

public class Wrapper {

    private int keyAdd;
    private final int idLength = 6;
    private final int randomFactor = 100000;

    public String wrap(Reference reference) { // palauttaa bibtex-Stringinä
        String type = reference.toString();
        String key = reference.getField("title").replaceAll("\\s+", "");

        if (key.length() >= idLength) {
            key = key.substring(0, idLength);
        }
        key += ((int) (Math.random() * randomFactor)); // pseudouniikki id
        String n = System.getProperty("line.separator");

        String bib = "@" + type + "{" + key + "," + n;
        keyAdd++;
        // Wrap fields that have been initiated
        Set<String> keys = reference.getFieldsMap().keySet();
        int count = 0;
        for (String field : keys) {
            count++;
            bib += "\t" + field +" = {" + reference.getField(field) + "}";
            if (keys.size() != count) {
                bib += ",";
            }
            bib += n;
        } 
        bib += "}";

        return bib;
    } 
}
