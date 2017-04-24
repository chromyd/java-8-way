package de.blogspot.soahowto.java8way;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.Test;

public class OptionalMagic {

    private PersonDao personDao = new PersonDao();

    private Person person = personDao.findPersonBySsn(1).get();

    @Test(expected = NullPointerException.class)
    public void sinCity() {
        person.getResidence().getAddress().getCity();
    }

    @Test
    public void sinCity_A_DameToKillFor() {
        if (person != null) {
            Residence residence = person.getResidence();
            if (residence != null) {
                Address address = residence.getAddress();
                if (address != null) {
                    address.getCity();
                }
            }
        }
    }

    @Test(expected = NotFoundException.class)
    public void cityOfGod() {
        personDao.findPersonBySsn(1)
                .map(Person::getResidence)
                .map(Residence::getAddress)
                .map(Address::getCity)
                .orElseThrow(NotFoundException::new);
    }

    @Test(expected = NotFoundException.class)
    public void cityOfGod_10YearsLater() {
        personDao.findPersonBySsn(1)
                .map(Person::getResidence)
                .map(Residence::getAddress)
                .map(Address::getCity)
                .ifPresentOrElse(System.out::println, () -> { throw new NotFoundException(); });
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

        public int getSsn() {
            return ssn;
        }

        public void setSsn(int ssn) {
            this.ssn = ssn;
        }

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
