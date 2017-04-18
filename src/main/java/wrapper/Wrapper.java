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
        String key = reference.getField("title") + keyAdd; // Jokaisella täytyy olla 
                                                           // uniikki id
        String bib = "@" + type + "{" + key + ",\n";
        keyAdd++;
        for (String field : reference.getRequiredFields()) {
            bib += "\t" + field +" = {" + reference.getField(field) + "},\n";
        }
        bib += "}";
        
        return bib;
    }

//        Main-code example:
//        Wrapper wrp = new Wrapper();
//        String bib = wrp.wrap(a);
//        io.println(bib);
}
