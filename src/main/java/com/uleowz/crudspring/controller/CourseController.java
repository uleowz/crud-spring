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
import org.springframework.web.bind.annotation.PutMapping;
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
        .map(recordFound -> ResponseEntity.ok().body(recordFound))
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


    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable Long id, @RequestBody Course course){
        
        return courseRepository.findById(id).map(recordFound -> {
            recordFound.setName(course.getName());
            recordFound.setCategory(course.getCategory());
            Course updated = courseRepository.save(recordFound); // JPA/Hibernate sabe que já tem ID, então internamente acontece um update ao invés de insert.
            return ResponseEntity.ok().body(updated);

        }).orElse(ResponseEntity.notFound().build());
    }

    

}
