package com.example.covidretrofitapp;


public class CovidClass {
    String cases;
    String todayCases;
    String deaths;
    String todayDeaths;
    String recovered;
    String todayRecovered;
    String active;
    String todayActive;
    String country;

    public CovidClass(String cases, String todayCases, String deaths, String todayDeaths, String recovered, String todayRecovered, String active,String todayActive, String country){
        this.cases=cases;
        this.active=active;
        this.todayRecovered=todayRecovered;
        this.todayCases=todayCases;
        this.deaths=deaths;
        this.country=country;
        this.recovered=recovered;
        this.todayDeaths=todayDeaths;
        this.todayActive=todayActive;
    }

    public void setTodayActive(String todayActive) {
        this.todayActive = todayActive;
    }

    public String getTodayActive() {
        return todayActive;
    }
    public String getCases() {
        return cases;
    }

    public void setCases(String cases) {
        this.cases = cases;
    }

    public String getTodayCases() {
        return todayCases;
    }

    public void setTodayCases(String todayCases) {
        this.todayCases = todayCases;
    }

    public String getDeaths() {
        return deaths;
    }

    public void setDeaths(String deaths) {
        this.deaths = deaths;
    }

    public String getTodayDeaths() {
        return todayDeaths;
    }

    public void setTodayDeaths(String todayDeaths) {
        this.todayDeaths = todayDeaths;
    }

    public String getRecovered() {
        return recovered;
    }

    public void setRecovered(String recovered) {
        this.recovered = recovered;
    }

    public String getTodayRecovered() {
        return todayRecovered;
    }

    public void setTodayRecovered(String todayRecovered) {
        this.todayRecovered = todayRecovered;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }


}
