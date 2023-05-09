package com.mylogin.pract.repository;

import com.mylogin.pract.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface StudentRepostory extends JpaRepository<Student,Integer> {

    @Query("select s from Student s where (s.studentno like concat('%',:searchstudent,'%') or " +
            "s.name like concat('%',:searchstudent,'%'))")
    Page<Student> findAll(@Param("searchstudent") String searchstudent, Pageable of);




    @Query("select st from Student st where st.id=:id")
    Student getStudent(int id);
}
