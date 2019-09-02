package com.itsarraisi.oracle.springboot.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDER")
public class Order {
    @Id
    @Column(name = "ID")
    private String id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_CUSTOMER", referencedColumnName = "ID")
    private Customer customer;

    @Column(name = "DATE")
    private String date;
}
