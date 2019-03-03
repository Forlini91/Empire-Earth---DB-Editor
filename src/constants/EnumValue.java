package constants;

/**
 * Interface for all constants/enums
 *
 * @author MarcoForlini
 */
public interface EnumValue {

	/**
	 * Gets the name to be shown in the UI
	 *
	 * @return The UI name
	 */
	String getName();

	/**
	 * Gets the code used in the dat files
	 *
	 * @return The dat code
	 */
	int getCode();


	/**
	 * Gets the ordinal number of the constant/enum
	 *
	 * @return the ordinal number
	 */
	int ordinal();

	/**
	 * Build a name for the UI
	 *
	 * @return The UI name
	 */
	default String buildUIName() {
		return getCode() + ": " + getName();
	}

	/**
	 * Returns the EnumValue with this code, if any, else null
	 *
	 * @param code The code
	 * @return The EnumValue with this code.
	 */
	EnumValue parseValue(int code);



	static EnumValue[] parseEnum(String name) {
		switch (name) {
			case "AreaEffectType":
				return AreaEffectType.values();
			case "AttributeCode":
				return AttributeCode.values();
			case "ButtonPosition":
				return ButtonPosition.values();
			case "CalamityType":
				return ControlType.values();
			case "EffectCode":
				return EffectCode.values();
			case "FormEventType":
				return FormEventType.values();
			case "GFXEffectType":
				return GFXEffectType.values();
			case "ObjectType":
				return ObjectType.values();
			case "ResourceType":
				return ResourceType.values();
			case "TechType":
				return TechType.values();
			case "TerrainFamily":
				return TerrainFamily.values();
			case "UnitType":
				return UnitType.values();
			case "WonderType":
				return WonderType.values();
			case "WorldID":
				return WorldID.values();
			default:
				return null;
		}
	}

}
