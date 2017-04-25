package wrapper;

import domain.Reference;
import domain.Article;
import domain.Book;
import domain.Inproceedings;

public class Wrapper {

    private int keyAdd;

    public Wrapper() {
        keyAdd = 0;
    }

    public String wrap(Reference reference) { // palauttaa bibtex-Stringinä
        String type = reference.toString();
        String key = reference.getField("title").replaceAll("\\s+", "");
        key = key + keyAdd; // uniikki id
        String n = System.getProperty("line.separator");
                                                           
        String bib = "@" + type + "{" + key + "," + n;
        keyAdd++;
        for (String field : reference.getRequiredFields()) {
            bib += "\t" + field +" = {" + reference.getField(field) + "}," + n;
        }
        /* Wrap optional fields
        for (String field : reference.getOptionalFields()) {
            bib += "\t" + field + " = {" + reference.getField(field) +"}," + n;
        }
        */
        bib += "}";
        
        return bib;
    }
}
