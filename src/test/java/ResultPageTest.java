import com.codeborne.selenide.SelenideElement;
import dev.failsafe.internal.util.Assert;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.annotations.*;
import java.util.ArrayList;

public class ResultPageTest {

    @DataProvider(name = "Keywords")
    public Object[][] Category() {
        String category = System.getProperty("Category","Books");
        String name = System.getProperty("Name","Java");
        return new Object[][]{{name,category}};
    }

    @Test(dataProvider = "Keywords")
    public void checkNameTest(String name, String category){
        ResultPage result = ResultPage.getResultPage();
        ArrayList<Book> books;
        boolean isContainBook;

        result.openHomePage();
        result.selectCategory(category);
        result.fillSearchingBar(name);
        result.clickSearchingSubmitButton();
        books = result.fillBooks();
        Book book = result.getNeededBook("https://a.co/d/7nA98uH");
        //Book book = books.get(1);
        isContainBook = result.booksJavaCheck(books, book);

        Assert.isTrue(isContainBook,
                String.format("Книга:\n%s\nне була знайдена на першій сторінці пошуку",
                        book.toString()));

        result.closeBrowser();
    }
}
