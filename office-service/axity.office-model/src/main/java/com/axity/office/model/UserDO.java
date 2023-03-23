package com.axity.office.model;

import java.io.Serializable;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;


import javax.persistence.Table;


import lombok.Getter;
import lombok.Setter;
/**
 * Entity class of the table TBL_User
 * 
 * @author username@axity.com
 */
@Entity
@Table(name = "TBL_User")
@Getter
@Setter
public class UserDO implements Serializable
{
  private static final long serialVersionUID = 1L;


  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "cd_id")
  private Integer id;
  
  
  @Column(name = "cd_username")
  private String username;
  
  @Column(name = "cd_email")
  private String email;
  
  @Column(name = "nb_name")
  private String name;
  
  @Column(name = "nb_lastname")
  private String lastName;
  
  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinTable(name = "TRL_UserRole", joinColumns = {
    @JoinColumn(name = "cd_user", referencedColumnName = "cd_id") }, inverseJoinColumns = {
        @JoinColumn(name = "cd_role", referencedColumnName = "cd_id") })
  private List<RoleDO> roles;
  
  
  

  

  /**
   * {@inheritDoc}
   */
  @Override
  public boolean equals( Object object )
  {
    boolean isEquals = false;
    if( this == object )
    {
      isEquals = true;
    }
    else if( object != null && object.getClass().equals( this.getClass() ) )
    {
      UserDO that = (UserDO) object;

      isEquals = Objects.equals( this.id, that.id );
    }
    return isEquals;
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public int hashCode()
  {
    return Objects.hash( this.id );
  }

}
