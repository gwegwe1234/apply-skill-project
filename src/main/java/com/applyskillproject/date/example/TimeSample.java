package com.applyskillproject.date.example;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;

@Component
@RequiredArgsConstructor
public class TimeSample {

    private final Clock clock;

    private static String BLANK = " ";

    public static void main(String[] args) {
        localDate();
        localTime();
        localDateTime();
        instant();
        durationAndPeriod();
        changeTimeImmutably();
        formatting();
        zoneId();
    }

    private static void localDate() {
        System.out.println("[LocalDate]");
        // of 선언
        LocalDate date = LocalDate.of(2022, 3, 4);

        // 내장 함수 사용
        int year = date.getYear();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        DayOfWeek dow = date.getDayOfWeek();
        int len = date.lengthOfMonth();
        boolean leap = date.isLeapYear();

        System.out.println(StringUtils.join(year, BLANK, month, BLANK, day, BLANK, dow, BLANK, len, BLANK, leap));


        // ChronoField 사용
        int year2 = date.get(ChronoField.YEAR);
        int moth2 = date.get(ChronoField.MONTH_OF_YEAR);
        int day2 = date.get(ChronoField.DAY_OF_MONTH);

        System.out.println(StringUtils.join(year2, BLANK, moth2, BLANK, day2));

        // parse 선언
        LocalDate parseDate = LocalDate.parse("2023-04-05");

        System.out.println(parseDate);
    }

    private static void localTime() {
        System.out.println();
        System.out.println("[LocalTime]");
        LocalTime time = LocalTime.of(15, 23, 30);
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();

        System.out.println(StringUtils.join(hour, BLANK, minute, BLANK, second));

        // parse 선언
        LocalTime time2 = LocalTime.parse("13:45:55");
        System.out.println(time2);
    }

    private static void localDateTime() {
        System.out.println();
        System.out.println("[LocalDateTime]");
        // LocalDate + LocalTime

        LocalDate date = LocalDate.parse("2020-09-21");
        LocalTime time = LocalTime.parse("13:45:55");


        LocalDateTime dt1 = LocalDateTime.of(2020, Month.SEPTEMBER, 21, 13, 45, 55);
        LocalDateTime dt2 = LocalDateTime.of(date, time);
        LocalDateTime dt3 = date.atTime(13, 45, 55);
        LocalDateTime dt4 = date.atTime(time);
        LocalDateTime dt5 = time.atDate(date);

        System.out.println(dt1);
        System.out.println(dt2);
        System.out.println(dt3);
        System.out.println(dt4);
        System.out.println(dt5);

        // LocalDateTime -> LocalDate or LocalTime
        LocalDate date1 = dt1.toLocalDate();
        LocalTime time1 = dt1.toLocalTime();

        System.out.println(date1);
        System.out.println(time1);
    }

    private static void instant() {
        System.out.println();
        System.out.println("[Instant]");
        // 기계어가 알아보기 좋게 만든 단위
        // 유닉스 에포크 시간(1970/1/1 0시 0분 0초 UTC) 을 기준으로 특정 지점까지의 시간을 초로 표현
        // 인간이 알아보기 좋게 만든 now를 제공

        Instant instantDay = Instant.now();

        System.out.println(instantDay);

    }

    private static void durationAndPeriod() {
        System.out.println();
        System.out.println("[Duration / Period]");

        LocalDateTime dt1 = LocalDateTime.now();
        LocalDateTime dt2 = LocalDateTime.of(2020, 5, 4, 13, 5, 4);
        LocalTime time1 = LocalTime.now();
        LocalTime time2 = LocalTime.of(13, 4, 5);
        Duration d1 = Duration.between(dt1, dt2);
        Duration d2 = Duration.between(time1, time2);

        System.out.println(d1);
        System.out.println(d2);

        // Period : LocalDate 사용 가능
        Period tenDays = Period.between(LocalDate.of(2019, 9, 11)
                , LocalDate.of(2019, 9, 21));
        System.out.println(tenDays);
    }

    private static void changeTimeImmutably() {
        System.out.println();
        System.out.println("[Change Time]");

        LocalDate date1 = LocalDate.of(2020, 3, 28);
        LocalDate date2 = date1.withYear(2021);
        LocalDate date3 = date2.withDayOfMonth(30);
        LocalDate date4 = date3.with(ChronoField.MONTH_OF_YEAR, 4);
        LocalDate date5 = date4.plusWeeks(1);
        LocalDate date6 = date5.minusYears(10);
        LocalDate date7 = date6.plus(6, ChronoUnit.MONTHS);

        System.out.println(date1);
        System.out.println(date2);
        System.out.println(date3);
        System.out.println(date4);
        System.out.println(date5);
        System.out.println(date6);
        System.out.println(date7);
    }

    private static void formatting() {
        System.out.println();
        System.out.println("[Format]");

        LocalDate date = LocalDate.of(2014, 3, 18);
        String s1 = date.format(DateTimeFormatter.BASIC_ISO_DATE);
        String s2 = date.format(DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.println(s1);
        System.out.println(s2);

        LocalDate date1 = LocalDate.parse("20140318",
                DateTimeFormatter.BASIC_ISO_DATE);
        LocalDate date2 = LocalDate.parse("2014-03-18",
                DateTimeFormatter.ISO_LOCAL_DATE);

        System.out.println(date1);
        System.out.println(date2);
    }

    private static void zoneId() {
        System.out.println();
        System.out.println("[zone]");

        ZoneId romeZone = ZoneId.of("Europe/Rome");

        LocalDate date = LocalDate.of(2014, Month.MARCH, 18);
        ZonedDateTime zdt1 = date.atStartOfDay(romeZone);

        LocalDateTime dateTime = LocalDateTime.of(2014, Month.MARCH, 18, 13, 45);
        ZonedDateTime zdt2 = dateTime.atZone(romeZone);

        Instant instant = Instant.now();
        ZonedDateTime zdt3 = instant.atZone(romeZone);

        System.out.println(zdt1);
        System.out.println(zdt2);
        System.out.println(zdt3);
    }

    public LocalDate notUsingClock() {
        return LocalDate.now();
    }

    public LocalDate usingClock() {
        return LocalDate.now(clock);
    }
}
