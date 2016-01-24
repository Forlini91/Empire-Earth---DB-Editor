package dbstructure;

import java.util.ArrayList;
import java.util.List;

public class EntryValue implements Comparable<EntryValue> {
	
	public final List<Entry> entries = new ArrayList<>();
	public final Object value;
	
	public EntryValue (Entry entry, Object value) {
		entries.add(entry);
		this.value = value;
	}
	
	@Override
	public String toString(){
		return ' ' + entries.toString();
	}
	
	@Override
	public int compareTo (EntryValue o) {
		if (o.value instanceof String){
			return ((String) value).compareTo((String) o.value);
		} else if (o.value instanceof Float){
			return ((Float) value).compareTo((Float) o.value);
		} else if (o.value instanceof Integer){
			return ((Integer) value).compareTo((Integer) o.value);
		} else {
			throw new UnsupportedOperationException("Can't sort this value");
		}
	}
	
}
