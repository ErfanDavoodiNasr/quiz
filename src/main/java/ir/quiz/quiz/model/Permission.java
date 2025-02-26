package ir.quiz.quiz.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;


@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@NoArgsConstructor
@Entity
@Table(name = Permission.TABLE_NAME)
public class Permission extends BaseModel<Long> {
    public static final String TABLE_NAME = "permissions";

    @Column(unique = true, nullable = false)
    private String name;
}