package mk.ukim.finki.wp.lab.selenium;

import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class BalloonPage extends AbstractPage{

    @FindBy(css = "tr[class=balloon]")
    private List<WebElement> balloonRows;


    @FindBy(css = ".delete-balloon")
    private List<WebElement> deleteButtons;


    @FindBy(className = "edit-balloon")
    private List<WebElement> editButtons;


    @FindBy(css = ".cart-order")
    private List<WebElement> cartButtons;


    @FindBy(css = ".add-product-btn")
    private List<WebElement> addBalloonButton;

    public BalloonPage(WebDriver driver) {
        super(driver);
    }

    public static BalloonPage to(WebDriver driver) {
        get(driver, "/balloons");
        return PageFactory.initElements(driver, BalloonPage.class);
    }

    public void assertElemts(int balloonsNumber, int deleteButtons, int editButtons, int cartButtons, int addButtons) {
        Assert.assertEquals("rows do not match", balloonsNumber, this.getBalloonRows().size());
        Assert.assertEquals("delete do not match", deleteButtons, this.getDeleteButtons().size());
        Assert.assertEquals("edit do not match", editButtons, this.getEditButtons().size());
        Assert.assertEquals("cart do not match", cartButtons, this.getCartButtons().size());
        Assert.assertEquals("add is visible", addButtons, this.getAddBalloonButton().size());
    }
}
