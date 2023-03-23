package com.[%= companylower %].[%= namelower %].service.util;

import org.springframework.kafka.support.serializer.JsonSerializer;

import com.[%= companylower %].[%= namelower %].commons.request.MessageDto;

/**
 * Message Serializer class
 * 
 * @author [%= username %]
 */
public class MessageSerializer extends JsonSerializer<MessageDto>
{

}
