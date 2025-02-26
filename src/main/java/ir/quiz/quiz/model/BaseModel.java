package ir.quiz.quiz.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;


@SuperBuilder
@Data
@NoArgsConstructor
@MappedSuperclass
public abstract class BaseModel<ID extends Number> implements Serializable {
    @Id
    @GeneratedValue
    private ID id;
}
