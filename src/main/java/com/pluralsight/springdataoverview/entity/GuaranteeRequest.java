package com.pluralsight.springdataoverview.entity;

import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "GUARANTEE_REQUEST")
public class GuaranteeRequest {
    @Id
    @GeneratedValue()
    @Column(name = "id", precision = 12)
    @NotNull
    private Long id;

    @Column(name = "STAKEHOLDER_NUMBER", length = 20)
    private String STAKEHOLDER_NUMBER;

    @Column(name = "STAKEHOLDER_DATE")
    private LocalDateTime STAKEHOLDER_DATE;

    @Column(name = "STAKEHOLDER_SUBJECT", length = 50)
    private String STAKEHOLDER_SUBJECT;

    @Column(name = "SEPAM_CODE", length = 16)
    private String SEPAM_CODE;

    @Column(name = "CUSTOMS_GUARANTEE_NUMBER", length = 20)
    private String CUSTOMS_GUARANTEE_NUMBER;

    @Column(name = "IS_IRIB_CUSTOMS", precision = 1)
    private Long IS_IRIB_CUSTOMS;

    @Column(name = "GUARANTEE_TYPE", precision = 12)
    private Long GUARANTEE_TYPE;

}
