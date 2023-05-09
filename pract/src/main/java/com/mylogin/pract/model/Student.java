package com.mylogin.pract.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Table(name = "student")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Student implements Serializable {

    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @Column(name = "studentno")
    @Basic(optional = false)
    private String studentno;


    @Column(name = "name")
    @Basic(optional = false)
    private String name;


    @Column(name = "address")
    @Basic(optional = false)
    private String address;

    @Column(name = "contact")
    @Basic(optional = false)
    private String contact;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "student_id",fetch = FetchType.LAZY,orphanRemoval = true)//orphanRemoval = true is allow the innerForm operations(list eken ain krnna wage eewa)
    private List<Studenthasprogram> studenthasprogramList;

    public Student(String studentno){
        this.studentno = studentno;
    }


}
