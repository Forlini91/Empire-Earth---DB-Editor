package operations;

import java.util.function.DoublePredicate;

import datmanager.Core;
import datmanager.DatFile;
import datstructure.DatStructure;
import datstructure.Entry;
import datstructure.Link;
import datstructure.Type;


/**
 * Operator used in {@link ConditionOperator}
 * @author MarcoForlini
 */
public class ConditionOperator implements Condition {
	
	/** {@link DatFile} of the entry */
	public DatFile datFile;

	/** Index of the field to compare */
	public int index;

	/** The operator */
	public Operator operator;

	/** The value to compare */
	public Object value;


	private String name;
	private Condition condition;


	@Override
	public boolean match (Entry entry) {
		try {
			return condition.match(entry);
		} catch (Exception e) {
			return false;
		}
	}

	
	/**
	 * Creates a new {@link ConditionOperator}
	 * @param datFile		{@link DatFile} of the entry
	 * @param index			Index of the field to compare
	 * @param operator		The operator
	 * @param value			The float to compare
	 */
	public ConditionOperator(DatFile datFile, int index, Operator operator, Object value){
		setData(datFile, index, operator, value);
		
	}

	/**
	 * Set the fields and build a new name
	 * @param datFile		{@link DatFile} of the entry
	 * @param index			Index of the field to compare
	 * @param operator		The operator
	 * @param value			The float to compare
	 */
	public void setData(DatFile datFile, int index, Operator operator, Object value){
		this.datFile = datFile;
		this.index = index;
		this.operator = operator;
		this.value = value;
		if (index < 0){
			name = "<Any field> " + operator + ' ' + value;
		} else {
			name = datFile.datStructure.fieldStructs[index].name + ' ' + operator + ' ' + value;
		}
		condition = buildCondition(datFile.datStructure, index, operator, value);
	}
	

	
	/**
	 * Build a condition which check if the given entry satisfy the comparison
	 * @param datStructure	{@link DatStructure} of the entry
	 * @param index			Index of the field to compare
	 * @param operator		The operator
	 * @param value			The integer to compare
	 * @return		A condition which return true if the entry match the comparison
	 */
	private static Condition buildCondition(DatStructure datStructure, int index, Operator operator, Object value){
		if (index < 0){
			return buildAllFieldsCondition(datStructure, operator, value);
		}
		return buildSingleFieldCondition(datStructure, index, operator, value);
	}
	
	private static Condition buildSingleFieldCondition(DatStructure datStructure, int index, Operator operator, Object value){
		Type type;
		if (index < datStructure.fieldStructs.length){
			type = datStructure.fieldStructs[index].type;
		} else if (datStructure.extraField != null){
			type = datStructure.extraField.type;
		} else {
			return ALWAYS_FALSE;
		}

		if (type == Type.STRING){
			String text = value instanceof Float ? Core.numberFormat.format(((Float) value).doubleValue()) : Integer.toString((Integer) value);
			switch (operator){
				case EQUAL:				return (Entry entry) -> entry.getString(index).equals(text);
				case DIFFERENT:			return (Entry entry) -> !entry.getString(index).equals(text);
				case GREATER:			return (Entry entry) -> entry.getString(index).compareTo(text) > 0;
				case GREATHER_EQUAL:	return (Entry entry) -> entry.getString(index).compareTo(text) >= 0;
				case LESS:				return (Entry entry) -> entry.getString(index).compareTo(text) < 0;
				case LESS_EQUAL:		return (Entry entry) -> entry.getString(index).compareTo(text) <= 0;
				default:				return ALWAYS_TRUE;
			}
		} else if (value instanceof String){
			return ALWAYS_FALSE;
		} else {
			DoublePredicate checkBuild;
			float floatValue = ((Float) value).floatValue();
			switch (operator){
				case EQUAL:				checkBuild = (double entryValue) -> entryValue == floatValue; break;
				case DIFFERENT:			checkBuild = (double entryValue) -> entryValue != floatValue; break;
				case GREATER:			checkBuild = (double entryValue) -> entryValue > floatValue; break;
				case GREATHER_EQUAL:	checkBuild = (double entryValue) -> entryValue >= floatValue; break;
				case LESS:				checkBuild = (double entryValue) -> entryValue < floatValue; break;
				case LESS_EQUAL:		checkBuild = (double entryValue) -> entryValue <= floatValue; break;
				default:				checkBuild = (double entryValue) -> true;
			}
			DoublePredicate check = checkBuild;	//This fucking idiot fucking requires the fucking variable to fucking be effectively fucking final, FUCK!...
			if (Core.LINK_SYSTEM && type == Type.ID){
				return (Entry entry) -> check.test(entry.getLink(index).target.ID);
			} else if (type == Type.FLOAT) {
				return (Entry entry) -> check.test(entry.getFloat(index));
			} else {
				return (Entry entry) -> check.test(entry.getInt(index));
			}
		}
	}
	
	
	
	
	private static Condition buildAllFieldsCondition(DatStructure datStructure, Operator operator, Object value){
		if (value instanceof Float){
			DoublePredicate checkBuild;
			float floatValue = ((Float) value).floatValue();
			switch (operator){
				case EQUAL:				checkBuild = (double entryValue) -> entryValue == floatValue; break;
				case DIFFERENT:			checkBuild = (double entryValue) -> entryValue != floatValue; break;
				case GREATER:			checkBuild = (double entryValue) -> entryValue > floatValue; break;
				case GREATHER_EQUAL:	checkBuild = (double entryValue) -> entryValue >= floatValue; break;
				case LESS:				checkBuild = (double entryValue) -> entryValue < floatValue; break;
				case LESS_EQUAL:		checkBuild = (double entryValue) -> entryValue <= floatValue; break;
				default:				checkBuild = (double entryValue) -> true;
			}
			DoublePredicate check = checkBuild;	//Another fucking idiot which fucking requires the fucking variable to fucking be effectively fucking final, DOUBLE FUCK!...
			return (Entry entry) -> entry.values.stream().anyMatch(val -> {
				if (val instanceof String) {
					return false;
				} else if (val instanceof Link){
					return check.test(((Link) val).target.ID);
				} else {
					return check.test((Integer) val);
				}
			});
		}
		
		String text = value instanceof Float ? Core.numberFormat.format(((Float) value).doubleValue()) : Integer.toString((Integer) value);
		switch (operator){
			case EQUAL:				return (Entry entry) -> entry.values.stream().anyMatch(text::equals);
			case DIFFERENT:			return (Entry entry) -> !entry.values.stream().anyMatch(text::equals);
			case GREATER:			return (Entry entry) -> entry.values.stream().anyMatch(val -> val.toString().compareTo(text) > 0);
			case GREATHER_EQUAL:	return (Entry entry) -> entry.values.stream().anyMatch(val -> val.toString().compareTo(text) >= 0);
			case LESS:				return (Entry entry) -> entry.values.stream().anyMatch(val -> val.toString().compareTo(text) < 0);
			case LESS_EQUAL:		return (Entry entry) -> entry.values.stream().anyMatch(val -> val.toString().compareTo(text) <= 0);
			default:				return ALWAYS_TRUE;
		}
	}

	
	
	@Override
	public String toString () {
		return name;
	}
	
}
