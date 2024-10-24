package net.lahlalia.emp_api.entities;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@Entity
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Pointage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPoi;

    @NotNull(message = "100")
    private LocalDateTime dateEntre;

    @NotNull(message = "101")
    private LocalDateTime dateSortie;
    @ManyToOne
    private Employe employe;

    @CreatedDate
    @Column(nullable = false, updatable = false)
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime lastModifiedDate;

    @Column(nullable = false)
    private boolean archived = false;
}
