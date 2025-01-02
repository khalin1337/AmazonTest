import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
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
    @Step("Open browser and go to Amazon")
    public void openHomePage() {
        Configuration.browser = "firefox";
        open(Url);
    }
    @Step("Close browser")
    public void closeBrowser() {
        closeWebDriver();
    }

    //Locators
    SelenideElement searchingSubmitButton =  $(By.xpath("//input[@id='nav-search-submit-button']"));
    SelenideElement searchingBar = $(By.xpath("//input[@id='twotabsearchtextbox']"));
    SelenideElement categorySelector = $(By.xpath("//select[@id='searchDropdownBox']"));

    //Search Java
    @Step("Select category")
    public void selectCategory(String category) {
        categorySelector.selectOption(category);
    }
    @Step("Fill search bar")
    public void fillSearchingBar(String name) {
        searchingBar.val(name);
    }

    @Step("Search Java in Books category")
    public void clickSearchingSubmitButton() {
        searchingSubmitButton.click();
    }
}
