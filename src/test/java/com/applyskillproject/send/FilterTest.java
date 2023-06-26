package com.applyskillproject.send;

import com.applyskillproject.db.MoneyFilterRuleRepository;
import com.applyskillproject.model.FilterCondition;
import com.applyskillproject.model.MoneyFilterRule;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class FilterTest {

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    private MoneyFilterRuleRepository moneyFilterRuleRepository = new MoneyFilterRuleRepository();

    // Admin 역할
    @Test
    void 필터_전송_테스트1() {
        //given
        String url = "http://localhost:" + port + "/money";

        moneyFilterRuleRepository.save(
                MoneyFilterRule.builder()
                        .type("TYPE1")
                        .enable(true)
                        .conditionMatchType(MoneyFilterRule.MatchType.AND)
                        .conditions(
                                List.of(
                                        FilterCondition.builder()
                                                .key("key1")
                                                .keyType(FilterCondition.KeyType.PATH)
                                                .value("/money")
                                                .valueMatchType(FilterCondition.MatchType.EXACT)
                                                .build(),
                                        FilterCondition.builder()
                                                .key("key1")
                                                .keyType(FilterCondition.KeyType.METHOD)
                                                .value("GET")
                                                .valueMatchType(FilterCondition.MatchType.EXACT)
                                                .build()
                                ))
                        .build());

        //when
        String response = restTemplate.getForObject(url, String.class);

        //then
        assertTrue(response.contains("Send Money"));
    }

    @Test
    void 필터_전송_테스트2() {
        //given
        String url = "http://localhost:" + port + "/money";

        moneyFilterRuleRepository.save(
                MoneyFilterRule.builder()
                        .type("TYPE1")
                        .enable(true)
                        .conditionMatchType(MoneyFilterRule.MatchType.AND)
                        .conditions(
                                List.of(
                                        FilterCondition.builder()
                                                .key("key1")
                                                .keyType(FilterCondition.KeyType.PATH)
                                                .value("/money")
                                                .valueMatchType(FilterCondition.MatchType.EXACT)
                                                .build(),
                                        FilterCondition.builder()
                                                .key("key1")
                                                .keyType(FilterCondition.KeyType.METHOD)
                                                .value("GET")
                                                .valueMatchType(FilterCondition.MatchType.EXACT)
                                                .build()
                                ))
                        .build());

        //when
        String response = restTemplate.postForObject(url, null, String.class);

        //then
        assertTrue(response.contains("Post Money"));
    }
}
