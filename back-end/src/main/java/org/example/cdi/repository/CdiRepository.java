package org.example.cdi.repository;

import org.example.cdi.domain.Cdi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CdiRepository extends JpaRepository<Cdi, Long> {

}
