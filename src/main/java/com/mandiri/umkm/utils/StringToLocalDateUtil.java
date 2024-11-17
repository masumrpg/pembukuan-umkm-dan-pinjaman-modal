package com.mandiri.umkm.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StringToLocalDateUtil {
    public static LocalDate convertToLocalDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return LocalDate.parse(dateString, formatter);
    }

}