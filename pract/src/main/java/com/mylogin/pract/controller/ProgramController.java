package com.mylogin.pract.controller;

import com.mylogin.pract.model.Program;
import com.mylogin.pract.model.Student;
import com.mylogin.pract.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;

@RestController
@RequestMapping(value = "/program")
public class ProgramController {


    @Autowired
    private ProgramRepository dao;

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
    public String update(@RequestBody Program program){


        if(program!= null){

            try{

                dao.save(program);
                return "Program Update Successfully";
            }catch (Exception ex){
                return "Not Save Your Data"+ex.getMessage();
            }


        }else
            return "0";


    }



    //delete program
    @DeleteMapping
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
