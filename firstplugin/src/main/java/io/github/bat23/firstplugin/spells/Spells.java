package io.github.bat23.firstplugin.spells;

public final class Spells {
	
	public enum CustomSpells {
		Reducto, Confringo, AvadaKedavra;
	}
	
	public Spell getspell(CustomSpells spellenumtype) {
		Spell spell = new Spell();
		switch (spellenumtype) {
		case Reducto :
			spell.setType(spellenumtype);
			break;
		case Confringo :
			spell.setType(spellenumtype);
			break;
		case AvadaKedavra :
			spell.setType(spellenumtype);
			break;
		}
		return spell;
	}
}
