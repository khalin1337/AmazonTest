import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import static com.codeborne.selenide.Selenide.*;

public class HomePage {

    //Original URL
    private final String Url = "https://www.amazon.com/";
    public String getUrl()
    {
        return this.Url;
    }
    //Singleton
    private static HomePage home;
    protected HomePage() {}
    public static HomePage getHomePage() {
        if(home == null) {
            home= new HomePage();
        }
        return home;
    }

    //Open Firefox and go to https://www.amazon.com/
    @Step("Opening browser and go to Amazon")
    public void openHomePage() {
        Configuration.browser = "firefox";
        open(Url);
    }

    //Locators
    public SelenideElement searchingSubmitButton() {
        return $(By.xpath("//input[@id='nav-search-submit-button']"));
    }
    public SelenideElement searchingPlate() {
        return $(By.xpath("//input[@id='twotabsearchtextbox']"));
    }
    public SelenideElement categorySelector() {
        return $(By.xpath("//select[@id='searchDropdownBox']"));
    }
    //Search Java
    @Step("Search Java in Books category")
    public void searchJavaInBooks() {
        categorySelector().selectOption("Books");
        searchingPlate().val("Java");
        searchingSubmitButton().click();
    }
}
