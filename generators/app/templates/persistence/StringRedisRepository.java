package com.[%= companylower %].[%= namelower %].persistence;

import java.time.Duration;
import java.time.temporal.ChronoUnit;
import java.util.Set;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

/**
 * Class String Redis Repository
 * 
 * @author [%= username %]
 */
@Component
public class StringRedisRepository
{
  @Autowired
  private RedisTemplate<String, String> redisTemplate;

  @Resource(name = "redisTemplate")
  private ValueOperations<String, String> redisData;

  @Value("${application.redis.ttl:600}")
  private long defaultTtl;

  /**
   * Adds a redis pair key, value with the default time to live
   * 
   * @param key
   * @param value
   */
  public void add( String key, String value )
  {
    this.add( key, value, this.defaultTtl );
  }

  /**
   * Adds a redis pair key,value with a given time to live
   * 
   * @param key
   * @param value
   * @param ttl
   */
  public void add( String key, String value, long ttl )
  {
    this.redisTemplate.opsForValue().set( key, value );
    this.redisTemplate.expire( key, Duration.of( ttl, ChronoUnit.SECONDS ) );
  }

  /**
   * Gets the value given its key
   * 
   * @param key
   * @return
   */
  public String get( String key )
  {
    return this.redisTemplate.opsForValue().get( key );
  }

  /**
   * Checks if a given key has been set
   * 
   * @param key
   * @return
   */
  public boolean hasKey( String key )
  {
    return this.redisTemplate.hasKey( key );
  }

  /**
   * Gets the keys by a pattern
   * 
   * @param patternKey
   * @return
   */
  public Set<String> getKeys( String patternKey )
  {
    return this.redisTemplate.keys( patternKey );
  }

  /**
   * Deletes a key
   * 
   * @param key
   */
  public void delete( String key )
  {
    this.redisTemplate.opsForValue().getOperations().delete( key );
  }
}
