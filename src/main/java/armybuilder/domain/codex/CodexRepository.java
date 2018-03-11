package armybuilder.domain.codex;

import armybuilder.domain.events.DomainEvent;
import armybuilder.domain.events.EventStore;

import java.util.LinkedList;
import java.util.Optional;

public class CodexRepository {
	private final EventStore eventStore;

	public CodexRepository(EventStore eventStore) {

		this.eventStore = eventStore;
	}

	public Optional<Codex> findByName(CodexName codexName) {
		return eventStore.getAllIds(new CodexIdIdTypeMatcher()).map(this::getById).filter(c -> c.getName().equals(codexName)).findFirst();
	}

	private Codex getById(CodexId codexId) {
		final LinkedList<DomainEvent> allEvents = eventStore.getAllEvents(codexId);
		Codex codex = new Codex();
		for (DomainEvent<CodexId, Codex> event :allEvents){
			codex = event.apply(codex, false);
		}
		return codex;
	}

}