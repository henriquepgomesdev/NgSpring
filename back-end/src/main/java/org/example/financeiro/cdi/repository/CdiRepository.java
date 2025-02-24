package org.example.financeiro.cdi.repository;

import org.example.financeiro.cdi.domain.Cdi;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CdiRepository extends JpaRepository<Cdi, Long> {

}
