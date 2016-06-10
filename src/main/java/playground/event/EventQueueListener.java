package playground.event;

public interface EventQueueListener {

    void onEvent(QueueableEvent event);

}
