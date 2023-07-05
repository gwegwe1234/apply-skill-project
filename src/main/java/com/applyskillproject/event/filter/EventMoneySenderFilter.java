package com.applyskillproject.event.filter;

import com.applyskillproject.event.db.MoneyFilterRuleRepository;
import com.applyskillproject.event.model.MoneyFilterRule;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class EventMoneySenderFilter implements Filter {

    private final MoneyFilterRuleRepository moneyFilterRuleRepository = new MoneyFilterRuleRepository();

    @Override
    public void init(FilterConfig filterConfig) {
        log.info("Filter start");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        log.info(httpRequest.toString());

        List<String> successMessageList = moneyFilterRuleRepository.getRules()
                .stream()
                .filter(MoneyFilterRule::isEnable)
                .filter(moneyFilterRule -> MoneyFilterRule.checkFilterRule(moneyFilterRule, httpRequest))
                .map(moneyFilterRule -> sendKafka(httpRequest))
                .collect(Collectors.toList());

        successMessageList.forEach(log::info);

        chain.doFilter(request, response);
    }

    /**
     * Kafka로 로그를 send하는 메소드
     * 실제로 카프카에 연동하고 결과를 return 해야 하지만 간단하게 보여주기 위해 연동은 안하고 응답 패스만 리턴
     */
    private String sendKafka(HttpServletRequest httpRequest) {
        log.info("Send log message to another server using kafka : {}", httpRequest.toString());
        return httpRequest.getServletPath() + " path kafka sending success";
    }

    @Override
    public void destroy() {
        log.info("Filter end");
    }
}
