package com.ugurukku.country.controller;

import com.ugurukku.country.models.CountryResponse;
import com.ugurukku.country.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService service;

    @GetMapping
    public List<CountryResponse> getAllAvailableCountries(@RequestParam String currency) {
        return service.getAvailableCountries(currency);
    }

}
