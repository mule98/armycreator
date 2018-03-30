package armybuilder.domain.codex.attributes;

import lombok.Data;

@Data
public class WeaponSkill extends EntryAttribute {
    private final String skill;

    public String toString() {
        return skill;
    }
}
