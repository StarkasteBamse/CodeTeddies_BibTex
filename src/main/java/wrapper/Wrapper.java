
package wrapper;

import main.Article;


public class Wrapper {
    
    private int keyAdd;
    
    public Wrapper() {
        keyAdd = 0;
    }
    
    public String wrap(Article art) { // wrappaa artikkelin - palauttaa bibtex-muotoisena Stringinä
        String type = "article";
        String key = art.title + keyAdd; // Jokaisella täytyy olla uniikki id
        String bib = "@" + type + "{" + key + ",\n";
        keyAdd++;
        bib = bib + "\tauthor = {" + art.author + "},\n";
        bib = bib + "\ttitle = {" + art.title + "},\n";
        bib = bib + "\tjournal = {" + art.journal + "},\n";
        bib = bib + "\tyear = {" + art.year + "},\n";
        bib = bib + "\tvolume = {" + art.volume + "}\n";
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
