package com.example.springbootwebtutorial.springbootwebtutorial.repositories;

import com.example.springbootwebtutorial.springbootwebtutorial.entities.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity,Long> {

}
