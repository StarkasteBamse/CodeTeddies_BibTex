package main;

public class Article {
    public String author;
    public String title;
    public String journal;
    public int year;
    public int volume;

    public Article() {

    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public boolean hasRequiredFields() {
        if (this.author.isEmpty()) {
            return false;
        } else if (this.title.isEmpty()) {
            return false;
        } else if (this.journal.isEmpty()) {
            return false;
        } else if (year == 0) {
            return false;
        } else if (volume == 0) {
          return false;
        }
        return true;
    }
}
