package com.ffyc.server.common.utils;

import java.sql.Timestamp;
import java.util.Date;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ser.CustomSerializerFactory;

public class CustomJacksonObjectMapper extends ObjectMapper
{

    public CustomJacksonObjectMapper()
    {
        CustomSerializerFactory factory = new CustomSerializerFactory();
        factory.addSpecificMapping(Date.class, new CustomeJacksonDateSerializer());
        factory.addSpecificMapping(java.sql.Date.class, new CustomeJacksonDateSerializer());
        factory.addSpecificMapping(Timestamp.class, new CustomJacksonDateTimeSerializer());
        this.setSerializerFactory(factory);
    }

}