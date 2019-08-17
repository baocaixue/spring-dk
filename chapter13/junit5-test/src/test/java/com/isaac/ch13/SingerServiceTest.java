package com.isaac.ch13;

import com.isaac.ch13.config.SimpleTestConfig;
import com.isaac.ch13.entities.Singer;
import com.isaac.ch13.service.SingerService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringJUnitConfig(classes = SimpleTestConfig.class)//这是一个组合注解，将Junit Jupiter的@ExtendWith(SpringExtension.class)和Spring TestContext框架的@ContextConfiguration组合
@DisplayName("Integration SingerService Test")
@ActiveProfiles("test")
@Slf4j
public class SingerServiceTest {
    @Autowired
    private SingerService singerService;

    /**
     * @BeforeAll method 'setUp()' must be static unless the test class is annotated with @TestInstance(Lifecycle.PER_CLASS).
     */
    @BeforeAll
    public static void setUp() {
        log.info("--> @BeforeAll - executes before executing all test methods in this class");
    }

    @AfterAll
    public static void tearDown(){
        log.info("--> @AfterAll - executes before executing all test methods in this class");
    }

    @BeforeEach
    public void init() {
        log.info("--> @BeforeEach - executes before each test method in this class");
    }

    @AfterEach
    public void dispose() {
        log.info("--> @AfterEach - executes after each test method in this class");
    }

    @Test
    @DisplayName("should return all singers")
    @SqlGroup({
            @Sql(value = "classpath:db/test-data.sql", config = @SqlConfig(encoding = "utf-8", separator = ";", commentPrefix = "--"), executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD),
            @Sql(value = "classpath:db/clean-up.sql", config = @SqlConfig(encoding = "utf-8", separator = ";", commentPrefix = "--"), executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
    })
    public void findAll() {
        List<Singer> result = singerService.findAll();
        assertNotNull(result);
        assertEquals(1, result.size());
    }
}
