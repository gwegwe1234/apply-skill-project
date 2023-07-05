package com.applyskillproject.date;

import com.applyskillproject.date.example.TimeSample;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;

import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class LocalDateCompareTest {

    @Mock
    Clock clock;

    @InjectMocks
    TimeSample timeSample;

    @Test
    void 타임_비교_테스트_Clock_미사용() {
        String now = "2022-05-15";

        Assertions.assertThat(LocalDate.of(2022, 5, 15)).isNotEqualTo(timeSample.notUsingClock());
    }

    @Test
    void 타임_비교_테스트_Clock_사용() {
        String now = "2022-12-15T10:15:00Z";

        Clock fixedClock = Clock.fixed(Instant.parse(now), ZoneOffset.ofHours(9));
        doReturn(fixedClock.instant()).when(clock).instant();
        doReturn(fixedClock.getZone()).when(clock).getZone();

        Assertions.assertThat(LocalDate.of(2022, 12, 15)).isEqualTo(timeSample.usingClock());

    }
}
