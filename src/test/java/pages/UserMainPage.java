//Добавить ожидания, безопасные клики

package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.awt.*;

public class UserMainPage extends BasePage {

    private static final By GOODS_BUTTON = By.xpath(".//*[@class = 'tico null' and contains(text(),'Товары')]");
    private static final By GROUPS_BUTTON = By.xpath(".//*[@class = 'tico null' and contains(text(),'Группы')]");
    private static final By MESSAGES_BUTTON = By.xpath(".//* [@id='msg_toolbar_button']");
    private static final By CHECK_PRODUCT = By.xpath(".//* [@class='mall-media-link_a']");
    private static final By COMMENT = By.xpath(".//*[@class='media-text_cnt']");

    public UserMainPage(WebDriver driver) {
        super(driver);
    }

    //Открываем страницу с товароми
    public GoodsPage openGoodsPage(){
        click(GOODS_BUTTON);
        return new GoodsPage(driver);
    }

    //открываем страницу с сообщениями
    public DialogPage openMessagesPage(){
        click(MESSAGES_BUTTON);
        return new DialogPage (driver);
    }

    //открываем репост товара со стены юзера
    public ProductPageFrame openShareFromUserMainPage(){
        click(CHECK_PRODUCT);
        return new ProductPageFrame(driver);
    }

    // открываем группы
    public GroupsPage openGroups(){
        click(GROUPS_BUTTON);
        return new GroupsPage(driver);
    }

    // открыть товар с комментом
    public ProductWithMessageFrame openProductWithComment(){
        click(COMMENT);
        return new ProductWithMessageFrame(driver);
    }

    @Override
    protected void check(WebDriver driver){
        assertTrue(driver,3,GOODS_BUTTON,"Кнопка Товары не загрузилась","Кнопка товары загрузилась");
        assertTrue(driver,3,MESSAGES_BUTTON,"Кнопка сообщения не загрузилась","Кнопка загрузилась");
        assertTrue(driver,3,GROUPS_BUTTON, "Кнопка группы не загрузилась", "Кнопка группы загрузилась");
        assertTrue(driver,3,CHECK_PRODUCT, "репост не загрузился", "Репост загрузился");
    }
}
