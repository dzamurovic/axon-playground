package playground;

import org.axonframework.domain.GenericEventMessage;
import org.joda.time.DateTime;

import java.util.HashMap;
import java.util.UUID;

public class EventUtils {

    public static <T> GenericEventMessage<T> asEventMessage(T event) {
        DateTime timestamp = DateTime.now();
        return new GenericEventMessage<T>(UUID.randomUUID().toString(), timestamp, event, new HashMap<String, Object>());
    }

}
