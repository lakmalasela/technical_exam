package com.mylogin.pract.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "program")
@AllArgsConstructor
@NoArgsConstructor
public class Program implements Serializable {
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @Column(name = "programno")
    @Basic(optional = false)
    private String programno;


    @Column(name = "name")
    @Basic(optional = false)
    private String name;


    @Column(name = "duration")
    @Basic(optional = false)
    private String duration;

    @Column(name = "cost")
    @Basic(optional = false)
    private BigDecimal cost;
}
