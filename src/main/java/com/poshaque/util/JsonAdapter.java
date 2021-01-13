package com.poshaque.util;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class JsonAdapter {
	
	private static final ObjectMapper MAPPER = new ObjectMapper();

    public String unmarshal(final Map<?, ?> jsonList) throws Exception {
        return MAPPER.writeValueAsString(jsonList);
    }

    public Map<?, ?> marshal(final String jsonString) throws Exception {

        try {
            return MAPPER.readValue(jsonString, new TypeReference<Map<?, ?>>() {
            });
        } catch (final IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
