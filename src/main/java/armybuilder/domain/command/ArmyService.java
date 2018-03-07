package armybuilder.domain.command;

import armybuilder.domain.Army;
import armybuilder.domain.ArmyId;
import armybuilder.domain.ArmyRepository;
import armybuilder.domain.events.NameChanged;

public class ArmyService {

    private final ArmyRepository armyRepository;

    public ArmyService(ArmyRepository armyRepository) {
        this.armyRepository = armyRepository;
    }

    public ArmyId createArmy(CreateArmy createArmy) {
        Army army = new Army(createArmy.name, ArmyId.next());
        armyRepository.save(army);
        return army.id;
    }

    public void rename(RenameArmy renameArmy) {
        Army army = armyRepository.getById(renameArmy.id);
        army.applyChange(new NameChanged(renameArmy.name));
        armyRepository.save(army);
    }
}
