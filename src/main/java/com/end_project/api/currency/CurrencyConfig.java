package com.end_project.api.currency;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class CurrencyConfig {

    @Value("http://api.nbp.pl/api/exchangerates/rates/a/")
    private String currencyApiEndPoint;
    @Value("usd")
    private String dollar;
    @Value("eur")
    private String euro;
}
