package com.itsarraisi.oracle.springboot.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ORDER_LINE")
public class OrderLine {
    @Id
    @Column(name = "ID")
    private String id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_ORDER", referencedColumnName = "ID")
    private Order idOrder;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_PRODUCT", referencedColumnName = "ID")
    private Product idProduct;

    @Column(name = "AMOUNT")
    private Integer amount;

    @Column(name = "PURCHASE_PRICE")
    private BigDecimal purchasePrice;
}
