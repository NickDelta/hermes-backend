package org.hua.hermes.data.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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

    @NotEmpty(message = "{entity.application.organization.notempty}")
    @Column(name = "organization")
    private String organization;

    @NotNull(message = "{entity.application.state.notnull}")
    @Enumerated(EnumType.STRING)
    @Column(name = "state")
    private ApplicationState state;

    @Size(max = 1024, message = "{entity.application.details.size}")
    @Column(name = "details")
    private String details;

    @NotNull(message = "{entity.application.appointmentDate.notnull}")
    @Future(message = "{entity.application.appointmentDate.future}")
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "appointment_date")
    private Date appointmentDate;

}
