package com.[%= companylower %].[%= namelower %].config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import lombok.extern.slf4j.Slf4j;

/**
 * Redis configuration class
 * 
 * @author [%= username %]
 */
@Configuration
@Slf4j
public class RedisConfiguration
{
  @Value("${application.redis.hostname:localhost}")
  private String hostname;

  @Value("${application.redis.port:6379}")
  private int port;

  @Value("${application.redis.username:}")
  private String username;

  @Value("${application.redis.password:}")
  private String password;

  @Bean
  public JedisConnectionFactory jedisConnectionFactory()
  {
    log.info( "->redis host: {}", this.hostname );
    log.info( "->redis port: {}", this.port );
    var configuration = new RedisStandaloneConfiguration( this.hostname, this.port );
    if( StringUtils.isNotBlank( this.username ) )
    {
      configuration.setUsername( username );
    }
    if( StringUtils.isNotBlank( this.password ) )
    {
      configuration.setPassword( RedisPassword.of( password ) );
    }
    return new JedisConnectionFactory( configuration );
  }

  @Bean
  public RedisTemplate<String, String> redisTemplate()
  {
    RedisTemplate<String, String> template = new RedisTemplate<>();
    template.setConnectionFactory( jedisConnectionFactory() );
    template.setKeySerializer( new StringRedisSerializer() );
    template.setValueSerializer( new StringRedisSerializer() );
    return template;
  }
}
