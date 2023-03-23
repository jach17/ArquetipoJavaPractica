package com.[%= companylower %].[%= namelower %].commons.util;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Configuration class
 * 
 * @author [%= username %]
 */
@Configuration
@PropertySource("classpath:message.properties")
public class MessagePropertiesConfig
{

}
