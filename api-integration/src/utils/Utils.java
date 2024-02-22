package utils;

import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.HOURS;

public class Utils {
    public static boolean isSameHour(LocalDateTime timestamp,
                                     LocalDateTime timestampToCompare) {
        return timestamp.truncatedTo(HOURS)
                .isEqual(timestampToCompare.truncatedTo(HOURS));
    }
}
