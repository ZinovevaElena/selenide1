import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class CardDeliveryTest {
    private String generateDate(int addDays, String pattern) {
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }

    @Test
    void shouldCardDeliveryTest() {
        open("http://localhost:9999");
        $("data-test-id=city").setValue("Санкт-Петербург");
        $("data-test-id=date").doubleClick().sendKeys(generateDate(5, "dd.MM.yyyy"));
        $("data-test-id=name").setValue("Иванова Маргарита");
        $("data-test-id=phone").setValue("+79850000000");
        $("data-test-id=agreement").click();
        $("button").click();
        $(withText("Успешно!")).shouldBe(Condition.visible, Duration.ofSeconds(15));
        $(withText("Встреча успешно забронирована на")).shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
}
