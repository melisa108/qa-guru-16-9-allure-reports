package allure.homework;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import qa.guru.allure.WebSteps;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepsTest {

    public static final String REPOSITORY = "melisa108/qa-guru-16-9-allure-reports";
    public static final int ISSUENUMBER = 1;

    @Test
    @DisplayName("Тест на лямбда шаги через step (name, () -> {})")
    public void testLambdaStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открываем главную страницу", () -> {
            open("https://github.com");
        });

        step("Ищем репозиторий" + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").setValue(REPOSITORY);
            $(".header-search-input").submit();
        });

        step("Переходим в репозиторий " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });

        step("Открываем таб Issues", () -> {
            $("#issues-tab").click();
        });

        step("Проверяем наличие номера Issue " + ISSUENUMBER, () -> {
            $(withText("#" + ISSUENUMBER)).should(Condition.visible);
        });

    }

    @Test
    @DisplayName("Тест на шаги с аннотацией @Step")
    public void testAnnotatedStep() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        allure.homework.WebStepsTest steps = new WebStepsTest();

        steps.openMainPage();
        steps.searchForRepository(REPOSITORY);
        steps.clickOnRepositoryLink(REPOSITORY);
        steps.openIssuesTab();
        steps.shouldSeeIssueWithText(ISSUENUMBER);
    }
}
