package ru.netology.rest;

import com.codeborne.selenide.SelenideElement;

import org.junit.jupiter.api.Test;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryCardTest {

    @Test
        void shouldSendDeliveryForm(){
        open("http://localhost:9999");

        SelenideElement form = $(".form");

        form.$("[data-test-id=city] .input__control").setValue("Самара");

        form.$("[data-test-id=date] .input__control").setValue("02.08.2021");

        form.$("[data-test-id=name] .input__control").setValue("Василий Пупкин");

        form.$("[data-test-id=phone] .input__control").setValue("+79270000000");

        form.$("[data-test-id=agreement]").click();form.$("[role=button]").click();

        $("[data-test-id=order-success]").shouldHave(exactText(
                "  Успешно! "
                        + "Встреча успешно забронирована в ближайшее время."));
    }


}
