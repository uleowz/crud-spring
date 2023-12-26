package com.uleowz.crudspring.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.uleowz.crudspring.model.Course;
import com.uleowz.crudspring.service.CourseService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


import java.util.List;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Validated // Controller vai validar todas as validações do Java Bean ou do Hibernate/Validation.
@RestController // avisando ao spring que esta Classe terá endpoints (url)
@RequestMapping("/api/courses") // dizendo qual é o endereço(url) que ira acessar esta classe 
// @AllArgsConstructor // vai criar um constructor do CourseController passando como argumento o CourseRepository.
public class CourseController {

    private final CourseService courseService;

    public CourseController(CourseService courseService){
        this.courseService = courseService;
    }



    @GetMapping // método deve ser acionado quando chegar uma requisição HTTP GET que corresponde ao URL.
    public List<Course> list(){
        return courseService.list();
    }


    @GetMapping("/{id}")    
    public ResponseEntity<Course> findById(@PathVariable @NotNull @Positive Long id){
        return courseService.findById(id)
        .map(recordFound -> ResponseEntity.ok().body(recordFound))
        .orElse(ResponseEntity.notFound().build());
    }

    //@RequestMapping(method = RequestMethod.POST);
    @PostMapping
    @ResponseStatus(code = HttpStatus.ACCEPTED)
    public Course create(@RequestBody @Valid Course course) {
        return courseService.create(course);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Course> update(@PathVariable @NotNull @Positive Long id, @RequestBody @Valid Course course){
        
        return courseService.update(id, course)
        .map(recordFound -> ResponseEntity.ok().body(recordFound))
        .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable @NotNull @Positive Long id){
        if (courseService.delete(id)) {
            return ResponseEntity.noContent().<Void>build();
        }
        return ResponseEntity.notFound().build();
    }
    

}
