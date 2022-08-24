package com.victor.noloosecoins;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")

class NoLooseCoinsApplicationTests {


    @Test
    void test(){
        Assertions.assertTrue(true);
    }

}
