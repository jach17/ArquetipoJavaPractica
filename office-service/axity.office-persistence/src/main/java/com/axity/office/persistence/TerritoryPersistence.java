package com.axity.office.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axity.office.model.TerritoryDO;

/**
 * Persistence interface of  de {@link com.axity.office.model.TerritoryDO}
 * 
 * @author username@axity.com
 */
@Repository
public interface TerritoryPersistence extends JpaRepository<TerritoryDO, Integer>
{
  // Agregar consultas personalizadas
}
