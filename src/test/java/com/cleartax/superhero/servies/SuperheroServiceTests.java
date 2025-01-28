package com.cleartax.superhero.servies;

import com.cleartax.superhero.repository.SuperheroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class SuperheroServiceTests {

    //@Autowired
//    @Mock
//    private SuperheroRepository superheroRepository;

//    public SuperheroServiceTests(SuperheroRepository superheroRepository){
//        this.superheroRepository = superheroRepository;
//    }

//    @Test
//    public void test(){
//        assertEquals(4, 2 + 2);
//    }
//
//    @ParameterizedTest
//    @CsvSource({
//            "1, 1, 2",
//            "2, 4, 8"
//    })
//    public void testParameter(int a, int b, int expected){
//        assertEquals(expected, a + b);
//    }
//
//    @Test
//    public void getAllSuperheroesTest(){
//        assertNotNull(superheroRepository.findAll());
//    }

}
