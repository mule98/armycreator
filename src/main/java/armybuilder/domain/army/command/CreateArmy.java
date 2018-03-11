package armybuilder.domain.army.command;

import armybuilder.domain.army.Name;

public class CreateArmy {
	public final Name name;

    public CreateArmy(Name name) {

		this.name = name;
	}
}
