package com.applyskillproject.model;

import lombok.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MoneyFilterRule {
    private String type;
    private MatchType conditionMatchType;
    private List<FilterCondition> conditions;
    private boolean enable;

    public enum MatchType {
        AND, OR
    }

    public static boolean checkFilterRule(MoneyFilterRule moneyFilterRule, HttpServletRequest httpRequest) {
        switch (moneyFilterRule.getConditionMatchType()) {
            case OR:
                return hasAnyMatch(moneyFilterRule.getConditions(), httpRequest);
            case AND:
            default:
                return hasAllMatch(moneyFilterRule.getConditions(), httpRequest);
        }
    }

    private static boolean hasAnyMatch(List<FilterCondition> conditions, HttpServletRequest httpRequest) {
        return conditions.stream().anyMatch(filterCondition -> filterCondition.isMatch(httpRequest));
    }

    private static boolean hasAllMatch(List<FilterCondition> conditions, HttpServletRequest httpRequest) {
        return conditions.stream().allMatch(filterCondition -> filterCondition.isMatch(httpRequest));
    }
}
