package com.example.userservice.karate;

import com.intuit.karate.junit5.Karate;

class UserKarateTest {

    @Karate.Test
    Karate testAll() {
        return Karate.run("classpath:com/example/userservice/karate/UserTest").relativeTo(getClass());

    }
}
