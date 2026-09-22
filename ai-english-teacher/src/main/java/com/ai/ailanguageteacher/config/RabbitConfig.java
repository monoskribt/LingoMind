package com.ai.ailanguageteacher.config;

import com.common.config.RabbitSettings;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitConfig {

    private final RabbitSettings rabbitSettings;

    @Bean
    public Queue statisticQueue() {
        return new Queue(rabbitSettings.getQueue(), false);
    }

    @Bean
    public DirectExchange statisticExchange() {
        return new DirectExchange(rabbitSettings.getExchange());
    }

    @Bean
    public Binding statisticBinding(Queue statisticQueue, DirectExchange statisticExchange) {
        return BindingBuilder.bind(statisticQueue)
                .to(statisticExchange)
                .with(rabbitSettings.getKey());
    }
}
