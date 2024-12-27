import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.SelenideElement;
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
    //Locators
    public SelenideElement SearchingSubmitButton() {
        return $(By.xpath("//input[@id='nav-search-submit-button']"));
    }
    public SelenideElement SearchingPlate() {
        return $(By.xpath("//input[@id='twotabsearchtextbox']"));
    }
    public SelenideElement CategorySelector() {
        return $(By.xpath("//select[@id='searchDropdownBox']"));
    }
    //Search Java
    public void SearchJavaInBooks() {
        CategorySelector().selectOption("Books");
        SearchingPlate().val("Java");
        SearchingSubmitButton().click();
    }
    //Open Firefox and go to https://www.amazon.com/
    public void OpenHomePage() {
        Configuration.browser = "firefox";
        open(Url);
    }
}
