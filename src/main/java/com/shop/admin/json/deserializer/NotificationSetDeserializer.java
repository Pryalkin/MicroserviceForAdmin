package com.shop.admin.json.deserializer;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.shop.admin.model.Notification;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

public class NotificationSetDeserializer extends StdDeserializer<Set<Notification>> {

    public NotificationSetDeserializer() {
        this(null);
    }

    public NotificationSetDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Set<Notification> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
        return new HashSet<>();
    }
}