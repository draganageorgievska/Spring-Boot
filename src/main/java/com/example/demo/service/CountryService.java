package com.example.demo.service;

import com.example.demo.model.Country;

public interface CountryService {
    Country save(String name, String continent);
}
