package armybuilder.domain.command;

import armybuilder.domain.Name;

public class CreateArmy {
	public final Name name;

    public CreateArmy(Name name) {

		this.name = name;
	}
}
