package com.applyskillproject.date;

import com.applyskillproject.date.example.MutableDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Date;

@ExtendWith(MockitoExtension.class)
public class MutableDateTest {

    @Test
    void Date_가변성_문제_테스트() {
        Date currentDate = new Date();
        MutableDate originalDate = new MutableDate(currentDate);

        Date clonedOriginalDate = (Date) originalDate.getDate().clone();

        long oneHourInMillis = 60 * 60 * 1000;
        originalDate.getDate().setTime(currentDate.getTime() + oneHourInMillis);

        Assertions.assertThat(originalDate).isNotEqualTo(clonedOriginalDate);
    }
}
