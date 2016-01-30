package com.ffyc.server.common.utils;

import java.io.IOException;
import java.util.Date;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.springframework.stereotype.Component;

@Component
public class CustomeJacksonDateSerializer extends JsonSerializer<Date>
{

    // private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void serialize(Date date, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException
    {
        // String formattedDate = dateFormat.format(date);
        String formattedDate = String.valueOf(date.getTime());
        gen.writeString(formattedDate);
    }
}