package ru.pearx.carbide.jackson;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import ru.pearx.carbide.DomainedName;

import java.io.IOException;

/*
 * Created by mrAppleXZ on 27.04.18 16:48.
 */
public class ModuleCarbide extends SimpleModule
{
    public ModuleCarbide()
    {
        super("CarbideLib-Jackson");
        addSerializer(DomainedName.class, new StdSerializer<DomainedName>(DomainedName.class)
        {
            @Override
            public void serialize(DomainedName value, JsonGenerator gen, SerializerProvider provider) throws IOException
            {
                gen.writeString(value.toString());
            }
        });
        addDeserializer(DomainedName.class, new StdDeserializer<DomainedName>(DomainedName.class)
        {
            @Override
            public DomainedName deserialize(JsonParser p, DeserializationContext ctxt) throws IOException
            {
                return new DomainedName(p.getValueAsString());
            }
        });
    }
}