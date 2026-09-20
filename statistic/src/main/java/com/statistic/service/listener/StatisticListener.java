package com.statistic.service.listener;

import com.common.config.RabbitSettings;
import com.common.dto.StatisticEventDto;
import com.statistic.service.StatisticEventSender;
import com.statistic.service.StatisticService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import static com.statistic.util.RabbitMessageUtil.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class StatisticListener {

    private final RabbitSettings rabbitSettings;
    private final StatisticService service;
    private final StatisticEventSender eventSender;

    @RabbitListener(queues = "${rabbit.queue}")
    public void handleStatisticEvent(StatisticEventDto event) {
        log.debug("Received event with userId: {} and attemptType: {}", event.getUserId(), event.getAttemptType());
        try {
            service.processUserStatistic(event);
        } catch (Exception exc) {
            eventSender.sendDlqEvent(event);
        }
    }

    @RabbitListener(queues = "${rabbit.dlq}")
    public void handleStatisticDlqEvent(StatisticEventDto event, Message message) {
        var headers = message.getMessageProperties().getHeaders();

        var delay = (Long) headers.get(DELAY);
        var retryBackoff = (Integer) headers.get(RETRY_BACKOFF);
        var attempts = (Integer) headers.get(NUMBER_ATTEMPTS);
        var correlationId = (String) headers.get(CORRELATION_ID);

        if (attempts < rabbitSettings.getConfig().getNumberAttempts()) {
            eventSender.sendRetryDlqEvent(event, delay * retryBackoff, retryBackoff, attempts + 1, correlationId);
            return;
        }

        log.warn("Event with correlationId: [{}] exhausted retry attempts: {}", correlationId, attempts);
    }

}