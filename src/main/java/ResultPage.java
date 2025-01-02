import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

import java.util.ArrayList;
import java.util.List;

import static com.codeborne.selenide.Selenide.*;

public class ResultPage extends HomePage {
    private static ResultPage result;
    private String nameNeededBook;
    public String getNameNeededBook() {
        return this.nameNeededBook;
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
    public ElementsCollection getResults() {
        return $$(By.xpath("//div[@role='listitem']"));
    }
    public List<String> getResultPrices(SelenideElement result) {
        return result.$$(By.xpath(".//div[@class='a-row']/a[not(contains(span[text()],'to rent'))]/span[@class='a-price']/span[@class='a-offscreen']")).texts();
    }
    public String getResultName(SelenideElement result) {
        return result.$(By.xpath(".//h2//span")).text();
    }
    public boolean getResultBestsellerMark(SelenideElement result) {
        return  !result.$(By.xpath(".//div[contains(@class,'s-list-status-badge-container')]")).text().isEmpty();
    }
    public String getResultAuthorsName(SelenideElement result) {
        String temp = result.$(By.xpath(".//div[@class='a-row' and span[contains(text(),'by')]]")).text();
        int endIndex = temp.length();
        if (temp.contains("|")) endIndex = temp.lastIndexOf("|");
        return  temp.substring(temp.indexOf("by")+2,endIndex);
    }

    
    //Fil ArrayList<Book>
    @Step("Create and fill List of books with names, authors`s names, prices and bestseller marks")
    public ArrayList<Book> fillBooks(){
        ArrayList<Book> books = new ArrayList<>();
        for (SelenideElement result : getResults()) {
            books.add(new Book(getResultName(result),
                    getResultBestsellerMark(result),
                    getResultPrices(result),
                    getResultAuthorsName(result)));
            System.out.println( books.getLast().toString() );
        }
        return books;
    }

    //Check to having https://a.co/d/3iWogjC in books
    @Step("Get Name of book that needed to find")
    public void takeNameNeededBook(String Url){
        open(Url);
        nameNeededBook = $(By.xpath("//span[@id='productTitle']")).text();
    }

    @Step("Check if list of books contains needed book")
    public boolean booksJavaCheck(ArrayList<Book> books) {
        return books.stream().anyMatch(book -> book.getName().contains(this.nameNeededBook.trim()));
    }
}
