package com.axity.office.persistence;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.axity.office.model.RoleDO;

/**
 * Persistence interface of  de {@link com.axity.office.model.RoleDO}
 * 
 * @author username@axity.com
 */
@Repository
public interface RolePersistence extends JpaRepository<RoleDO, Integer>
{
  // Agregar consultas personalizadas
}
