package domain;

public class Inproceedings extends Reference {
    
    public String author;
    public String title;
    public String booktitle;
    public String year;
    
    public Inproceedings() {
        
    }

//CHECKSTYLE:OFF
    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setBookTitle(String BookTitle) {
        this.booktitle = BookTitle;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public boolean hasRequiredFields() {
        if (this.author == null) {
            return false;
        } else if (this.title == null) {
            return false;
        } else if (this.booktitle == null) {
            return false;
        } else if (this.year == null) {
            return false;
        } else {
            return true;
        }
    }
//CHECKSTYLE:ON

}
