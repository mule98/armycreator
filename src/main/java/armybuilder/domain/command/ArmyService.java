package armybuilder.domain.command;

import armybuilder.domain.Army;
import armybuilder.domain.ArmyId;
import armybuilder.domain.ArmyRepository;
import armybuilder.domain.events.ArmyCreated;
import armybuilder.domain.events.NameChanged;

public class ArmyService {

    private final ArmyRepository armyRepository;

    public ArmyService(ArmyRepository armyRepository) {
        this.armyRepository = armyRepository;
    }

    public ArmyId createArmy(CreateArmy createArmy) {
        ArmyCreated armyCreated = new ArmyCreated(createArmy.name, ArmyId.next());
        return armyCreated.apply(new Army(), true)
                          .getId();
    }

    public void rename(RenameArmy renameArmy) {
        Army army = armyRepository.getById(renameArmy.id);
        new NameChanged(renameArmy.name, renameArmy.id).apply(army, true);
    }
}
