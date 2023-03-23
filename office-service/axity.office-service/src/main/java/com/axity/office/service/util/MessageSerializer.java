package com.axity.office.service.util;

import org.springframework.kafka.support.serializer.JsonSerializer;

import com.axity.office.commons.request.MessageDto;

/**
 * Message Serializer class
 * 
 * @author username@axity.com
 */
public class MessageSerializer extends JsonSerializer<MessageDto>
{

}
