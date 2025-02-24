package org.example.financeiro.selic.repository;

import org.example.financeiro.selic.domain.Selic;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SelicRepository extends JpaRepository<Selic, Long> {

}
