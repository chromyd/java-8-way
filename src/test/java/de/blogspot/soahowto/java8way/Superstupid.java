package de.blogspot.soahowto.java8way;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.google.gson.Gson;

public class Superstupid {

    @Test
    public void runMe() {
        assertThat("sex").containsOnlyOnce("x");
    }

    @Test
    public void fiddlingWithGson() {
        Gson g = new Gson();

        Person person = g.fromJson("{\"name\": \"John\"}", Person.class);
        System.out.println(person.getName()); //John

        System.out.println(g.toJson(person)); // {"name":"John"}
    }
}
