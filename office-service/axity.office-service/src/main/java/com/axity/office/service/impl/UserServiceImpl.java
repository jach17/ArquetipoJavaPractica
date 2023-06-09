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

import com.axity.office.commons.dto.RoleDto;
import com.axity.office.commons.dto.UserDto;
import com.axity.office.commons.enums.ErrorCode;
import com.axity.office.commons.exception.BusinessException;
import com.axity.office.commons.request.MessageDto;
import com.axity.office.commons.request.PaginatedRequestDto;
import com.axity.office.commons.response.GenericResponseDto;
import com.axity.office.commons.response.HeaderDto;
import com.axity.office.commons.response.PaginatedResponseDto;
import com.axity.office.model.UserDO;
import com.axity.office.model.QUserDO;
import com.axity.office.model.RoleDO;
import com.axity.office.persistence.RolePersistence;
import com.axity.office.persistence.UserPersistence;
import com.axity.office.service.UserService;
import com.github.dozermapper.core.Mapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;

import lombok.extern.slf4j.Slf4j;

/**
 * Class UserServiceImpl
 * 
 * @author username@axity.com
 */
@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {
  @Autowired
  private UserPersistence userPersistence;
  @Autowired
  private RolePersistence rolePersistence;

  @Autowired
  private Mapper mapper;

  /**
   * {@inheritDoc}
   */
  @Override
  public PaginatedResponseDto<UserDto> findUsers(PaginatedRequestDto request) {
    log.debug("%s", request);

    int page = request.getOffset() / request.getLimit();
    Pageable pageRequest = PageRequest.of(page, request.getLimit(), Sort.by("id"));

    var paged = this.userPersistence.findAll(pageRequest);

    var result = new PaginatedResponseDto<UserDto>(page, request.getLimit(), paged.getTotalElements());

    paged.stream().forEach(x -> result.getData().add(this.transform(x)));

    return result;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<UserDto> find(Integer id) {
    GenericResponseDto<UserDto> response = null;

    var optional = this.userPersistence.findById(id);
    if (optional.isPresent()) {
      response = new GenericResponseDto<>();
      response.setBody(this.transform(optional.get()));
    }

    return response;
  }

  private GenericResponseDto<UserDto> getGenericResponse(int error, String message) {
    GenericResponseDto<UserDto> genericResponse = new GenericResponseDto<>();
    genericResponse.setHeader(new HeaderDto(error, message));
    return genericResponse;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<UserDto> create(UserDto dto) {
    if (existUsername(dto.getUsername())) {
      return getGenericResponse(ErrorCode.USERNAME_ALREADY_EXISTS.getCode(), "Error. Username already exist.");
    }

    if (existEmail(dto.getEmail())) {
      return getGenericResponse(ErrorCode.EMAIL_ALREADY_EXISTS.getCode(), "Error. Email already exist.");
    }

    if (dto.getRoles() == null) {
      return getGenericResponse(ErrorCode.NOT_ROLE_SELECTED.getCode(), "Error. You must select at least one rol.");
    }

    if (isRolesEmpty(dto.getRoles())) {
      return getGenericResponse(ErrorCode.NOT_ROLE_SELECTED.getCode(), "Error. You must select at least one rol.");
    }

    boolean roleNotExist = dto.getRoles().stream()
        .filter(role -> !existRole(role.getId()))
        .findAny()
        .isPresent();

    if (roleNotExist) {
      return getGenericResponse(ErrorCode.ROLE_NOT_FOUND.getCode(), "Error. Role selected does not exist.");
    }

    UserDO entity = new UserDO();
    this.mapper.map(dto, entity);
    entity.setId(null);

    var roles = new ArrayList<RoleDO>();
    entity.setRoles(roles);

    dto.getRoles().stream().forEach(r -> {
      entity.getRoles().add(this.rolePersistence.findById(r.getId()).get());
    });

    this.userPersistence.save(entity);
    dto.setId(entity.getId());
    return new GenericResponseDto<>(dto);
  }

  /**
   * 
   * @param username
   */
  private boolean isRolesEmpty(List<RoleDto> roles) {
    return roles.isEmpty();
  }

  /**
   * 
   * @param email
   */
  private boolean existEmail(String email) {
    return (this.userPersistence.findByEmail(email).isPresent());
  }

  /**
   * 
   * @param username
   */
  private boolean existUsername(String username) {
    return (this.userPersistence.findByUsername(username).isPresent());
  }

  /**
   * 
   * @param id
   */
  private boolean existRole(Integer id) {
    return this.rolePersistence.findById(id).isPresent();
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> update(UserDto dto) {
    var optional = this.userPersistence.findById(dto.getId());
    if (optional.isEmpty()) {
      throw new BusinessException(ErrorCode.OFFICE_NOT_FOUND);
    }

    var entity = optional.get();

    entity.setUsername(dto.getUsername());
    entity.setEmail(dto.getEmail());
    entity.setName(dto.getName());
    entity.setLastName(dto.getLastName());
    // TODO: Actualizar entity.Roles (?)

    this.userPersistence.save(entity);

    return new GenericResponseDto<>(true);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public GenericResponseDto<Boolean> delete(Integer id) {
    var optional = this.userPersistence.findById(id);
    if (optional.isEmpty()) {
      throw new BusinessException(ErrorCode.OFFICE_NOT_FOUND);
    }

    var entity = optional.get();
    this.userPersistence.delete(entity);

    return new GenericResponseDto<>(true);
  }

  private UserDto transform(UserDO entity) {
    UserDto dto = null;
    if (entity != null) {
      dto = this.mapper.map(entity, UserDto.class);
    }
    return dto;
  }
}
