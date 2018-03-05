package armybuilder.domain.events;

import armybuilder.domain.Name;
import jdk.nashorn.internal.ir.annotations.Immutable;
import lombok.Data;

@Data
@Immutable
public class ArmyCreated {
	private final Name name;

	public ArmyCreated(Name name) {this.name = name;}
}
