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
    TBILISI("Tbilisi", 6 * 60 + 15, "", "Georgia"),
    ATHENS("Athens", 3 * 60 + 35, "", "Greece"),
    BUDAPEST("Budapest", 2 * 60 + 20, "", "Hungary"),
    REYKJAVIK("Reykjavík", 3 * 60 + 45, "", "Iceland"),
    DUBLIN("Dublin", 3 * 60 + 15, "", "Ireland"),
    // PRISTINA,
    // VADUZ,
    // LUXEMBOURG,
    // VALLETTA,
    // CHISINAU,
    // MONACO,

    // PODGORICA,
    // SKOPJE,
    // LISBON,
    // BUCHAREST,
    // SAN_MARINO,
    // BELGRADE,
    // BRATISLAVA,
    // LJUBLJANA,
    // MADRID,
    // BERN,
    // ANKARA,
    // KYIV,
    // VATICAN_CITY
    ;

    private String locationName;
    private long flightDuration;

    private FlightDestinationsEnum(String locationName, long flightDurationMinutes, String countryISOCode,
            String country) {
    }
}
