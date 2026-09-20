package com.statistic;

import org.springframework.boot.SpringApplication;

public class TestStatisticApplication {

    public static void main(String[] args) {
        SpringApplication.from(StatisticApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
