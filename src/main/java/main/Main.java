package main;

import io.ConsoleIO;

public class Main {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) {
      ConsoleIO io = new ConsoleIO();


        // TODO: Ignore me from test reports
        System.out.println(testMe("Moi t. CodeTeddies"));

        while (true) {
          io.print("Add? (y/n)");
          if (io.readLine().equals("n")) {
            break;
          } else {
            Article a = new Article();

            io.println("BibTex an article!");
            io.print("Author: ");
            String author = io.readLine();
            if (scandeja(author)) {
                io.println("Invalid author name");
            } else {
                a.setAuthor(author);
            }
            io.println("");

            io.print("Title: ");
            String title = io.readLine();
            if (scandeja(title)) {
                io.println("Invalid title name");
            } else {
                a.setTitle(title);
            }
            io.println("");

            io.print("Journal: ");
            String journal = io.readLine();
            if (scandeja(journal)) {
                io.println("Invalid journal name");
            } else {
                a.setJournal(journal);
            }
            io.println("");

            io.print("Year: ");
            String year = io.readLine();
            a.setYear(Integer.parseInt(year));
            io.println("");

            io.print("Volume: ");
            String volume = io.readLine();
            a.setVolume(Integer.parseInt(volume));
            io.println("");

            if (a.hasRequiredFields()) {
                io.println("New article added succesfully");
            }
          }
        }


    }

    public static boolean scandeja(String s) {
      if (s.contains("ä") || s.contains("ö") || s.contains("Ä") || s.contains("Ö")) {
        return true;
      }
      return false;
    }


    public static String testMe(String lol) {
        return lol;
    }

}
