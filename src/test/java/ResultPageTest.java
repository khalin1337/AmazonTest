import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.commands.As;
import dev.failsafe.internal.util.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import static com.codeborne.selenide.Selenide.*;

public class ResultPageTest {
    ResultPage Result = ResultPage.getResultPage();
    @BeforeClass
    public void SetUP(){
        Result.OpenHomePage();
    }
    @AfterClass
    public void TearDown(){
        closeWebDriver();
    }
    @Test
    public void CheckNameTest(){
        Assert.isTrue(Result.BooksJavaCheck(),String.format("Книга:\"%s\" не була знайдена на першій сторінці пошуку", Result.getNameNeededBook()));
    }
}
