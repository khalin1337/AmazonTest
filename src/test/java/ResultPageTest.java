import com.codeborne.selenide.SelenideElement;
import dev.failsafe.internal.util.Assert;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.testng.annotations.*;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;

public class ResultPageTest {
    ResultPage result = ResultPage.getResultPage();
    @BeforeClass
    public void setUP(){
        result.openHomePage();
    }
    @AfterClass
    @Step("Close browser")
    public void tearDown(){
        closeWebDriver();
    }
    @Test
    public void checkNameTest(){
        Assert.isTrue(result.booksJavaCheck(),
                String.format("Книга:\"%s\" не була знайдена на першій сторінці пошуку",
                        result.getNameNeededBook()));
    }
}
