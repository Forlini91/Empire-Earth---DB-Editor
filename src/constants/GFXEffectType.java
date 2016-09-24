package constants;

/**
 * Effect codes used in an Effect entry.
 * They defines the action to execute.
 * @author MarcoForlini
 */
@SuppressWarnings ("javadoc")
public enum GFXEffectType implements EnumValue {

	C_1_NONE ("None", -1),
	C00_FIRETRAIL ("Fire trail", 0),
	C01_UNKNOWN ("Unknown", 1),
	C02_ICON ("Icon", 2),
	C03_EXHAUSTTRAIL ("Exhaust trail", 3),
	C04_EXPLOSION ("Explosion", 4),
	C05_FIRE ("Fire", 5),
	C06_IMPACT ("Impact", 6),
	C07_IMPACTBLOOD ("Impact blood", 7),
	C08_LIGHTNING ("Lightning", 8),
	C09_MUZZLEFLASH ("Muzzle flash", 9),
	C10_PLASMAPULSE ("Plasma pulse", 10),
	C11_SMOKEPLUME ("Smoke plume", 11),
	C12_VELOCITYPARTICLE ("Velocity particle", 12),
	C13_LIGHTGLOW ("Light glow", 13),
	C14_PULSEWAVE ("Pulse wave", 14),
	C15_SQUAREMARCHINGICONS ("Square marching icons", 15),
	C16_CIRCULARMARCHINGICONS ("Circular marching icons", 16),
	C17_UKNNOWN ("Unkwnown", 17),
	C18_SHOCKWAVE ("Shockwave", 18),
	C19_UNKNOWN ("Unknown", 19),
	C20_MARCHINGICONS ("Marching icons", 20),
	C21_UNKNOWN ("Unknown", 21),
	C22_WINTERBREATH ("Winter breath", 22),
	C23_UNKNOWN ("Unknown", 23),
	C24_LINEARPROJECTILE3D ("Linear projectile 3D", 24),
	C25_UNKNOWN ("Unknown", 25),
	C26_EARTHQUAKE ("Earthquake", 26),
	C27_WATERSPLASH ("Watersplash", 27),
	C28_UNKNOWN ("Unknown", 28),
	C29_UNKNOWN ("Unknown", 29),
	C30_DESTRUCTIONSMOKE ("Destruction smoke", 30),
	C31_SMOKEMUZZLEBLAST ("Smoke muzzle blast", 31),
	C32_VOLCANO ("Volcano", 32),
	C33_TRACERS ("Tracers", 33),
	C34_WATERRIPPLE ("Water ripple", 34),
	C35_DISEASE ("Disease", 35),
	C36_HURRICANE ("Hurricane", 36),
	C37_ADDITIVEPASS ("Additive pass", 37),
	C38_SHELLS ("Shells", 38),
	C39_LINEARPROJECTILE2D ("Linear projectile 2D", 39),
	C40_NUCLEAREXPLOSION ("Nuclear explosion", 40),
	C41_FARM ("Farm", 41),
	C42_RAIN ("Rain", 42),
	C43_SMOKEGENERATOR ("Smoke generator", 43),
	C44_DAMAGESMOKE ("Damage smoke", 44),
	C45_MARCHINGLINE ("Marching line", 45),
	C46_PARTICLE2D ("Particle 2D", 46),
	C47_BLASTWAVE ("Blastwave", 47),
	C48_PULSECANNONBALL ("Pulse cannonball", 48),
	C49_UNKNOWN ("Unknown", 49),
	C50_UNKNOWN ("Unknown", 50),
	C51_UNKNOWN ("Unknown", 51),
	C52_UNKNOWN ("Unknown", 52),
	C53_UNKNOWN ("Unknown", 53),
	C54_UNKNOWN ("Unknown", 54),
	C55_UNKNOWN ("Unknown", 55),
	C56_UNKNOWN ("Unknown", 56),
	C57_PARTICLE3D ("Particle 3D", 57),
	C58_PARTICLEEMITTER ("Particle emitter", 58),
	C59_MULTIEFFECT ("Multi effect", 59),
	C60_GOAL ("Goal", 60),
	C61_SNOW ("Snow", 61),
	C62_STORM ("Storm", 62),
	C63_TELEPORT ("Teleport", 63),
	C64_TRACERBEAM ("Tracer beam", 64),
	C65_COMMANDCONFIRMATION ("Command confirmation", 65),
	C66_VIRUS ("Virus", 66),
	C67_UNKNOWN ("Unknown", 67),
	C68_UNKNOWN ("Unknown", 68),
	C69_SHIELD ("Shield", 69),
	C70_SCREENFLASH ("Screen flash", 70),
	C71_CLOAK ("Cloack", 71)

	;
	



	/** Name to be shown in the UI */
	public final String name;
	
	/** Code used in the dat files */
	public final int code;

	
	GFXEffectType(String effectName, int effectCode){
		name = effectName;
		code = effectCode;
	}

	@Override
	public String getName(){
		return name;
	}
	
	@Override
	public int getCode () {
		return code;
	}
	
	@Override
	public boolean isValid (int code) {
		return code >= 0 && code <= 71;
	}
	
	@Override
	public GFXEffectType parseValue(int code){
		switch (code){
			case -1: return C_1_NONE;
			case 0: return C00_FIRETRAIL;
			case 1: return C01_UNKNOWN;
			case 2: return C02_ICON;
			case 3: return C03_EXHAUSTTRAIL;
			case 4: return C04_EXPLOSION;
			case 5: return C05_FIRE;
			case 6: return C06_IMPACT;
			case 7: return C07_IMPACTBLOOD;
			case 8: return C08_LIGHTNING;
			case 9: return C09_MUZZLEFLASH;
			case 10: return C10_PLASMAPULSE;
			case 11: return C11_SMOKEPLUME;
			case 12: return C12_VELOCITYPARTICLE;
			case 13: return C13_LIGHTGLOW;
			case 14: return C14_PULSEWAVE;
			case 15: return C15_SQUAREMARCHINGICONS;
			case 16: return C16_CIRCULARMARCHINGICONS;
			case 17: return C17_UKNNOWN;
			case 18: return C18_SHOCKWAVE;
			case 19: return C19_UNKNOWN;
			case 20: return C20_MARCHINGICONS;
			case 21: return C21_UNKNOWN;
			case 22: return C22_WINTERBREATH;
			case 23: return C23_UNKNOWN;
			case 24: return C24_LINEARPROJECTILE3D;
			case 25: return C25_UNKNOWN;
			case 26: return C26_EARTHQUAKE;
			case 27: return C27_WATERSPLASH;
			case 28: return C28_UNKNOWN;
			case 29: return C29_UNKNOWN;
			case 30: return C30_DESTRUCTIONSMOKE;
			case 31: return C31_SMOKEMUZZLEBLAST;
			case 32: return C32_VOLCANO;
			case 33: return C33_TRACERS;
			case 34: return C34_WATERRIPPLE;
			case 35: return C35_DISEASE;
			case 36: return C36_HURRICANE;
			case 37: return C37_ADDITIVEPASS;
			case 38: return C38_SHELLS;
			case 39: return C39_LINEARPROJECTILE2D;
			case 40: return C40_NUCLEAREXPLOSION;
			case 41: return C41_FARM;
			case 42: return C42_RAIN;
			case 43: return C43_SMOKEGENERATOR;
			case 44: return C44_DAMAGESMOKE;
			case 45: return C45_MARCHINGLINE;
			case 46: return C46_PARTICLE2D;
			case 47: return C47_BLASTWAVE;
			case 48: return C48_PULSECANNONBALL;
			case 49: return C49_UNKNOWN;
			case 50: return C50_UNKNOWN;
			case 51: return C51_UNKNOWN;
			case 52: return C52_UNKNOWN;
			case 53: return C53_UNKNOWN;
			case 54: return C54_UNKNOWN;
			case 55: return C55_UNKNOWN;
			case 56: return C56_UNKNOWN;
			case 57: return C57_PARTICLE3D;
			case 58: return C58_PARTICLEEMITTER;
			case 59: return C59_MULTIEFFECT;
			case 60: return C60_GOAL;
			case 61: return C61_SNOW;
			case 62: return C62_STORM;
			case 63: return C63_TELEPORT;
			case 64: return C64_TRACERBEAM;
			case 65: return C65_COMMANDCONFIRMATION;
			case 66: return C66_VIRUS;
			case 67: return C67_UNKNOWN;
			case 68: return C68_UNKNOWN;
			case 69: return C69_SHIELD;
			case 70: return C70_SCREENFLASH;
			case 71: return C71_CLOAK;
			default: return null;
		}
	}
	
	@Override
	public String toString(){
		return buildUIName();
	}

}
