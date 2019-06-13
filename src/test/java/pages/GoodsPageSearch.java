package pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import wrappers.GoodsPageWrapper;

import java.util.List;
import java.util.Random;

public class GoodsPageSearch extends BasePage implements IGoodsPage{

    private static final By ORDERS = By.xpath(".//*[@class='filter_ac' and contains(text(), 'Мои заказы')]");
    private static final By USER_PAGE = By.xpath(".//*[@class='toolbar_logo_img']");
    private static final By PRODUCT_CARD = By.xpath(".//*[@class='ugrid_i']");
    private static final By COLOR = By.xpath(".//*[@class='mall-card_choose-item']");
    private static final By SORT = By.xpath(".//*[@id='mallsortby']");
    private static final By CHEAP = By.xpath(".//*[@value='PRICE_ASC']");
    private static final By EXPENSIVE = By.xpath(".//*[@value='PRICE_DSC']");

    public GoodsPageSearch(WebDriver driver) {
        super(driver);
    }

    // проверка наличия кнопок
    @Override
    protected void check(WebDriver driver) {
        Assert.assertTrue("Не дождались кнопки 'Мои заказы'",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(ORDERS)));

        Assert.assertTrue("Не дождались кнопки перехода на страницу пользователя",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(USER_PAGE)));

        Assert.assertTrue("Не дождались кнопки сортировки",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(SORT)));

        Assert.assertTrue("Не дождались кнопки сначала дешевые",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(CHEAP)));

        Assert.assertTrue("Не дождались кнопки сначала дорогие",
                new WebDriverWait(driver, 10).
                        until((ExpectedCondition<Boolean>) d -> isElementPresent(EXPENSIVE)));
    }

    // выбор цвета
    public String chooseColor(){
        click(COLOR);
        List<WebElement> elements = driver.findElements(COLOR);
        WebElement element = elements.get(new Random().nextInt(elements.size()));
        final String number = element.getCssValue("background-color");
        return number;
    }

    // выбор способа сортировки товаров сначала дешевые
    public void chooseSortCheap(){
        click(SORT);
        click(CHEAP);
    }

    // выбор способа сортировки товаров сначала дорогие
    public void chooseSortExpensive(){
        click(SORT);
        click(EXPENSIVE);
    }

    // получение обёрнутых товаров
    public List<GoodsPageWrapper> getGetProducts(){
        List<WebElement> elements = driver.findElements(PRODUCT_CARD);
        return wrappers.GoodsPageTransformer.wrapProducts(elements,driver);
    }

    @Override
    public MyOrdersPage openMyOrders() {
        click(ORDERS);
        return new MyOrdersPage(driver);
    }

    @Override
    public UserMainPage openUserMainPage() {
        click(USER_PAGE);
        return new UserMainPage(driver);
    }

}