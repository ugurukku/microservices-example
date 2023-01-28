package com.ugurukku.payment.client;

import com.ugurukku.payment.models.client.CountryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(name = "country",url = "${client.country.endpoint}")
public interface CountryClient {

    @GetMapping("/countries")
    List<CountryDto> getAllAvailableCountries(@RequestParam String currency);



}
