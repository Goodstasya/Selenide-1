package ru.netology.rest;

import com.codeborne.selenide.SelenideElement;

import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class DeliveryCardTest {

    @Test
    void shouldSendDeliveryForm() throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date dt = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(dt);
        c.add(Calendar.DATE, 3);
        dt = c.getTime();

        String s;

        s = "Встреча успешно забронирована на " + dateFormat.format(dt);

        open("http://localhost:9999");

        //SelenideElement form = $(".form");

        $$("[data-test-id=city] .input__control").last().setValue("Самара");
        $$("[data-test-id=date] .input__control").last().setValue(dateFormat.format(dt));
        $$("[data-test-id=name] .input__control").last().setValue("Василий Пупкин");
        $$("[data-test-id=phone] .input__control").last().setValue("+79270000000");
        $$("[data-test-id=agreement]").last().click();
        $$("button").find(exactText("Забронировать")).click();
        $("[data-test-id=notification]").waitUntil(visible, 15000);
        $("[data-test-id=notification]").shouldHave(text(s));
    }


}
