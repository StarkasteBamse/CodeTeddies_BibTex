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
        String type = "article";
        String key = reference.getField("title") + keyAdd; // Jokaisella täytyy olla 
                                                     // uniikki id
        String bib = "@" + type + "{" + key + ",\n";
        keyAdd++;
        bib = bib + "\tauthor = {" + reference.getField("author") + "},\n";
        bib = bib + "\ttitle = {" + reference.getField("title") + "},\n";
        bib = bib + "\tjournal = {" + reference.getField("journal") + "},\n";
        bib = bib + "\tyear = {" + reference.getField("year") + "},\n";
        bib = bib + "\tvolume = {" + reference.getField("volume") + "}\n";
        bib = bib + "}";
//        String bibtex = "@"; // + type + "{" + key + ", ";
//        String[] taulukko = new String[5];//art.getJotain()
//        for (int i = 0; i < fieldCount; i++) {
//            bibtex = bibtex + fieldname " = {" + fieldText + "},\n";
//        }
        return bib;
    }

//        Main-code example:
//        Wrapper wrp = new Wrapper();
//        String bib = wrp.wrap(a);
//        io.println(bib);
}
