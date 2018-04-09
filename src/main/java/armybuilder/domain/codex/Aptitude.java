package armybuilder.domain.codex;

import armybuilder.domain.army.Name;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.experimental.Wither;

@AllArgsConstructor
@Wither
@EqualsAndHashCode
public class Aptitude {
    final AptitudeId id;
    final Name name;
}
