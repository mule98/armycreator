package armybuilder.domain.army.command;

import armybuilder.domain.army.Name;
import lombok.Data;

@Data
public class CreateArmy {
    private final Name name;

}
