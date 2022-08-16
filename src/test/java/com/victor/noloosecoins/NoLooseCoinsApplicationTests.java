package com.victor.noloosecoins;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.Assert;

import javax.validation.constraints.AssertTrue;

@SpringBootTest
@ActiveProfiles("test")

class NoLooseCoinsApplicationTests {


    @Test
    void test(){
        Assertions.assertTrue(true);
    }

}
