package com.mylogin.pract.repository;

import com.mylogin.pract.model.Program;
import com.mylogin.pract.model.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProgramRepository extends JpaRepository<Program,Integer> {

    @Query("select p from Program p where (p.programno like concat('%',:searchprogramno,'%') or " +
            "p.name like concat('%',:searchprogramno,'%'))")
    Page<Program> findAll(@Param("searchprogramno") String searchprogramno, Pageable of);





}
