package com.statistic.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class RabbitMessageUtil {

    public final static String DELAY = "event-delay";
    public final static String RETRY_BACKOFF = "event-retry-backoff";
    public final static String NUMBER_ATTEMPTS = "event-number-attempts";
    public final static String CORRELATION_ID = "event-correlation-id";

}
