package com.mylogin.pract.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name = "student_has_program")
@AllArgsConstructor
@NoArgsConstructor
public class Studenthasprogram {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Basic(optional = false)
    private Integer id;



    @JoinColumn(name = "student_id",referencedColumnName = "id")
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    @JsonIgnore
    private Student student_id;


    @JoinColumn(name = "program_id",referencedColumnName = "id")
    @ManyToOne(optional = false,fetch = FetchType.EAGER)
    private Program program_id;


    @Column(name = "registerdate")
    @Basic(optional = false)
    private LocalDate registerdate;







}
