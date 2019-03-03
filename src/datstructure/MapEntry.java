package datstructure;

import java.util.Map;
import java.util.Objects;

class MapEntry<T> implements Map.Entry<String, T> {
	private final String key;
	private T value;

	public MapEntry(String key, T value) {
		this.key = Objects.requireNonNull(key);
		this.value = value;
	}

	@Override
	public String getKey() { return key; }

	@Override
	public T getValue() { return value; }

	@Override
	public T setValue(T value) {
		final var old = this.value;
		this.value = value;
		return old;
	}

}
