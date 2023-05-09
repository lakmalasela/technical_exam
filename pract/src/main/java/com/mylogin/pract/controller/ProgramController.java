package com.mylogin.pract.controller;

import com.mylogin.pract.model.Program;
import com.mylogin.pract.model.Student;
import com.mylogin.pract.repository.ProgramRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/program")
public class ProgramController {


    @Autowired
    private ProgramRepository dao;

    private final Logger logger = LoggerFactory.getLogger((ProgramController.class));

    //pagination
    //program/findAll?page=0&size=1
    @GetMapping(value = "/findAll",params = {"page","size"},produces = "application/json")
    public Page<Program> findAll(@RequestParam("page") int page, @RequestParam("size") int size){


        return dao.findAll(PageRequest.of(page,size, Sort.Direction.DESC,"id"));


    }


    //search program number
    //program/findAll?page=0&size=1&searchprogramno=S000000005
    @GetMapping(value = "/findAll",params = {"page","size","searchprogramno"},produces = "application/json")
    public Page<Program>findAll(@RequestParam("page") int page, @RequestParam("size") int size,@RequestParam("searchprogramno") String searchprogramno){

        return dao.findAll(searchprogramno,PageRequest.of(page,size, Sort.Direction.DESC,"id"));

    }


    //insert program
    @PostMapping
    public String insert(@RequestBody Program program){

        if(program!= null ){
            try{

                dao.save(program);
                return "Program Added Successfull";
            }catch (Exception ex){
                return "Not Save Your Data"+ex.getMessage();
            }

        }else
            return "0";


    }



    //update program

    @PutMapping
    @CachePut(cacheNames = "students",key = "#program.id" )
    public String update(@RequestBody Program program){


        if(program!= null){

            try{

                dao.save(program);
                logger.debug("==> StudentController : /studentupdate: :",program.toString());
                return "Program Update Successfully";

            }catch (Exception ex){
                return "Not Save Your Data"+ex.getMessage();
            }


        }else
            return "0";


    }




    //delete program
    @DeleteMapping
    @CacheEvict(cacheNames = "students",allEntries = false,key = "#id")
    public String delete(@RequestParam int id){

        if(id>0){

            try{
                dao.deleteById(id);
                return "Programe Delete Successfully";

            }catch (Exception ex){
                return "Not Save Your Data"+ex.getMessage();
            }


        }else{
            return "0";
        }


    }


}
