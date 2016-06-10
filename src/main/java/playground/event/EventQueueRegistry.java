package playground.event;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class EventQueueRegistry extends Thread {

    private BlockingQueue<QueueableEvent> queue;
    private List<EventQueueListener> listeners;

    public EventQueueRegistry(ArrayBlockingQueue arrayBlockingQueue) {
        queue = arrayBlockingQueue;
        listeners = new ArrayList();
    }

    public void register(EventQueueListener listener) {
        listeners.add(listener);
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                QueueableEvent event = queue.take();
                for (EventQueueListener listener : listeners) {
                    listener.onEvent(event);
                }
            } catch (InterruptedException e) {

            }
        }
    }
}
