import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.*;

import static com.codeborne.selenide.Selenide.*;

public class ResultPage extends HomePage {
    private static ResultPage result;
    private Book neededBook;
    public Book getNeededBook() {
        return this.neededBook;
    }
    private ResultPage(){
        super();
    }
    public static ResultPage getResultPage() {
        if(result == null){
            result = new ResultPage();
        }
        return result;
    }

    //Locators
    ElementsCollection results = $$(By.xpath("//div[@role='listitem']"));

    //Methods to get info from elements
    public ArrayList<String> getResultPrices(SelenideElement result) {
        return (ArrayList<String>) result.$$(By.xpath(".//div[@class='a-row']/a[not(contains(span[text()],'to rent'))]/span[@class='a-price']/span[@class='a-offscreen']")).texts();
    }
    public String getResultName(SelenideElement result) {
        return result.$(By.xpath(".//h2//span")).text();
    }
    public boolean getResultBestsellerMark(SelenideElement result) {
        return  !result.$(By.xpath(".//div[contains(@class,'s-list-status-badge-container')]")).text().isEmpty();
    }
    public String getResultAuthorsName(SelenideElement result) {
        String temp = result.$(By.xpath(".//div[@class='a-row' and span[contains(text(),'by')]]")).text();
        temp = temp.replace("and",",");
        temp = temp.replace(", et al.","");
        int endIndex = temp.length();
        if (temp.contains("|")) endIndex = temp.lastIndexOf("|");
        return  temp.substring(temp.indexOf("by")+2,endIndex);
    }

    
    //Fil ArrayList<Book>
    @Step("Create and fill List of books with names, authors`s names, prices and bestseller marks")
    public ArrayList<Book> fillBooks(){
        ArrayList<Book> books = new ArrayList<>();
        for (SelenideElement result : results) {
            books.add(new Book(getResultName(result),
                    getResultBestsellerMark(result),
                    getResultPrices(result),
                    getResultAuthorsName(result).trim()));
            System.out.println( books.getLast().toString() );
        }
        return books;
    }

    //Check to having https://a.co/d/3iWogjC in books
    @Step("Get book that needed to find")
    public Book getNeededBook(String Url){
        open(Url);
        String name = $(By.xpath("//span[@id='productTitle']")).text();
        ArrayList<String> price = new ArrayList<String>();
        price.add($(By.xpath("//span/a//span[@class='a-size-base a-color-secondary']")).text());
        String authors = $(By.xpath("//div[@id='bylineInfo']")).text();
        boolean bestseller = !$(By.xpath("//div[@id='zeitgeistBadge_feature_div']")).text().isEmpty();
        authors = authors.replace(" (Author)","");
        authors = authors.replace("by","");
        authors = authors.trim();
        return new Book(name,bestseller,price,authors);
    }

    @Step("Check if list of books contains needed book")
    public boolean booksJavaCheck(ArrayList<Book> books, Book book) {
        return books.contains(book);
        //return books.stream().anyMatch(b -> b.getName().contains(book.getName()));
        //return books.stream().anyMatch(b -> book.equals(b));
    }
}
