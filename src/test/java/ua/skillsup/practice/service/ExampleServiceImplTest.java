package ua.skillsup.practice.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import ua.skillsup.practice.dao.entity.ExampleEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;


@RunWith(MockitoJUnitRunner.class)
public class ExampleServiceImplTest {
    private static final int MIN_NUMBER_CHARACTERS = 3;
    private static final int MAX_NUMBER_CHARACTERS = 20;
    private static final BigDecimal LOWEST_LIMIT_PRICE = BigDecimal.valueOf(15.00);
    private static final BigDecimal LOWER_LIMIT_PRICE = BigDecimal.valueOf(14.00);


    private static final String TITLE = "table";
    private static final BigDecimal PRICE = BigDecimal.valueOf(35.545);

//    @Mock
//    private ExampleServiceImpl exampleService;
//
//    @Test
//    public void shouldNotPassAddNewItemWhenLowerLimitPriceCrossed(){
//      exampleService.addNewItem(TITLE,LOWER_LIMIT_PRICE);
//      int size = exampleService.exampleDao.findAll().size();
//      assertEquals(0, size);
//    }

    @Test
    public void shouldRetrieveSavedValueFromDB() {
        //GIVEN
        ExampleServiceImpl service = new ExampleServiceImpl();
        service.addNewItem(TITLE, PRICE);
        //WHEN
        List<ExampleEntity> list = service.exampleDao.findAll();
        long actualNumber = list.stream().filter(e -> e.getTitle().equals(TITLE)).count();
        //THEN
        assertEquals(1, actualNumber);
    }

    @Test
    public void shouldBeUniqueTitleInDB() {
        //GIVEN
        ExampleServiceImpl service = new ExampleServiceImpl();
        service.addNewItem(TITLE, PRICE);
        service.addNewItem(TITLE, PRICE);
        //WHEN
        List<ExampleEntity> list = service.exampleDao.findAll();
        long actualNumber = list.stream().filter(e -> e.getTitle().equals(TITLE)).count();
        //THEN
        assertEquals(1, actualNumber);
    }

    @Test
    public void shouldBeCorrectStatistic() {
        //GIVEN
        ExampleServiceImpl service = new ExampleServiceImpl();
        service.addNewItem("computer", BigDecimal.valueOf(200.0));
        service.addNewItem("mouse", BigDecimal.valueOf(100.0));
        //WHEN
        Map<LocalDate, BigDecimal> actualStatistic = service.getStatistic();
        //THEN
        Boolean isResult = actualStatistic.containsValue(new BigDecimal("150.00").setScale(2, RoundingMode.HALF_UP));
        assertEquals(true, isResult);
    }
}
