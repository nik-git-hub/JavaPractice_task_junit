package ua.skillsup.practice.service;

import ua.skillsup.practice.dao.ExampleDao;
import ua.skillsup.practice.dao.ExampleDaoImpl;
import ua.skillsup.practice.dao.entity.ExampleEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ExampleServiceImpl implements ExampleService {
    public static final int MIN_NUMBER_CHARACTERS = 3;
    public static final int MAX_NUMBER_CHARACTERS = 20;
    public static final BigDecimal LOWEST_LIMIT_PRICE = BigDecimal.valueOf(15.00);
    private long id;

    public ExampleDao exampleDao = new ExampleDaoImpl();

    public void addNewItem(String title, BigDecimal price) {
        Boolean result;
        if (title.trim().length() < MIN_NUMBER_CHARACTERS
                || title.trim().length() > MAX_NUMBER_CHARACTERS
                || price.compareTo(LOWEST_LIMIT_PRICE) < 0)
        {
            result = false;
        }
        result = exampleDao.store(new ExampleEntity(++id, title.trim(), price));
    }

    public Map<LocalDate, BigDecimal> getStatistic() {
        List<ExampleEntity> list = exampleDao.findAll();

        Map<LocalDate, Double> mapValueDouble = list
                .stream()
                .collect(Collectors.groupingBy((s) -> s.getDateIn().atZone(ZoneOffset.UTC).toLocalDate(),
                        Collectors.averagingDouble((p) -> (p.getPrice().doubleValue()))));

        return mapValueDouble.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        e -> BigDecimal.valueOf(e.getValue()).setScale(2, RoundingMode.HALF_UP)));
    }

}
