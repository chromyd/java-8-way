package de.blogspot.soahowto.java8way;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Test;

public class OptionalMagic {

    private PersonDao personDao = new PersonDao();

    private Person nullPerson = null;

    private Person aPerson = personDao.findPersonBySsn(1).get();

    @Test(expected = NotFoundException.class)
    public void getCity() {
        personDao.findPersonBySsn(1)
                .map(Person::getResidence)
                .map(Residence::getAddress)
                .map(Address::getCity)
                .orElseThrow(NotFoundException::new);
    }

    @SuppressWarnings("serial")
    public static class NotFoundException extends RuntimeException {
    }

    public static class PersonDao {
        public Optional<Person> findPersonBySsn(int ssn) {
            switch (ssn) {
            case 1:
                return Optional.of(new Person(ssn));
            default:
                return Optional.empty();
            }
        }
    }

    public static class Person {

        public Person(int ssn) {
            super();
            this.ssn = ssn;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        private int ssn;

        private String firstName;

        private String lastName;

        private Residence residence;

        public Residence getResidence() {
            return residence;
        }

        public void setResidence(Residence residence) {
            this.residence = residence;
        }

    }

    public static class Residence {
        public LocalDate getValidFrom() {
            return validFrom;
        }

        public void setValidFrom(LocalDate validFrom) {
            this.validFrom = validFrom;
        }

        public Address getAddress() {
            return address;
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        private LocalDate validFrom;

        private Address address;
    }

    public static class Address {
        public String getStreet() {
            return street;
        }

        public void setStreet(String street) {
            this.street = street;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        private String street;

        private String city;

        private String country;
    }
}
