package armybuilder.domain;

import armybuilder.domain.events.ArmyCreated;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ArmyTest {

	@Test
	public void addUnit() {
		Army army = new Army().applyChange(new ArmyCreated(null, ArmyId.next()));

		Unit unit = new Unit();
		army.add(unit);

		assertTrue(army.units().allMatch(t -> t.equals(unit)));
		assertEquals(1, army.units().count());


		Unit unit2 = new Unit();
		army.add(unit2);

		assertTrue(army.units().anyMatch(t -> t.equals(unit)));
		assertTrue(army.units().anyMatch(t -> t.equals(unit2)));
		assertEquals(2, army.units().count());


		army.add(unit2);

		assertTrue(army.units().anyMatch(t -> t.equals(unit)));
		assertTrue(army.units().anyMatch(t -> t.equals(unit2)));
		assertEquals(2, army.units().count());
	}

	@Test
	public void addUnitTwice() {
		Army army = new Army().applyChange(new ArmyCreated(null, ArmyId.next()));

		Unit unit = new Unit();
		army = army.add(unit);

		assertTrue(army.units().allMatch(t -> t.equals(unit)));
		assertEquals(1, army.units().count());


		army.add(unit);

		assertTrue(army.units().allMatch(t -> t.equals(unit)));
		assertEquals(1, army.units().count());
	}

}