package com.mylogin.pract.controller;


import com.mylogin.pract.model.Student;
import com.mylogin.pract.model.Studenthasprogram;
import com.mylogin.pract.repository.StudentRepostory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RestController
@RequestMapping(value = "/student")
public class StudentController {


    private final Logger logger = LoggerFactory.getLogger((StudentController.class));


    @Autowired
    private StudentRepostory dao;

    //pagination
    //student/findAll?page=0&size=1
    @GetMapping(value = "/findAll",params = {"page","size"},produces = "application/json")
    public Page<Student> findAll(@RequestParam("page") int page, @RequestParam("size") int size){


        return dao.findAll(PageRequest.of(page,size, Sort.Direction.DESC,"id"));


    }


    //search student number
    //student/findAll?page=0&size=1&searchstudent=S000000005
    @GetMapping(value = "/findAll",params = {"page","size","searchstudent"},produces = "application/json")
    public Page<Student>findAll(@RequestParam("page") int page, @RequestParam("size") int size,@RequestParam("searchstudent") String searchstudent){

        return dao.findAll(searchstudent,PageRequest.of(page,size, Sort.Direction.DESC,"id"));

    }

    //insert student
    @PostMapping
    public String insert(@RequestBody Student student){


        if(student!= null  ){
            try{

                for(Studenthasprogram shp: student.getStudenthasprogramList())
                    shp.setStudent_id(student);


                dao.save(student);
                return "Student Addedd Successfully";
            }catch (Exception ex){
                return "Not Save Your Data"+ex.getMessage();
            }

        }else
            return "0";



    }

    //update student
    @PutMapping
    public String update(@RequestBody Student student){

        if(student!= null){

            try{


                for(Studenthasprogram shp: student.getStudenthasprogramList())
                    shp.setStudent_id(student);


                dao.save(student);
                return "Student Update Successfully";
            }catch (Exception ex){
                return "Not Save Your Data"+ex.getMessage();
            }


        }else
            return "0";



    }
    //student delete
    @DeleteMapping
    public String delete(@RequestParam int id){

        if(id>0){

            try{
                dao.deleteById(id);
                return "Delete Successfully";

            }catch (Exception ex){
                return "Not Save Your Data"+ex.getMessage();
            }


        }else{
            return "0";
        }


    }

    //get by student
    //student/getstudent?id=1
    @GetMapping(value = "/getstudent",params = {"id"},produces = "application/json")
    @Cacheable(cacheNames  = "Students ",key = "#id")
    public Student removeemployeeList(@RequestParam("id") int id){
        logger.debug("==> StudentController : /student/{} call :",id);
        return dao.getStudent(id);
    }






}
