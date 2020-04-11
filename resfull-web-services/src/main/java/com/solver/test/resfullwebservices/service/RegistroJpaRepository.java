package com.solver.test.resfullwebservices.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistroJpaRepository extends JpaRepository<Registro, Long>{

}
