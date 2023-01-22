package mk.ukim.finki.wp.lab.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

public class AddOrEditBalloon extends AbstractPage{

    private WebElement color;
    private WebElement desc;
    private WebElement manufacturer;
    private WebElement submit;

    public AddOrEditBalloon(WebDriver driver) {
        super(driver);
    }

    public static BalloonPage addBalloon(WebDriver driver, String color,  String desc, String manufacturer) {
        get(driver, "/balloons/add-form");
        AddOrEditBalloon addOrEditProduct = PageFactory.initElements(driver, AddOrEditBalloon.class);
        addOrEditProduct.color.sendKeys(color);
        addOrEditProduct.desc.sendKeys(desc);
        addOrEditProduct.manufacturer.click();
        addOrEditProduct.manufacturer.findElement(By.xpath("//option[. = '" + manufacturer + "']")).click();

        addOrEditProduct.submit.click();
        return PageFactory.initElements(driver, BalloonPage.class);
    }

    public static BalloonPage editBalloon(WebDriver driver, WebElement editButton, String color, String desc, String manufacturer) {
        editButton.click();
        System.out.println(driver.getCurrentUrl());
        AddOrEditBalloon addOrEditProduct = PageFactory.initElements(driver, AddOrEditBalloon.class);
        addOrEditProduct.color.sendKeys(color);
        addOrEditProduct.desc.sendKeys(desc);
        addOrEditProduct.manufacturer.click();
        addOrEditProduct.manufacturer.findElement(By.xpath("//option[. = '" + manufacturer + "']")).click();

        addOrEditProduct.submit.click();
        return PageFactory.initElements(driver, BalloonPage.class);
    }

}
