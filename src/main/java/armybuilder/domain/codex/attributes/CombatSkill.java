package armybuilder.domain.codex.attributes;

import lombok.Data;

@Data
public class CombatSkill extends EntryAttribute {
    private final String combatSkill;

    public String toString() {
        return combatSkill;
    }
}
