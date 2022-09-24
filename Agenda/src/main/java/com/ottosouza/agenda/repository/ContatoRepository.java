package com.ottosouza.agenda.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ottosouza.agenda.entity.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Integer> {

}
