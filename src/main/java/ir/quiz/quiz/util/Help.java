package ir.quiz.quiz.util;

import ir.quiz.quiz.exception.InvalidDateException;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class Help {

    public static void checkTimeIsValid(LocalDateTime startAt, LocalDateTime endAt) {
        if (startAt.isBefore(LocalDateTime.now())) {
            throw new InvalidDateException("start time is before now");
        } else if (endAt.isBefore(startAt)) {
            throw new InvalidDateException("end time is before start time");
        }
    }
}
