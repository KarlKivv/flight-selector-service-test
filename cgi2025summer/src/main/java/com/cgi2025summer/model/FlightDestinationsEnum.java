package com.cgi2025summer.model;

public enum FlightDestinationsEnum {
    RIGA("Riga", 50, "LV", "Latvia"),
    VILNIUS("Vilnius", 70, "LT", "Lithuania"),
    HELSINKI("Helsinki", 30, "FI", "Finland"),
    WARSAW("Warsaw", 100, "PL", "Poland"),
    STOCKHOLM("Stockholm", 65, "SE", "Sweden"),
    OSLO("Oslo", 90, "NO", "Norway"),
    COPENHAGEN("Copenhagen", 95, "DK", "Denmark"),
    BERLIN("Berlin", 115, "DE", "Germany"),
    LONDON("London", 180, "GB", "United Kingdom"),
    PARIS("Paris", 180, "FR", "France"),
    AMSTERDAM("Amsterdam", 150, "NL", "Netherlands"),
    ROME("Rome", 195, "IT", "Italy"),
    PRAGUE("Prague", 3 * 60 + 10, "CZ", "Czechia"),
    TIRANA("Tirana", 4 * 60 + 45, "AL", "Albania"),
    ANDORRA_LA_VELLA("Andorra la Vella", 3 * 60 + 55, "AD", "Andorra"),
    YEREVAN("Yerevan", 6 * 60 + 55, "AM", "Armenia"),
    VIENNA("Vienna", 2 * 60 + 25, "AT", "Austria"),
    BAKU("Baku", 6 * 60 + 55, "AZ", "Azerbaijan"),
    BRUSSELS("Brussels", 2 * 60 + 40, "BE", "Belgium"),
    SARAJEVO("Sarajevo", 4 * 60 + 35, "BA", "Bosnia & Herzegovina"),
    SOFIA("Sofia", 4 * 60 + 55, "BG", "Bulgaria"),
    ZAGREB("Zagreb", 4 * 60, "HR", "Croatia"),
    NICOSIA("Nicosia", 5 * 60 + 35, "CY", "Cyprus"),
    TBILISI("Tbilisi", 6 * 60 + 15, "GE", "Georgia"),
    ATHENS("Athens", 3 * 60 + 35, "GR", "Greece"),
    BUDAPEST("Budapest", 2 * 60 + 20, "HU", "Hungary"),
    REYKJAVIK("Reykjavík", 3 * 60 + 45, "IS", "Iceland"),
    DUBLIN("Dublin", 3 * 60 + 15, "IE", "Ireland"),
    VADUZ("Vaduz", 2 * 60 + 55, "LI", "Liechtenstein"),
    LUXEMBOURG("Luxembourg", 4 * 60 + 30, "LU", "Luxembourg"),
    VALLETTA("Valletta", 4 * 60 + 5, "MT", "Malta"),
    CHISINAU("Chisinau", 4 * 60 + 55, "MD", "Moldova"),
    PODGORICA("Podgorica", 7 * 60, "ME", "Montenegro"),
    SKOPJE("Skopje", 6 * 60 + 45, "MK", "North Macedonia"),
    LISBON("Lisbon", 6 * 60 + 5, "PT", "Portugal"),
    BUCHAREST("Bucharest", 4 * 60 + 20, "RO", "Romania"),
    BELGRADE("Belgrade", 5 * 60, "RS", "Serbia"),
    BRATISLAVA("Bratislava", 24 * 60 + 11 * 60, "SK", "Slovakia"),
    LJUBLJANA("Ljubljana", 3 * 60 + 40, "SI", "Slovenia"),
    MADRID("Madrid", 5 * 60 + 40, "ES", "Spain"),
    BERN("Bern", 10 * 60 + 5, "CH", "Switzerland"),
    ANKARA("Ankara", 6 * 60 + 10, "TR", "Türkiye"),
    ;

    private String locationName;
    private long flightDurationMinutes;
    private String countryISOCode;
    private String country;

    private FlightDestinationsEnum(String locationName, long flightDurationMinutes, String countryISOCode,
            String country) {
        this.locationName = locationName;
        this.flightDurationMinutes = flightDurationMinutes;
        this.countryISOCode = countryISOCode;
        this.country = country;
    }

    public long getFlightDuration() {
        return this.flightDurationMinutes;
    }

    @Override
    public String toString() {
        return this.locationName;
    }
}
