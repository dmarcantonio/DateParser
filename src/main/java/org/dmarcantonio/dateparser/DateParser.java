package org.dmarcantonio.dateparser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * Created by davidmarcantonio on 16-06-15.
 */
public class DateParser {

    public static void main(String... args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter date you want to parse: ");
        String date = scanner.next();

        System.out.println("Parsed: " + parsePubDate(date));
    }

    private static Long parsePubDate(String pubDate) {

        if(pubDate != null && !pubDate.trim().isEmpty()){
            try {
                String format = determineDateFormat(pubDate);
                if(format == null){
                    return null;
                }
                System.out.println("Format detected: " + format);
                SimpleDateFormat df = new SimpleDateFormat(format);
                Date date = df.parse(pubDate);
                return date.getTime();
            } catch(ParseException pe) {
                System.out.println("Failed to parse. Not setting date");
                pe.printStackTrace();
            }
        }

        return null;
    }

    private static final String[] DATE_FORMATS = {
            "yyyy-MM-dd'T'HH:mm:ss'Z'",   "yyyy-MM-dd'T'HH:mm:ssZ",
            "yyyy-MM-dd'T'HH:mm:ss",      "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "yyyy-MM-dd'T'HH:mm'Z'",
            "yyyy-MM-dd HH:mm:ss",        "yyyy-MM-dd",
            "yyyy/MM/dd",
            "MM/dd/yyyy HH:mm:ss",        "MM/dd/yyyy'T'HH:mm:ss.SSS'Z'",
            "MM/dd/yyyy'T'HH:mm:ss.SSSZ", "MM/dd/yyyy'T'HH:mm:ss.SSS",
            "MM/dd/yyyy'T'HH:mm:ssZ",     "MM/dd/yyyy'T'HH:mm:ss",
            "MM/dd/yyyy",                 "EEE, d MMM yyyy HH:mm:ss z",
            "yyyy:MM:dd HH:mm:ss",        "yyyyMMddHHmmss",
            "yyyyMMdd",                   "EEE MMM dd HH:mm:ss z yyyy"
    };

    public static String determineDateFormat(String d) throws ParseException {
        if (d != null) {
            for (String parse : DATE_FORMATS) {
                SimpleDateFormat sdf = new SimpleDateFormat(parse);
                try {
                    sdf.parse(d);
                    return parse;
                } catch (ParseException e) {

                }
            }
            System.out.println("Date format not found. Sorry :(");
        }
        return null;
    }
}
