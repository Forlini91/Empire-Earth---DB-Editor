package constants;


/**
 * Tech type codes used in a Technology entry.
 * They defines the technology type.
 *
 * @author MarcoForlini
 */
public enum FormEventType implements EnumValue {

	C0_OPEN ("Open", 0),
	C1_CLOSE ("Close", 1),
	C2_LEFT_CLICK ("Mouse: Left click", 2),
	C4_MIDDLE_CLICK ("Mouse: Middle click", 4),
	C8_RIGHT_CLICK ("Mouse: Right click", 8),
	;


	/** Name to be shown in the UI */
	public final String	name;

	/** Code used in the dat files */
	public final int	code;


	FormEventType (String name, int code) {
		this.name = name;
		this.code = code;
	}

	@Override
	public String getName () {
		return name;
	}

	@Override
	public int getCode () {
		return code;
	}

	@Override
	public FormEventType parseValue (int code) {
		switch (code) {
			case 0:
				return C0_OPEN;
			case 1:
				return C1_CLOSE;
			case 2:
				return C2_LEFT_CLICK;
			case 4:
				return C4_MIDDLE_CLICK;
			case 8:
				return C8_RIGHT_CLICK;
			default:
				return null;
		}
	}

	@Override
	public String toString () {
		return buildUIName ();
	}

}
