package com.statistic.config;

import com.common.config.RabbitSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class RabbitConfig {

    private final RabbitSettings rabbitSettings;

    @Bean
    public Queue statisticDlq() {
        return QueueBuilder.durable(rabbitSettings.getDlq()).build();
    }

    @Bean
    public CustomExchange statisticDlqExchange(RabbitSettings settings) {
        var args = new HashMap<String, Object>();
        args.put("x-delayed-type", "direct");
        return new CustomExchange(settings.getExchangeDlq(), "x-delayed-message", true, false, args);
    }

    @Bean
    public Binding statisticDlqBinding(Queue statisticDlq, CustomExchange statisticDlqExchange) {
        return BindingBuilder.bind(statisticDlq)
                .to(statisticDlqExchange)
                .with(rabbitSettings.getKeyDlq())
                .noargs();
    }
}


