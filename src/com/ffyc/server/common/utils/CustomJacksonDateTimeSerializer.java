package com.ffyc.server.common.utils;

import java.io.IOException;
import java.sql.Timestamp;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.springframework.stereotype.Component;

@Component
public class CustomJacksonDateTimeSerializer extends JsonSerializer<Timestamp>
{

    // private static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    public void serialize(Timestamp date, JsonGenerator gen, SerializerProvider provider) throws IOException, JsonProcessingException
    {
        // String formattedDate = dateFormat.format(date);
        String formattedDate = String.valueOf(date.getTime());
        gen.writeString(formattedDate);
    }
}