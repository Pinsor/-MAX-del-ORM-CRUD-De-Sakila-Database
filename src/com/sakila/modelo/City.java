package com.sakila.modelo;

public class City extends Entity {
    private String cityName;
    private Country country;

    public City(int id, String cityName, Country country) {
        this.id = id;
        this.cityName = cityName;
        this.country = country;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
