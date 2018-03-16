package armybuilder.domain.army.services;

import armybuilder.domain.army.Army;
import armybuilder.domain.army.ArmyId;
import armybuilder.domain.army.ArmyRepository;
import armybuilder.domain.army.command.CreateArmy;
import armybuilder.domain.army.command.RenameArmy;
import armybuilder.domain.army.events.ArmyCreated;
import armybuilder.domain.army.events.NameChanged;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArmyService {

    private final ArmyRepository armyRepository;

    @Autowired
    public ArmyService(ArmyRepository armyRepository) {
        this.armyRepository = armyRepository;
    }

    public ArmyId createArmy(CreateArmy createArmy) {
        ArmyCreated armyCreated = new ArmyCreated(ArmyId.next(), createArmy.getName());
        return armyCreated.apply(new Army(), true)
                          .getId();
    }

    public void rename(RenameArmy renameArmy) {
        Army army = armyRepository.getById(renameArmy.getId());
        new NameChanged(renameArmy.getId(), renameArmy.getName()).apply(army, true);
    }
}
