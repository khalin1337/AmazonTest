import java.util.ArrayList;
import java.util.List;

public class Book {
    private final String name;
    private final boolean bestseller;
    private final ArrayList<String> prices;
    private final String authorName;

    public Book(String name, boolean bestseller, ArrayList<String> price, String authorName) {
        this.authorName = authorName;
        this.prices = price;
        this.name = name;
        this.bestseller = bestseller;
    }
    public String getName() {
        return this.name;
    }
    public ArrayList<String> getPrices() {
        return this.prices;
    }
    public String getAuthorName() {
        return this.authorName;
    }

    public boolean getBestsellerMark() {
        return  this.bestseller;
    }

    @Override
    public String toString() {
        return String.format("Назва: %s\nЧи є Бестселлером: %s\nАвтори: %s\nЦіни: %s",name, bestseller ? "Так" : "Ні", authorName, prices);
    }
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Book book)) {
            return false;
        }
        return this.name.equals(book.getName()) && this.authorName.contains(book.getAuthorName()) && this.prices.equals(book.getPrices()) && this.bestseller == book.getBestsellerMark();
    }
}
