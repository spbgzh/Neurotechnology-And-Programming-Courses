package com.epam.rd.jsp.currencies;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class Currencies {
    private Map<String, BigDecimal> curs = new TreeMap<>();

    public void addCurrency(String currency, BigDecimal weight) {
        curs.put(currency, weight);
    }

    public Collection<String> getCurrencies() {
        return curs.keySet();

    }

    public Map<String, BigDecimal> getExchangeRates(String referenceCurrency) {
        BigDecimal value = curs.get(referenceCurrency);
        Map<String, BigDecimal> map = new TreeMap<>();
        for (String i : curs.keySet()) {
            map.put(i, value.divide(curs.get(i), 5, RoundingMode.HALF_UP));
        }
        return map;
    }

    public BigDecimal convert(BigDecimal amount, String sourceCurrency, String targetCurrency) {
        BigDecimal source = curs.get(sourceCurrency);
        BigDecimal target = curs.get(targetCurrency);
        BigDecimal ans = source.divide(target, 100, RoundingMode.HALF_UP).multiply(amount);
        return ans.setScale(5, RoundingMode.HALF_UP);
    }
}
