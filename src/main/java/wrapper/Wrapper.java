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
        for (String field : reference.getRequiredFields()) {
            bib += "\t" + field + " = {" + reference.getField(field) + "}," + n;
        }
        // Wrap optional fields
        for (String field : reference.getOptionalFields()) {
            if (reference.getField(field) != null) {
                bib += "\t" + field + " = {" + reference.getField(field) + "}," + n;
            }
        }

        bib += "}";

        return bib;
    }
}
