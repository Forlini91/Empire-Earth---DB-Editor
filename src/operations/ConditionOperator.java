package operations;

import java.util.function.IntPredicate;
import java.util.function.Predicate;

import constants.EnumValue;
import datmanager.DatFile;
import datmanager.Language;
import datmanager.Util;
import datstructure.DatStructure;
import datstructure.Entry;
import datstructure.FieldStruct;
import datstructure.FieldType;
import datstructure.Link;


/**
 * Operator used in {@link ConditionOperator}
 *
 * @author MarcoForlini
 */
public class ConditionOperator implements Condition {

	/** {@link DatFile} of the entry */
	public DatFile	datFile;

	/** Index of the field to compare */
	public int		index;

	/** The operator */
	public Operator	operator;

	/** The value to compare */
	public Object	value;


	private String		name;
	private Condition	condition;


	@Override
	public boolean match (Entry entry) {
		try {
			return condition.match (entry);
		} catch (Exception e) {
			return false;
		}
	}


	/**
	 * Creates a new {@link ConditionOperator}
	 *
	 * @param datFile {@link DatFile} of the entry
	 * @param index Index of the field to compare
	 * @param operator The operator
	 * @param value The float to compare
	 */
	public ConditionOperator (DatFile datFile, int index, Operator operator, Object value) {
		setData (datFile, index, operator, value);

	}

	/**
	 * Set the fields and build a new name
	 *
	 * @param datFile {@link DatFile} of the entry
	 * @param index Index of the field to compare
	 * @param operator The operator
	 * @param value The value to compare
	 */
	public void setData (DatFile datFile, int index, Operator operator, Object value) {
		this.datFile = datFile;
		this.index = index;
		this.operator = operator;
		this.value = value;

		FieldStruct field = datFile.datStructure.getFieldStruct (index);
		if (field == null) {
			name = "<Any field> " + operator + ' ' + (Util.isNumber (value.toString ()) ? '\"' + (String) value + '\"' : value);
		} else {
			name = "(" + index + ") " + field.name + ' ' + operator + ' ' + (field.type == FieldType.STRING ? '\"' + (String) value + '\"' : value);
		}
		condition = buildCondition (datFile.datStructure, index, operator, value);
	}



	/**
	 * Build a condition which check if the given entry satisfy the comparison
	 *
	 * @param datStructure {@link DatStructure} of the entry
	 * @param index Index of the field to compare
	 * @param operator The operator
	 * @param value The integer to compare
	 * @return A condition which return true if the entry match the comparison
	 */
	private static Condition buildCondition (DatStructure datStructure, int index, Operator operator, Object value) {
		if (index < 0) {
			return buildAllFieldsCondition (datStructure, operator, (String) value);
		}
		return buildSingleFieldCondition (datStructure, index, operator, value);
	}

	private static Condition buildSingleFieldCondition (DatStructure datStructure, int index, Operator operator, Object value) {
		FieldType type;
		boolean extraIndexes = false;
		if (index < 0) {
			throw new IllegalArgumentException ("Invalid field: " + index);
		} else if (index < datStructure.fieldStructs.length) {
			type = datStructure.fieldStructs[index].type;
		} else if (datStructure.extraField != null) {
			type = datStructure.extraField.type;
			extraIndexes = true;
		} else {
			throw new IllegalArgumentException ("Invalid field: " + index);
		}


		switch (type) {
			case BOOLEAN:
				int boolValue = (Boolean) value == (operator == Operator.EQUAL || operator == Operator.EQUAL_NC) ? 1 : 0;
				return (Entry entry) -> boolValue == (Integer) entry.get (index);
			case ENUM:
				IntPredicate enumTester = getEnumCheck (operator, (EnumValue) value);
				return (Entry entry) -> enumTester.test ((Integer) entry.get (index));
			case STRING:
				Predicate <String> stringTester = getStringCheck (operator, (String) value);
				return (Entry entry) -> stringTester.test (entry.get (index).toString ());
			case LINK:
				FloatPredicate linkTester = getNumericCheck (operator, ((Entry) value).getID ());
				if (extraIndexes) {
					return (Entry entry) -> entry.getExtraFields ().stream ()
							.map (val -> ((Link) val).target.getID ())
							.anyMatch (linkTester::test);
				}
				return (Entry entry) -> linkTester.test (((Link) entry.get (index)).target.getID ());
			default:
				FloatPredicate numberTester = getNumericCheck (operator, (String) value);
				switch (type) {
					case LANGUAGE:
						return (Entry entry) -> numberTester.test (((Language) entry.get (index)).ID);
					case FLOAT:
						return (Entry entry) -> numberTester.test (((Float) entry.get (index)).floatValue ());
					case RANGE:
					case INTEGER:
						return (Entry entry) -> numberTester.test (((Integer) entry.get (index)).floatValue ());
					default:
						return (Entry entry) -> false;
				}
		}
	}




	@SuppressWarnings ("unused")
	private static Condition buildAllFieldsCondition (DatStructure datStructure, Operator operator, String textToSearch) {
		Predicate <String> stringTester = getStringCheck (operator, textToSearch);
		FloatPredicate numericTester = getNumericCheck (operator, textToSearch);

		return (Entry entry) -> entry.stream ().anyMatch (val -> {
			if (val instanceof String) {
				return stringTester.test ((String) val);
			} else if (val instanceof Integer) {
				return numericTester.test ((Integer) val);
			} else if (val instanceof Float) {
				return numericTester.test ((Float) val);
			} else if (val instanceof Link) {
				return numericTester.test (((Link) val).target.getID ());
			} else if (val instanceof Language) {
				return numericTester.test (((Language) val).ID);
			}
			return false;
		});
	}





	private static IntPredicate getEnumCheck (Operator operator, EnumValue valueToSearch) {
		int ordinalToSearch = valueToSearch.ordinal ();
		switch (operator) {
			case EQUAL:
			case EQUAL_NC:
				return (int entryValue) -> entryValue == ordinalToSearch;
			case DIFFERENT:
			case DIFFERENT_NC:
				return (int entryValue) -> entryValue != ordinalToSearch;
			case GREATER:
				return (int entryValue) -> entryValue > ordinalToSearch;
			case GREATER_EQUAL:
				return (int entryValue) -> entryValue >= ordinalToSearch;
			case LESS:
				return (int entryValue) -> entryValue < ordinalToSearch;
			case LESS_EQUAL:
				return (int entryValue) -> entryValue <= ordinalToSearch;
			default:
				return (int entryValue) -> false;
		}
	}

	private static FloatPredicate getNumericCheck (Operator operator, String valueToSearch) {
		try {
			float floatValueToSearch = Float.valueOf (valueToSearch);
			switch (operator) {
				case EQUAL:
				case EQUAL_NC:
					return (float entryValue) -> entryValue == floatValueToSearch;
				case DIFFERENT:
				case DIFFERENT_NC:
					return (float entryValue) -> entryValue != floatValueToSearch;
				case GREATER:
					return (float entryValue) -> entryValue > floatValueToSearch;
				case GREATER_EQUAL:
					return (float entryValue) -> entryValue >= floatValueToSearch;
				case LESS:
					return (float entryValue) -> entryValue < floatValueToSearch;
				case LESS_EQUAL:
					return (float entryValue) -> entryValue <= floatValueToSearch;
				default:
					return (float entryValue) -> false;
			}
		} catch (NumberFormatException e) {
			return (float entryValue) -> false;
		}
	}

	private static FloatPredicate getNumericCheck (Operator operator, float floatValueToSearch) {
		switch (operator) {
			case EQUAL:
			case EQUAL_NC:
				return (float entryValue) -> entryValue == floatValueToSearch;
			case DIFFERENT:
			case DIFFERENT_NC:
				return (float entryValue) -> entryValue != floatValueToSearch;
			case GREATER:
				return (float entryValue) -> entryValue > floatValueToSearch;
			case GREATER_EQUAL:
				return (float entryValue) -> entryValue >= floatValueToSearch;
			case LESS:
				return (float entryValue) -> entryValue < floatValueToSearch;
			case LESS_EQUAL:
				return (float entryValue) -> entryValue <= floatValueToSearch;
			default:
				return (float entryValue) -> false;
		}
	}

	private static Predicate <String> getStringCheck (Operator operator, String valueToSearch) {
		switch (operator) {
			case EQUAL:
				return (String entryValue) -> entryValue.trim ().equals (valueToSearch);
			case EQUAL_NC:
				return (String entryValue) -> entryValue.trim ().equalsIgnoreCase (valueToSearch);
			case DIFFERENT:
				return (String entryValue) -> !entryValue.trim ().equals (valueToSearch);
			case DIFFERENT_NC:
				return (String entryValue) -> !entryValue.trim ().equalsIgnoreCase (valueToSearch);
			case CONTAINS:
				return (String entryValue) -> entryValue.trim ().contains (valueToSearch);
			case CONTAINS_NOT:
				return (String entryValue) -> !entryValue.trim ().contains (valueToSearch);
			case STARTS_WITH:
				return (String entryValue) -> entryValue.trim ().startsWith (valueToSearch);
			case STARTS_WITH_NOT:
				return (String entryValue) -> !entryValue.trim ().startsWith (valueToSearch);
			case ENDS_WITH:
				return (String entryValue) -> entryValue.trim ().endsWith (valueToSearch);
			case ENDS_WITH_NOT:
				return (String entryValue) -> !entryValue.trim ().endsWith (valueToSearch);
			case LESS:
				return (String entryValue) -> entryValue.trim ().compareTo (valueToSearch) < 0;
			case LESS_EQUAL:
				return (String entryValue) -> entryValue.trim ().compareTo (valueToSearch) <= 0;
			case GREATER:
				return (String entryValue) -> entryValue.trim ().compareTo (valueToSearch) > 0;
			case GREATER_EQUAL:
				return (String entryValue) -> entryValue.trim ().compareTo (valueToSearch) >= 0;
			default:
				return (String entryValue) -> false;
		}
	}



	@Override
	public String toString () {
		return name;
	}

	private interface FloatPredicate {
		boolean test (float value);
	}

}
