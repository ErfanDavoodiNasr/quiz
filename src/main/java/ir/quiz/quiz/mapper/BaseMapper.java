package ir.quiz.quiz.mapper;

import java.util.List;

public interface BaseMapper<T, B> {
    T convertDtoToEntity(B b);
    List<T> convertDtoToEntity(List<B> b);
    B convertEntityToDto(T t);
    List<B> convertEntityToDto(List<T> t);
}
