package constants;

/**
 * Effect codes used in an Effect entry.
 * They defines the action to execute.
 * @author MarcoForlini
 */
@SuppressWarnings ("javadoc")
public enum GFXEffectType implements EnumValue {

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
	
	/**
	 * Parse the code and return the relative enum.
	 * @param code	The code
	 * @return		The relative enum
	 */
	public static GFXEffectType parseValue(int code){
		for (GFXEffectType gfxEffectType : values()){
			if (gfxEffectType.code == code){
				return gfxEffectType;
			}
		}
		return null;
	}
	
	@Override
	public String toString(){
		return buildUIName();
	}

}
