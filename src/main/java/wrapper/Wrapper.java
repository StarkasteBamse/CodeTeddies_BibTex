package wrapper;

import domain.Reference;
import domain.Article;
import domain.Book;
import domain.Inproceedings;

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
        for (String field : reference.getFieldsMap().keySet()) {
            bib += "\t" + field +" = {" + reference.getField(field) + "}," + n;
        } 
        bib += "}";

        return bib;
    } 
}
