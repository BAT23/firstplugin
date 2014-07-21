package io.github.bat23.firstplugin.spells;

import io.github.bat23.firstplugin.spells.Spells.CustomSpells;

public final class Spell {
	
	String spelltype;
	
	public void setType(CustomSpells spell) {
		switch (spell) {
		case Reducto :
			spelltype = "Reducto";
		case Confringo :
			spelltype = "Confringo";
		}
	}
	
	public String getType() {
		return spelltype;
	}
}
