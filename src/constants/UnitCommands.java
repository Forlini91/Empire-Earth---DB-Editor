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
	
	
	//	public final int value;
	
	//	UnitCommands(int value){
	//		this.value = value;
	//	}
	
	@Override
	public Integer getValue(){
		return 0;	//value;
	}
	
	
}
