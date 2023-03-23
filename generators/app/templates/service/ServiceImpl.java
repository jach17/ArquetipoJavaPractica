package com.[%= companylower %].[%= namelower %].service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.[%= companylower %].[%= namelower %].commons.dto.[%= namecamel %]Dto;
import com.[%= companylower %].[%= namelower %].commons.enums.ErrorCode;
import com.[%= companylower %].[%= namelower %].commons.exception.BusinessException;
import com.[%= companylower %].[%= namelower %].commons.request.MessageDto;
import com.[%= companylower %].[%= namelower %].commons.request.PaginatedRequestDto;
import com.[%= companylower %].[%= namelower %].commons.response.GenericResponseDto;
import com.[%= companylower %].[%= namelower %].commons.response.PaginatedResponseDto;
import com.[%= companylower %].[%= namelower %].model.[%= namecamel %]DO;
import com.[%= companylower %].[%= namelower %].model.Q[%= namecamel %]DO;
import com.[%= companylower %].[%= namelower %].persistence.[%= namecamel %]Persistence;
import com.[%= companylower %].[%= namelower %].service.[%= namecamel %]Service;
import com.github.dozermapper.core.Mapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;

import lombok.extern.slf4j.Slf4j;

/**
 * Class [%= namecamel %]ServiceImpl
 * 
 * @author [%= username %]
 */
@Service
@Transactional
@Slf4j
public class [%= namecamel %]ServiceImpl implements [%= namecamel %]Service
{
  @Autowired
  private [%= namecamel %]Persistence [%= entityLower %]Persistence;

  @Autowired
  private Mapper mapper;
  
  /**
   * {@inheritDoc}
   */
  @Override
  public PaginatedResponseDto<[%= namecamel %]Dto> find[%= namecamel %]s( PaginatedRequestDto request )
  {
    log.debug( "%s", request );

    int page = request.getOffset() / request.getLimit();
    Pageable pageRequest = PageRequest.of( page, request.getLimit(), Sort.by( "id" ) );

    var paged = this.[%= entityLower %]Persistence.findAll( pageRequest );

    var result = new PaginatedResponseDto<[%= namecamel %]Dto>( page, request.getLimit(), paged.getTotalElements() );

    paged.stream().forEach( x -> result.getData().add( this.transform( x ) ) );

    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<[%= namecamel %]Dto> find( Integer id )
  {
    GenericResponseDto<[%= namecamel %]Dto> response = null;

    var optional = this.[%= entityLower %]Persistence.findById( id );
    if( optional.isPresent() )
    {
      response = new GenericResponseDto<>();
      response.setBody( this.transform( optional.get() ) );
    }

    return response;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<[%= namecamel %]Dto> create( [%= namecamel %]Dto dto )
  {

    [%= namecamel %]DO entity = new [%= namecamel %]DO();
    this.mapper.map( dto, entity );
    entity.setId(null);

    this.[%= entityLower %]Persistence.save( entity );

    dto.setId(entity.getId());

    return new GenericResponseDto<>( dto );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> update( [%= namecamel %]Dto dto )
  {
    var optional = this.[%= entityLower %]Persistence.findById( dto.getId() );
    if( optional.isEmpty() )
    {
      throw new BusinessException( ErrorCode.[%= nameupper %]_NOT_FOUND );
    }

    var entity = optional.get();
    [% properties.forEach(function(property) { %]
    [% if (!property.primaryKey) { %][% if (property.update) { %]entity.set[%= property.name %]( dto.get[%= property.name %]() );[% } else { %]// TODO: Actualizar entity.[%= property.name %] (?) [% } %][% } }); %]

    this.[%= entityLower %]Persistence.save( entity );

    return new GenericResponseDto<>( true );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> delete( Integer id )
  {
    var optional = this.[%= entityLower %]Persistence.findById( id );
    if( optional.isEmpty() )
    {
      throw new BusinessException( ErrorCode.[%= nameupper %]_NOT_FOUND );
    }

    var entity = optional.get();
    this.[%= entityLower %]Persistence.delete( entity );

    return new GenericResponseDto<>( true );
  }

  private [%= namecamel %]Dto transform( [%= namecamel %]DO entity )
  {
    [%= namecamel %]Dto dto = null;
    if( entity != null )
    {
      dto = this.mapper.map( entity, [%= namecamel %]Dto.class );
    }
    return dto;
  }
}
