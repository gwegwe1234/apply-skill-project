package com.applyskillproject.model;

import lombok.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FilterCondition {
    public enum KeyType {
        PATH, METHOD, HEADER, QUERY
    }

    public enum MatchType {
        PREFIX, SUFFIX, EXACT
    }

    private KeyType keyType;
    private String key;
    @Builder.Default
    private MatchType valueMatchType = MatchType.EXACT;
    private String value;

    public Boolean isMatch(HttpServletRequest request) {
        switch (keyType) {
            case PATH:
                return isMatchWithType(valueMatchType, request.getServletPath(), value);
            case METHOD:
                return isMatchWithType(valueMatchType, request.getMethod().toUpperCase(), value.toUpperCase());
            case HEADER:
                return isMatchWithType(valueMatchType, request.getHeader(key), value);
            case QUERY:
            default:
                return isMatchWithType(valueMatchType, request.getParameter(key), value);
        }
    }

    private Boolean isMatchWithType(MatchType valueMatchType, String control, String value) {
        return Optional.ofNullable(control)
                .map(c -> {
                    switch (valueMatchType) {
                        case PREFIX:
                            return c.startsWith(value);
                        case SUFFIX:
                            return c.endsWith(value);
                        case EXACT:
                        default:
                            return c.equals(value);
                    }
                })
                .orElse(false);
    }
}
