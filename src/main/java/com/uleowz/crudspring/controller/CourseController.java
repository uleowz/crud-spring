package com.uleowz.crudspring.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uleowz.crudspring.model.Course;
import com.uleowz.crudspring.repository.CourseRepository;

import lombok.AllArgsConstructor;

import java.util.List;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController // avisando ao spring que esta Classe terá endpoints (url)
@RequestMapping("/api/courses") // dizendo qual é o endereço(url) que ira acessar esta classe 
@AllArgsConstructor // vai criar um constructor do CourseController passando como argumento o CourseRepository.
public class CourseController {

    private final CourseRepository courseRepository;



    @GetMapping // método deve ser acionado quando chegar uma requisição HTTP GET que corresponde ao URL.
    public List<Course> list(){
        return courseRepository.findAll();
    }


    @GetMapping("/{id}")    
    public ResponseEntity<Course> findById(@PathVariable Long id){
        return courseRepository.findById(id)
        .map(record -> ResponseEntity.ok().body(record))
        .orElse(ResponseEntity.notFound().build());
    }

    //@RequestMapping(method = RequestMethod.POST);
    @PostMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Course create(@RequestBody Course course) {
        // System.out.println(course.getName());
        return courseRepository.save(course);
        // return ResponseEntity.status(HttpStatus.CREATED).body(courseRepository.save(course));
    }

    

}
