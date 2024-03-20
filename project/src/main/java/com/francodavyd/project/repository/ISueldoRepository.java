package com.francodavyd.project.repository;

import com.francodavyd.project.model.Sueldo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ISueldoRepository extends JpaRepository<Sueldo, Long> {
}
