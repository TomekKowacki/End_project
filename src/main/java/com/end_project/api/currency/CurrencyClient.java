package com.end_project.api.currency;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class CurrencyClient {

    private final RestTemplate restTemplate;
    private final CurrencyConfig currencyConfig;

    public CurrencyDto getDollarRate() {

        URI url = UriComponentsBuilder
                .fromHttpUrl(currencyConfig.getCurrencyApiEndPoint() + currencyConfig.getDollar())
                .build()
                .encode()
                .toUri();

        CurrencyDto dollar = restTemplate.getForObject(url, CurrencyDto.class);
        return dollar;
    }

    public CurrencyDto getEuroRate() {

        URI url = UriComponentsBuilder
                .fromHttpUrl(currencyConfig.getCurrencyApiEndPoint() + currencyConfig.getEuro())
                .build()
                .encode()
                .toUri();

        CurrencyDto euro = restTemplate.getForObject(url, CurrencyDto.class);
        return euro;
    }
}
