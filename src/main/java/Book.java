public class Book {
    private String name;
    private boolean bestsellermark;

    public Book(String name, boolean bestsellermark) {
        this.name = name;
        this.bestsellermark = bestsellermark;
    }
    public String getName(){return this.name;}
    public boolean getBestsellerMark() {return  this.bestsellermark;}

    @Override
    public String toString() {return String.format("Назва: %s\nЧи є Бестселлером: %s ",name,bestsellermark);}
}
