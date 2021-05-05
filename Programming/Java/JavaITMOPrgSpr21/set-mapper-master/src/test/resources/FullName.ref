package com.epam.rd.autocode.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class FullName {
    private final String firstName;
    private final String lastName;
    private final String middleName;

    @JsonCreator
    public FullName(@JsonProperty("firstName") final String firstName,
                    @JsonProperty("lastName") final String lastName,
                    @JsonProperty("middleName") final String middleName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        final FullName fullName = (FullName) o;
        return Objects.equal(firstName, fullName.firstName) &&
                Objects.equal(lastName, fullName.lastName) &&
                Objects.equal(middleName, fullName.middleName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(firstName, lastName, middleName);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("firstName", firstName)
                .add("lastName", lastName)
                .add("middleName", middleName)
                .toString();
    }
}
