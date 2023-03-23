package com.[%= companylower %].[%= namelower %].persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.[%= companylower %].[%= namelower %].model.[%= namecamel %]DO;

/**
 * Persistence interface of  de {@link com.[%= companylower %].[%= namelower %].model.[%= namecamel %]DO}
 * 
 * @author [%= username %]
 */
@Repository
public interface [%= namecamel %]Persistence extends JpaRepository<[%= namecamel %]DO, Integer>
{
  // Agregar consultas personalizadas
}
