package armybuilder.domain.army.services;

import armybuilder.domain.army.Army;
import armybuilder.domain.army.ArmyId;
import armybuilder.domain.army.ArmyRepository;
import armybuilder.domain.army.command.CreateArmy;
import armybuilder.domain.army.command.RenameArmy;
import armybuilder.domain.army.events.ArmyCreated;
import armybuilder.domain.army.events.NameChanged;

public class ArmyService {

    private final ArmyRepository armyRepository;

    public ArmyService(ArmyRepository armyRepository) {
        this.armyRepository = armyRepository;
    }

    public ArmyId createArmy(CreateArmy createArmy) {
        ArmyCreated armyCreated = new ArmyCreated(ArmyId.next(), createArmy.name);
        return armyCreated.apply(new Army(), true)
                          .getId();
    }

    public void rename(RenameArmy renameArmy) {
        Army army = armyRepository.getById(renameArmy.id);
        new NameChanged(renameArmy.id, renameArmy.name).apply(army, true);
    }
}
