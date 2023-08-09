package training.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import training.enums.ResultType;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@ToString
public class Interview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "interview_id", unique = true, nullable = false)
    private Integer interviewId;

    @Column(nullable = false)
    @NotEmpty(message = "Time is not empty")
    private String time;

    @Column(nullable = false)
    @NotNull(message = "Date is not empty")
    private LocalDate date;

    @Column(nullable = false)
    @NotEmpty(message = "Interviewer is not empty")
    private String interviewer;

    @Column(nullable = false, length = 2000)
    @NotEmpty(message = "Comments is not empty")
    @Length(max=2000)
    private String comments;

    @Column(nullable = false)
    @NotNull(message = "Interview result is not empty")
    private ResultType interviewResult;

    @Column(nullable = false, length = 1000)
    @NotEmpty(message = "Remark is not empty")
    @Length(max=1000)
    private String remark;

    @ManyToOne
    @JoinColumn(name = "candidate_id")
    @ToString.Exclude
    private Candidate candidate;
}
