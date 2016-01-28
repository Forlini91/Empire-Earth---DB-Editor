package constants;


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


	//	public final int value;

	//	UnitCommands(int value){
	//		this.value = value;
	//	}

	@Override
	public Integer getValue(){
		return 0;	//value;
	}


}
