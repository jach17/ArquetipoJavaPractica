package com.axity.office.facade.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.axity.office.commons.dto.CountryDto;
import com.axity.office.commons.request.PaginatedRequestDto;
import com.axity.office.commons.response.GenericResponseDto;
import com.axity.office.commons.response.PaginatedResponseDto;
import com.axity.office.facade.CountryFacade;
import com.axity.office.service.CountryService;

/**
 * Class CountryFacadeImpl
 * @author username@axity.com
 */
@Service
@Transactional
public class CountryFacadeImpl implements CountryFacade
{
  @Autowired
  private CountryService countryService;
  /**
   * {@inheritDoc}
   */
  @Override
  public PaginatedResponseDto<CountryDto> findCountrys( PaginatedRequestDto request )
  {
    return this.countryService.findCountrys( request );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<CountryDto> find( Integer id )
  {
    return this.countryService.find( id );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<CountryDto> create( CountryDto dto )
  {
    return this.countryService.create( dto );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> update( CountryDto dto )
  {
    return this.countryService.update( dto );
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> delete( Integer id )
  {
    return this.countryService.delete( id );
  }
}
