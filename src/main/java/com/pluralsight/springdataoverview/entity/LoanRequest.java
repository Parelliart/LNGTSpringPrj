package com.pluralsight.springdataoverview.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Entity
@Setter
@Getter
@Table(name = "LN_REQUEST")
public class LoanRequest {

    @Id
    @Column(name = "ID")
    private Long id;

//    @Column(name = "REQUEST_DATE")
//    @Temporal(TemporalType.TIMESTAMP)
//    @DateTimeFormat(style = "MM")
//    private Date requestDate;

//    @Column(name = "REGISTER_DATE")
//    @Temporal(TemporalType.DATE)
//    @DateTimeFormat(style = "M-")
//    private Date registerDate;

    @Column(name = "DESCRIPTION", length = 255)
    private String description;

    @Column(name = "PROCESS_TYPE_ID", precision = 10)
    private Long processTypeId;

    @Column(name = "STATUS_ID", precision = 10)
    private Long statusId;

    @Column(name = "DTYPE", precision = 2, nullable = false)
    private Integer dtype;

    @Column(name = "CONTRACT_ID", precision = 15)
    private Long contract;

//    @Column(name = "REGISTER_DATE")
//    @Temporal(TemporalType.DATE)
//    @DateTimeFormat(style = "M-")
//    private Date insert_date;

}
