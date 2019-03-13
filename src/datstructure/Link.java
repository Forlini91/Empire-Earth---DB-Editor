package datstructure;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import datmanager.Util;

/**
 * This class represents a link between an entry and another entry
 *
 * @author MarcoForlini
 */
public class Link implements Comparable<Link>, LocalizedObject {

	public static final Predicate<Link> filterGenerator(String text, boolean localization) {
		try {
			final int num = Integer.valueOf(text);
			return link -> link.isValid() && link.target.getID() == num || (localization ? link.toLocalizedString() : link.toString()).toLowerCase().contains(text);
		} catch (final NumberFormatException e) {
			if (text.indexOf('*') >= 0) {
				final var patterns = Util.split(text.toLowerCase(), '*');
				if (patterns.size() > 0) {
					return link -> link.isValid() && Util.matchPatterns((localization ? link.toLocalizedString() : link.toString()).toLowerCase(), patterns);
				}
			}
			return link -> link.isValid() && (localization ? link.toLocalizedString() : link.toString()).toLowerCase().contains(text);
		}
	}

	/** The source entry which contains the link */
	public Entry source;

	/** The field struct */
	public FieldStruct fieldStruct;

	/** The pointed entry */
	public Entry target;

	/** If true, the link is inverted */
	public final boolean inverted;

	/**
	 * Create a new Link
	 *
	 * @param source      The entry which point
	 * @param fieldStruct The field struct
	 * @param target      The pointed entry
	 */
	public Link(Entry source, FieldStruct fieldStruct, Entry target) {
		this.source = source != null ? source : Entry.nullEntry;
		this.fieldStruct = fieldStruct;
		this.target = target != null ? target : Entry.nullEntry;
		inverted = false;
	}

	/**
	 * Create a new Link with inverted source and target
	 *
	 * @param link An existing link
	 */
	public Link(Link link) {
		source = link.target;
		fieldStruct = link.fieldStruct;
		target = link.source;
		inverted = !link.inverted;
	}

	public boolean isValid() { return source != null && target != null && source.isValidLinkTarget() && target.isValidLinkTarget(); }

	@Override
	public String toString() {
		return "(" + target.datStructure + "  >  " + fieldStruct.name + ")   " + target.toString();
	}

	@Override
	public String toLocalizedString() {
		return "(" + target.datStructure + "  >  " + fieldStruct.name + ")   " + target.toLocalizedString();
	}

	/**
	 * Given a list of links, return a list with all Links inverted (from target to source)
	 *
	 * @param links   A list of links
	 * @param ordered If true, orders the list
	 * @return A list of inverted links
	 */
	public static List<Link> getInverseLinks(List<Link> links, boolean ordered) {
		final List<Link> inverseLinks = links.parallelStream().map(Link::new).collect(Collectors.toList());
		if (ordered) {
			inverseLinks.sort(null);
		}
		return inverseLinks;
	}

	@Override
	public int compareTo(Link o) {
		if (o == null) {
			return -1;
		}
		Entry e1s, e1t, e2s, e2t;
		if (inverted) {
			e1s = target;
			e1t = source;
			e2s = o.target;
			e2t = o.source;
		} else {
			e1s = source;
			e1t = target;
			e2s = o.source;
			e2t = o.target;
		}
		if (e1s.dummyEntry || e2t.dummyEntry) {
			return (e2s.dummyEntry || e2t.dummyEntry) ? 0 : -1;
		} else if (e2s.dummyEntry || e2t.dummyEntry) {
			return 1;
		}

		switch (e1s.datStructure.compareTo(e2s.datStructure)) {
			case -1:
				return -1;
			case 1:
				return 1;
		}
		switch (fieldStruct.compareTo(o.fieldStruct)) {
			case -1:
				return -1;
			case 1:
				return 1;
		}
		switch (Integer.compare(e1s.getID(), e2s.getID())) {
			case -1:
				return -1;
			case 1:
				return 1;
		}
		return e1t.compareTo(e2t);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		} else if (o instanceof Link) {
			return compareTo((Link) o) == 0;
		}
		return false;
	}
}
