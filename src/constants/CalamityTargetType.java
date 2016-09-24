package constants;


/**
 * Tech type codes used in a Technology entry.
 * They defines the technology type.
 * @author MarcoForlini
 */
public enum CalamityTargetType implements EnumValue {
	
	/** No target */
	C0_None ("None", 0),

	/** Self target */
	C1_NoTarget("Self", 1),

	/** Cast on the selected area */
	C2_Area("Select area", 2),

	/** Cast on the selected target */
	C3_Target("Select target", 3),
	;


	/** Name to be shown in the UI */
	public final String name;
	
	/** Code used in the dat files */
	public final int code;

	
	CalamityTargetType(String name, int code){
		this.name = name;
		this.code = code;
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
		return code >= 0 && code <= 9;
	}
	
	@Override
	public CalamityTargetType parseValue(int code){
		switch (code){
			case 0: return C0_None;
			case 1: return C1_NoTarget;
			case 2: return C2_Area;
			case 3: return C3_Target;
			default: return null;
		}
	}

	@Override
	public String toString(){
		return buildUIName();
	}
	
}
