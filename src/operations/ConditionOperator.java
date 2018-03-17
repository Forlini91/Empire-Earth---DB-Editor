package operations;

import java.util.function.Predicate;

import constants.EnumValue;
import datmanager.DatFile;
import datmanager.Language;
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
			name = "<Any field> " + operator + ' ' + (value instanceof String ? '\"' + (String) value + '\"' : value);
		} else {
			name = "(" + index + ") " + field.name + ' ' + operator + ' ' + (value instanceof String ? '\"' + (String) value + '\"' : value);
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
			return buildAllFieldsCondition (datStructure, operator, value);
		}
		return buildSingleFieldCondition (datStructure, index, operator, value);
	}

	private static Condition buildSingleFieldCondition (DatStructure datStructure, int index, Operator operator, Object value) {
		FieldType type;
		boolean extraIndexes = false;
		if (index < datStructure.fieldStructs.length) {
			type = datStructure.fieldStructs[index].type;
		} else if (datStructure.extraField != null) {
			type = datStructure.extraField.type;
			extraIndexes = true;
		} else {
			return ALWAYS_FALSE;
		}


		switch (type) {
			case BOOLEAN:
				int boolValue = (Boolean) value ? 1 : 0;
				return (Entry entry) -> boolValue == (Integer) entry.get (index);
			case ENUM:
				Predicate <Integer> enumTester = getEnumCheck (operator, (EnumValue) value);
				return (Entry entry) -> enumTester.test ((Integer) entry.get (index));
			case STRING:
				Predicate <String> stringTester = getStringCheck (operator, (String) value);
				return (Entry entry) -> stringTester.test (entry.get (index).toString ());
			default:
				FloatPredicate numberTester = getNumericCheck (operator, (Float) value);
				switch (type) {
					case LINK:
						if (extraIndexes) {
							return (Entry entry) -> entry.getExtraFields ().stream ()
									.map (val -> ((Link) val).target.getID ())
									.anyMatch (numberTester::test);
						}
						return (Entry entry) -> numberTester.test (((Link) entry.get (index)).target.getID ());
					case LANGUAGE:
						return (Entry entry) -> numberTester.test (((Language) entry.get (index)).ID);
					case FLOAT:
						return (Entry entry) -> numberTester.test (((Float) entry.get (index)).floatValue ());
					case RANGE:
					case INTEGER:
						return (Entry entry) -> numberTester.test (((Integer) entry.get (index)).floatValue ());
					default:
						return ALWAYS_TRUE;
				}
		}
	}




	@SuppressWarnings ("unused")
	private static Condition buildAllFieldsCondition (DatStructure datStructure, Operator operator, Object valueToSearch) {
		if (!operator.supportNumber) {
			Predicate <String> stringTester = getStringCheck (operator, (String) valueToSearch);
			return (Entry entry) -> entry.stream ().anyMatch (entryValue -> stringTester.test (entryValue.toString ()));
		} else if (!operator.supportString) {
			FloatPredicate numericTester = getNumericCheck (operator, Float.valueOf ((String) valueToSearch));
			return (Entry entry) -> entry.stream ().anyMatch (val -> {
				if (val instanceof Integer) {
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

		try {
			Float floatValueToSearch = Float.valueOf ((String) valueToSearch);
			FloatPredicate numericTester = getNumericCheck (operator, floatValueToSearch);
			Predicate <String> stringTester = getStringCheck (operator, (String) valueToSearch);
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
		} catch (NumberFormatException e) {
			Predicate <String> stringTester = getStringCheck (operator, (String) valueToSearch);
			return (Entry entry) -> entry.stream ().anyMatch (entryValue -> stringTester.test (entryValue.toString ()));
		}
	}




	private static Predicate <Integer> getEnumCheck (Operator operator, EnumValue valueToSearch) {
		int ordinalToSearch = valueToSearch.ordinal ();
		switch (operator) {
			case EQUAL:
				return (Integer entryValue) -> entryValue == ordinalToSearch;
			case DIFFERENT:
				return (Integer entryValue) -> entryValue != ordinalToSearch;
			case GREATER:
				return (Integer entryValue) -> entryValue > ordinalToSearch;
			case GREATER_EQUAL:
				return (Integer entryValue) -> entryValue >= ordinalToSearch;
			case LESS:
				return (Integer entryValue) -> entryValue < ordinalToSearch;
			case LESS_EQUAL:
				return (Integer entryValue) -> entryValue <= ordinalToSearch;
			default:
				return (Integer entryValue) -> false;
		}
	}

	private static FloatPredicate getNumericCheck (Operator operator, float valueToSearch) {
		switch (operator) {
			case EQUAL:
				return (float entryValue) -> entryValue == valueToSearch;
			case DIFFERENT:
				return (float entryValue) -> entryValue != valueToSearch;
			case GREATER:
				return (float entryValue) -> entryValue > valueToSearch;
			case GREATER_EQUAL:
				return (float entryValue) -> entryValue >= valueToSearch;
			case LESS:
				return (float entryValue) -> entryValue < valueToSearch;
			case LESS_EQUAL:
				return (float entryValue) -> entryValue <= valueToSearch;
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
