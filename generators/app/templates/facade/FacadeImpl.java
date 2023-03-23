package com.[%= companylower %].[%= namelower %].facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.[%= companylower %].[%= namelower %].commons.dto.[%= namecamel %]Dto;
import com.[%= companylower %].[%= namelower %].commons.request.PaginatedRequestDto;
import com.[%= companylower %].[%= namelower %].commons.response.GenericResponseDto;
import com.[%= companylower %].[%= namelower %].commons.response.PaginatedResponseDto;
import com.[%= companylower %].[%= namelower %].facade.[%= namecamel %]Facade;
import com.[%= companylower %].[%= namelower %].service.[%= namecamel %]Service;

/**
 * Class [%= namecamel %]FacadeImpl
 * @author [%= username %]
 */
@Service
@Transactional
public class [%= namecamel %]FacadeImpl implements [%= namecamel %]Facade
{
  @Autowired
  private [%= namecamel %]Service [%= entityLower %]Service;
  /**
   * {@inheritDoc}
   */
  @Override
  public PaginatedResponseDto<[%= namecamel %]Dto> find[%= namecamel %]s( PaginatedRequestDto request )
  {
    return this.[%= entityLower %]Service.find[%= namecamel %]s( request );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<[%= namecamel %]Dto> find( Integer id )
  {
    return this.[%= entityLower %]Service.find( id );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<[%= namecamel %]Dto> create( [%= namecamel %]Dto dto )
  {
    return this.[%= entityLower %]Service.create( dto );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> update( [%= namecamel %]Dto dto )
  {
    return this.[%= entityLower %]Service.update( dto );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> delete( Integer id )
  {
    return this.[%= entityLower %]Service.delete( id );
  }
}
