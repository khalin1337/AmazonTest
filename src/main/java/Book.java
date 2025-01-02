import java.util.List;

public class Book {
    private final String name;
    private final boolean bestseller;
    private final List<String> prices;
    private final String authorName;

    public Book(String name, boolean bestseller, List<String> price, String authorName) {
        this.authorName = authorName;
        this.prices = price;
        this.name = name;
        this.bestseller = bestseller;
    }
    public String getName() {
        return this.name;
    }
    public List<String> getPrices() {
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
        return String.format("Назва: %s\nЧи є Бестселлером: %s\nАвтори: %s\nЦіни: %s ",name, bestseller ? "Так" : "Ні", authorName, prices);
    }
}
