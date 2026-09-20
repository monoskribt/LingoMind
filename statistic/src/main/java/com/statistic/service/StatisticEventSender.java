package com.statistic.service;

import com.common.config.RabbitSettings;
import com.common.dto.StatisticEventDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;

import static com.statistic.util.RabbitMessageUtil.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class StatisticEventSender {

    private final RabbitTemplate rabbitTemplate;
    private final RabbitSettings rabbitSettings;

    public void sendDlqEvent(StatisticEventDto event) {
        var correlationId = UUID.randomUUID();

        rabbitTemplate.convertAndSend(rabbitSettings.getExchangeDlq(), rabbitSettings.getKeyDlq(), event,
                message -> {
                    message.getMessageProperties().getHeaders().put(DELAY,
                            rabbitSettings.getConfig().getDelay());
                    message.getMessageProperties().getHeaders().put(RETRY_BACKOFF,
                            rabbitSettings.getConfig().getRetryBackoff());
                    message.getMessageProperties().getHeaders().put(NUMBER_ATTEMPTS,
                            rabbitSettings.getConfig().getNumberAttempts());
                    message.getMessageProperties().getHeaders().put(CORRELATION_ID, correlationId);
                    return message;
                });

        log.warn("Sent event to DLQ with userId: {} and correlationId: [{}]", event.getUserId(), correlationId);
    }

    public void sendRetryDlqEvent(StatisticEventDto event, Long delay, Integer backoff, Integer attempts,
                                  String correlationId) {
        rabbitTemplate.convertAndSend(rabbitSettings.getExchangeDlq(), rabbitSettings.getKeyDlq(), event,
                message -> {
                    message.getMessageProperties().getHeaders().put(DELAY, delay);
                    message.getMessageProperties().getHeaders().put(RETRY_BACKOFF, backoff);
                    message.getMessageProperties().getHeaders().put(NUMBER_ATTEMPTS, attempts);
                    message.getMessageProperties().getHeaders().put(CORRELATION_ID, correlationId);
                    message.getMessageProperties().setDelayLong(delay);
                    return message;
                });

        log.warn("Sent event to Retry with userId: {} and correlationId: [{}]", event.getUserId(), correlationId);
    }
}
