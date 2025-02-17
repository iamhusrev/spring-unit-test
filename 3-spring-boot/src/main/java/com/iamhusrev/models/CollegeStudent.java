package com.iamhusrev.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CollegeStudent implements Student {
    private int id;
    private String firstname;
    private String lastname;
    private String emailAddress;
    private StudentGrades studentGrades;

    public CollegeStudent(String firstname, String lastname, String emailAddress) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.emailAddress = emailAddress;
    }

    @Override
    public String studentInformation() {
        return getFullName() + " " + getEmailAddress();
    }

    @Override
    public String getFullName() {
        return getFirstname() + " " + getLastname();
    }

    private String getFirstNameAndId() {
        return getFirstname() + " " + getId();
    }

    @Override
    public String toString() {
        return "CollegeStudent{" +
            "id=" + id +
            ", firstname='" + firstname + '\'' +
            ", lastname='" + lastname + '\'' +
            ", emailAddress='" + emailAddress + '\'' +
            ", studentGrades=" + studentGrades +
            '}';
    }
}
