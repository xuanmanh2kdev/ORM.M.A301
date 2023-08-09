package training.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import java.time.LocalDate;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@DynamicUpdate
public class Candidate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_id", unique = true, nullable = false)
    private Integer candidateId;

    @Column(nullable = false)
    @NotEmpty(message = "Full name is not empty")
    private String fullName;

    @Column(nullable = false)
    @NotNull(message = "Date of birth is not empty")
    private LocalDate dateOfBirth;

    @Column(nullable = false)
    @NotNull(message = "Gender is not null")
    @Range(min = 0, max = 1)
    private Integer gender;

    @Column(nullable = false)
    @NotNull(message = "Graduation year is not empty")
    private LocalDate graduationYear;

    @Column(unique = true, nullable = false)
    @NotEmpty(message = "Phone is not empty")
    @Pattern(regexp = "^\\d{10}$")
    private String phone;

    @Column(unique = true, nullable = false)
    @NotEmpty(message = "Email is not empty")
    private String email;

    @Column(nullable = false)
    @NotEmpty(message = "Skill is not empty")
    private String skill;

    @Column(nullable = false)
    @NotEmpty(message = "Foreign language is not empty")
    private String foreignLanguage;

    @Column(nullable = false)
    @NotNull(message = "Level is not null")
    @Range(min = 1, max = 7)
    private Integer level;

    @Column(nullable = false)
    @NotEmpty(message = "cv is not empty")
    private String cv;

    @Column(nullable = false)
    @NotNull(message = "Allocation status is not empty")
    private Integer allocationStatus;

    @Column(nullable = false, length = 1000)
    @NotEmpty(message = "Remark is not empty")
    @Length(max=1000)
    private String remark;

    @OneToMany(mappedBy = "candidate", orphanRemoval=true, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @ToString.Exclude
    private Set<Interview> interviews;

    @OneToMany(mappedBy = "candidate", orphanRemoval=true, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @ToString.Exclude
    private Set<EntryTest> entryTests;
}
