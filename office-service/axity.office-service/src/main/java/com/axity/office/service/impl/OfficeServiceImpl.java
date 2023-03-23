package com.axity.office.service.impl;

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

import com.axity.office.commons.dto.OfficeDto;
import com.axity.office.commons.enums.ErrorCode;
import com.axity.office.commons.exception.BusinessException;
import com.axity.office.commons.request.MessageDto;
import com.axity.office.commons.request.PaginatedRequestDto;
import com.axity.office.commons.response.GenericResponseDto;
import com.axity.office.commons.response.PaginatedResponseDto;
import com.axity.office.model.OfficeDO;
import com.axity.office.model.QOfficeDO;
import com.axity.office.persistence.OfficePersistence;
import com.axity.office.service.OfficeService;
import com.github.dozermapper.core.Mapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;

import lombok.extern.slf4j.Slf4j;

/**
 * Class OfficeServiceImpl
 * 
 * @author username@axity.com
 */
@Service
@Transactional
@Slf4j
public class OfficeServiceImpl implements OfficeService
{
  @Autowired
  private OfficePersistence officePersistence;

  @Autowired
  private Mapper mapper;
  
  /**
   * {@inheritDoc}
   */
  @Override
  public PaginatedResponseDto<OfficeDto> findOffices( PaginatedRequestDto request )
  {
    log.debug( "%s", request );

    int page = request.getOffset() / request.getLimit();
    Pageable pageRequest = PageRequest.of( page, request.getLimit(), Sort.by( "id" ) );

    var paged = this.officePersistence.findAll( pageRequest );

    var result = new PaginatedResponseDto<OfficeDto>( page, request.getLimit(), paged.getTotalElements() );

    paged.stream().forEach( x -> result.getData().add( this.transform( x ) ) );

    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<OfficeDto> find( Integer id )
  {
    GenericResponseDto<OfficeDto> response = null;

    var optional = this.officePersistence.findById( id );
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
  public GenericResponseDto<OfficeDto> create( OfficeDto dto )
  {

    OfficeDO entity = new OfficeDO();
    this.mapper.map( dto, entity );
    entity.setId(null);

    this.officePersistence.save( entity );

    dto.setId(entity.getId());

    return new GenericResponseDto<>( dto );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> update( OfficeDto dto )
  {
    var optional = this.officePersistence.findById( dto.getId() );
    if( optional.isEmpty() )
    {
      throw new BusinessException( ErrorCode.OFFICE_NOT_FOUND );
    }

    var entity = optional.get();
    
    
    entity.setCity( dto.getCity() );
    entity.setPhone( dto.getPhone() );
    entity.setAddressLine1( dto.getAddressLine1() );
    entity.setAddressLine2( dto.getAddressLine2() );
    entity.setState( dto.getState() );
    // TODO: Actualizar entity.Country (?) 
    entity.setPostalCode( dto.getPostalCode() );

    this.officePersistence.save( entity );

    return new GenericResponseDto<>( true );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> delete( Integer id )
  {
    var optional = this.officePersistence.findById( id );
    if( optional.isEmpty() )
    {
      throw new BusinessException( ErrorCode.OFFICE_NOT_FOUND );
    }

    var entity = optional.get();
    this.officePersistence.delete( entity );

    return new GenericResponseDto<>( true );
  }

  private OfficeDto transform( OfficeDO entity )
  {
    OfficeDto dto = null;
    if( entity != null )
    {
      dto = this.mapper.map( entity, OfficeDto.class );
    }
    return dto;
  }
}
