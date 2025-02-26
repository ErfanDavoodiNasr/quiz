package ir.quiz.quiz.util;

import ir.quiz.quiz.exception.InvalidDateException;
import lombok.experimental.UtilityClass;

import java.time.LocalDateTime;

@UtilityClass
public class Help {

    public static void checkTimeIsValid(LocalDateTime startAt, LocalDateTime endAt) {
        if (
                startAt.isBefore(LocalDateTime.now()) || endAt.isBefore(startAt)
        ) {
            throw new InvalidDateException("please enter valid time");
        }
    }
}
