package com.amazon.demo.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author Anand B S
 * @date 05 Jul 2021
 */
public class DateUtil {

    public static String getDateTime(){
        LocalDateTime dateTime= LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        return formatter.format(dateTime);
    }

}
