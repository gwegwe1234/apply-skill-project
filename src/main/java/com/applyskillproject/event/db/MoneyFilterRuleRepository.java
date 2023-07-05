package com.applyskillproject.event.db;

import com.applyskillproject.event.model.MoneyFilterRule;

import java.util.ArrayList;
import java.util.List;

public class MoneyFilterRuleRepository {
    public static List<MoneyFilterRule> rules = new ArrayList<>();

    public void save(MoneyFilterRule moneyFilterRule) {
        rules.add(moneyFilterRule);
    }

    public List<MoneyFilterRule> getRules() {
        return rules;
    }
}
