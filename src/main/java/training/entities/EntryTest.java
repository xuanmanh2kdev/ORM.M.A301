package training.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;
import training.enums.ResultType;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
public class EntryTest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "test_id", unique = true, nullable = false)
    private Integer testId;

    @Column(nullable = false)
    @NotEmpty(message = "Time is not empty")
    private String time;

    @Column(nullable = false)
    @NotNull(message = "Date is not empty")
    private LocalDate date;

    @Column(nullable = false)
    @NotNull(message = "Valuator is not empty")
    private String languageValuator;


    @Column(columnDefinition="Decimal(4,2)", nullable = false)
    @NotNull(message = "Result is not null")
    @Range(min = 0, max = 10)
    private Double languageResult;

    @Column(nullable = false)
    @NotEmpty(message = "Technical valuator is not empty")
    private String technicalValuator;

    @Column(columnDefinition="Decimal(4,2)", nullable = false)
    @NotNull(message = "Result is not null")
    @Range(min = 0, max = 10)
    private Double technicalResult;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    @NotNull(message = "Result is not empty")
    private ResultType result;

    @Column(nullable = false, length = 1000)
    @NotEmpty(message = "Remark is not empty")
    @Length(max=1000)
    private String remark;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    @ToString.Exclude
    private Candidate candidate;

    @Column(name = "entry_test_skill", nullable = false)
    @NotEmpty(message = "Entry test skill is not empty")
    private String entryTestSkill;
}
