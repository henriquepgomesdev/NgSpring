package org.example.cdi.repository;

import org.example.cdi.domain.Selic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SelicRepository extends JpaRepository<Selic, Long> {

}
