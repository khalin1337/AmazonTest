import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;

public class ResultPage extends HomePage {
    private static ResultPage result;
    private String nameneededbook;
    public String getNameNeededBook(){return this.nameneededbook;}
    private ResultPage(){
        super();
    }
    public static ResultPage getResultPage(){
        if(result == null){
            result = new ResultPage();
        }
        return result;
    }

    //Locators
    public ElementsCollection GetResultsName(){
        return $$(By.xpath("//div[@role='listitem']//h2//span"));
    }
    public ElementsCollection GetResultsBestsellerMarks(){
        return  $$(By.xpath("//div[contains(@class,'s-list-status-badge-container')]"));
    }
    
    //Fil ArrayList<Book>
    public ArrayList<Book> FillBooks(){
        SearchJavaInBooks();
        ElementsCollection listname = GetResultsName();
        ElementsCollection listmarks = GetResultsBestsellerMarks();
        ArrayList<Book> books = new ArrayList<Book>();
        for(int i=0; i<listname.size(); i++){
            books.add(new Book(listname.texts().get(i), !listmarks.texts().get(i).isEmpty()));
        }
        return books;
    }

    //Check to having https://a.co/d/3iWogjC in books
    public void TakeNameNeededBook(String Url){
        open(Url);
        nameneededbook = $(By.xpath("//span[@id='productTitle']")).text();
    }

    public boolean BooksJavaCheck() {
        ArrayList<Book> books = FillBooks();
        TakeNameNeededBook("https://a.co/d/3iWogjC");//Замінив шукану книгу бо данної вами не було на першій сторінці пошуку
        //TakeNameNeededBook("https://www.amazon.com/Head-First-Java-Kathy-Sierra/dp/0596009208/ref=sr_1_2?dchild=1&keywords=Java&qid=1610356790&s=books&sr=1-2");
        return books.stream().anyMatch(book -> book.getName().contains(nameneededbook.trim()));
    }
}
