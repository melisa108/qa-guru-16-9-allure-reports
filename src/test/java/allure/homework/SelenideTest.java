package allure.homework;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.openqa.selenium.By.linkText;

public class SelenideTest {

    @Test
    @DisplayName("Тест на чистый Selenide (с Listener)")
    public void testIssueSearch() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://github.com");

        $(".header-search-input").click();
        $(".header-search-input").setValue("melisa108/qa-guru-16-9-allure-reports");
        $(".header-search-input").submit();
        $(linkText("melisa108/qa-guru-16-9-allure-reports")).click();
        $("#issues-tab").click();
        $(withText("#1")).should(Condition.visible);
    }
}