package mk.ukim.finki.wp.lab.selenium;

import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
@Getter
public class OrderCartPage extends AbstractPage{

    @FindBy(css = "tr[class=cart-order]")
    private List<WebElement> cartRows;


    public OrderCartPage(WebDriver driver) {
        super(driver);
    }

    public static OrderCartPage init(WebDriver driver) {
        return PageFactory.initElements(driver, OrderCartPage.class);
    }


    public void assertElemts(int cartItemsNumber) {
        Assert.assertEquals("rows do not match", cartItemsNumber, this.getCartRows().size());
    }
}
