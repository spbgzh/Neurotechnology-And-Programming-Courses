package com.epam.rd.autocode.domain;

import java.io.IOException;
import java.io.StringWriter;
import java.math.BigInteger;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class Department {

    private final BigInteger id;
    private final String name;
    private final String location;

    @JsonCreator
    public Department(@JsonProperty("id") final BigInteger id,
                    @JsonProperty("fullName") final String name,
                    @JsonProperty("position") final String location) {
        this.id = id;
        this.name = name;
        this.location = location;
    }

    public BigInteger getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final Department that = (Department) o;
        return Objects.equal(id, that.id) &&
                Objects.equal(name, that.name) &&
                Objects.equal(location, that.location);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id, name, location);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", id)
                .add("name", name)
                .add("location", location)
                .toString();
    }


    public static class Parser {
        private static ObjectMapper mapper = new ObjectMapper();
        static {
            mapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            mapper.registerModule(new JavaTimeModule());
            mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
            mapper.configure(DeserializationFeature.FAIL_ON_MISSING_CREATOR_PROPERTIES, false);
        }

        public static String toJson(Department department){
            try {
                final StringWriter writer = new StringWriter();
                mapper.writeValue(writer, department);
                return writer.toString();
            } catch (IOException exc) {
                throw new RuntimeException(exc);
            }
        }

        public static Department parseJson(String json){
            try {
                return mapper.readValue(json, Department.class);
            } catch (IOException exc) {
                throw new RuntimeException(exc);
            }
        }
    }
}
