package dbstructure;

import java.util.List;
import java.util.Map;

public class EntryValueMap{
	public final Map<Object, List<Entry>> map;
	public final Map<Object, List<Entry>> mapClean;
	public EntryValueMap (Map <Object, List<Entry>> map, Map <Object, List<Entry>> mapClean) {
		this.map = map;
		this.mapClean = mapClean;
	}
}