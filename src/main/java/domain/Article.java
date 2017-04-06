package domain;

public class Article extends Reference{

    public String author;
    public String title;
    public String journal;
    public String year;
    public String volume;

    public Article() {

    }
//CHECKSTYLE:OFF

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public boolean hasRequiredFields() {
        if (this.author == null) {
            return false;
        } else if (this.title == null) {
            return false;
        } else if (this.journal == null) {
            return false;
        } else if (this.year == null) {
            return false;
        } else if (volume == null) {
            return false;
        }
        return true;
    }
//CHECKSTYLE:ON
}
