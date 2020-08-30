package ua.skillsup.practice;

import ua.skillsup.practice.service.ExampleService;
import ua.skillsup.practice.service.ExampleServiceImpl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Map;

public class Main {

    public static void main(String[] args) {

        ExampleService exampleService = new ExampleServiceImpl();

        exampleService.addNewItem("item1", new BigDecimal("22.355").setScale(2, RoundingMode.HALF_UP));
        exampleService.addNewItem("item2", new BigDecimal("22.352").setScale(2, RoundingMode.HALF_UP));
        exampleService.addNewItem("item3", new BigDecimal("22.345").setScale(2, RoundingMode.HALF_UP));
        exampleService.addNewItem("item4", new BigDecimal("22.3").setScale(2, RoundingMode.HALF_UP));
        exampleService.addNewItem("item4", new BigDecimal("23.38").setScale(2, RoundingMode.HALF_UP));

        Map<LocalDate, BigDecimal> map = exampleService.getStatistic();
        for (Map.Entry<LocalDate, BigDecimal> entry : map.entrySet()) {
            System.out.println(entry.getKey());
            System.out.println(entry.getValue());
        }

    }
}
