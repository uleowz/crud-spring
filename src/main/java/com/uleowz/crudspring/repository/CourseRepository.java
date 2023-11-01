package com.uleowz.crudspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uleowz.crudspring.model.Course;

@Repository // ao declarar como interface, conseguimos extender as interfaces que nós temos do proprio JPA no SpringData
public interface CourseRepository extends JpaRepository<Course, Long>{
    
}
