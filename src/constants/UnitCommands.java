package constants;


/**
 * All possible commands for a unit, with the given code
 * @author MarcoForlini
 *
 */
public enum UnitCommands implements Element {

	Stop (),
	Explore (),
	Patrol (),
	Convert (),
	AttackGround (),
	Populate (),
	Stance (),
	Formation (),
	Heal (),
	Unload (),
	FighterSpawn (),
	BomberSpawn (),
	NuclearBomberSpawn (),
	;
	
	
	public final int value;

	UnitCommands(){		//Will be removed when all values will be discovered
		value = 0;
	}
	
	UnitCommands(int value){
		this.value = value;
	}
	
	@Override
	public Integer getValue(){
		return value;
	}
	
	
}
