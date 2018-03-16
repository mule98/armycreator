package armybuilder.domain.events;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class EventBusTest {

	@Test
	void publish() {
		EventBus eventBus = EventBus.instance();

		TestListener listener = new TestListener();
		eventBus.subscribe(listener);
		Event event = Mockito.mock(Event.class);
		EventBus.instance().publish(event);


		Assertions.assertEquals(event, listener.result);
	}

	final class TestListener implements EventListener {
		Event result;

		@Override
        public void propagate(Event event) {
			result = event;
		}

	}
}