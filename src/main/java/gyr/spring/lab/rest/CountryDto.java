/*
 * Copyright 2016 Russian Post
 *
 * This source code is Russian Post Confidential Proprietary.
 * This software is protected by copyright. All rights and titles are reserved.
 * You shall not use, copy, distribute, modify, decompile, disassemble or reverse engineer the software.
 * Otherwise this violation would be treated by law and would be subject to legal prosecution.
 * Legal use of the software provides receipt of a license from the right name only.
 */
package gyr.spring.lab.rest;

import gyr.spring.lab.domain.Country;

/**
 * DTO that represents Account
 */
@SuppressWarnings("all")
public class CountryDto {

    private int id = -1;
    private String name;

    private String code;

    public CountryDto() {
    }

    public CountryDto(int id, String name, String code) {
        this.id = id;
        this.name = name;
        this.code = code;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }


    public static Country toDomainObject(CountryDto dto) {
        return new Country(dto.getName() , dto.getId(), dto.getName());
    }

    public static CountryDto toDto(Country country) {
        return new CountryDto( country.getId() , country.getName(), country.getCode());
    }
}
