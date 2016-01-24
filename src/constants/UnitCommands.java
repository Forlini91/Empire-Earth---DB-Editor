package constants;


public enum UnitCommands implements Element {

	Stop (),
	Populate (),
	Explore (),
	Patrol (),
	Stance (),
	Formation (),
	Heal (),
	Convert (),
	AttackArea (),
	Unload (),
	FighterSpawn (),
	BomberSpawn (),
	NuclearBomberSpawn (),
	;
	
	
	public final byte[] value;
	
	UnitCommands(byte...value){
		this.value = value;
	}
	
	@Override
	public byte[] getValue(){
		return value;
	}
	
	
}
