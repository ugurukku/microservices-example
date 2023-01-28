package com.ugurukku.country.service;

import com.ugurukku.country.models.CountryResponse;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CountryService {

    public List<CountryResponse> getAvailableCountries(String currency){
        if (currency.equals(Currencies.USD.name())){
            return List.of(
                    new CountryResponse("USA", BigDecimal.valueOf(500)),
                    new CountryResponse("GER", BigDecimal.valueOf(600)),
                    new CountryResponse("GEO", BigDecimal.valueOf(700)));
        }

        return new ArrayList<>();

    }

}
