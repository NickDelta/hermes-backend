package org.hua.hermes.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "application",indexes = { @Index(name = "idx_application_citizen",columnList = "created_by"),
                                        @Index(name = "idx_application_organization", columnList = "organization") })
@Getter
@Setter
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Application extends AbstractEntity {
    @NotEmpty
    @Column(name = "organization")
    private String organization;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "applicationState")
    private ApplicationState applicationState;

    @Column(name = "details")
    private String details;

    @Temporal(TemporalType.TIMESTAMP)
    @Future
    @Column(name = "appointmentDate")
    private Date appointmentDate;
}
