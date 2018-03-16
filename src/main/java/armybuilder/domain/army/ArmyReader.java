package armybuilder.domain.army;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArmyReader {

    private final ArmyRepository armyRepository;

    public ArmyReader(ArmyRepository armyRepository) {
        this.armyRepository = armyRepository;
    }

    public List<Army> findByName(Name name) {
        return armyRepository.findByName(name);
    }

    public Army getById(ArmyId armyId) {
        return armyRepository.getById(armyId);
    }
}
