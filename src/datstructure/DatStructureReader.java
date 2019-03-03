package datstructure;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import constants.EnumValue;
import datmanager.StringIterator;

public class DatStructureReader {

	public static final FieldStruct TOO_FEW_PARAMETERS = new FieldStruct("INVALID", "TOO FEW PARAMETERS");
	public static final FieldStruct TOO_MANY_PARAMETERS = new FieldStruct("INVALID", "TOO MANY PARAMETERS");

	private final File file;
	private final Map<String, DatStructure> datStructureMap;

	public DatStructureReader(File file, Map<String, DatStructure> datStructureMap) throws IOException {
		this.file = file;
		this.datStructureMap = datStructureMap;
	}

	private Stream<String> read() throws IOException {
		return Files.lines(file.toPath()).map(String::strip);
	}

	public FieldStruct[] toArray() throws IOException {
		return read().map(this::parseLineIndex).filter(Objects::nonNull).toArray(FieldStruct[]::new);
	}

	public Map<String, FieldStruct> toMap() throws IOException {
		return read().map(this::parseLineCommon).filter(Objects::nonNull).collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
	}

	private FieldStruct parseLineIndex(String line) throws IndexOutOfBoundsException {
		if (line.isEmpty() || line.charAt(0) == '#') {
			return null;
		}

		final StringIterator it = new StringIterator(line, '\t');
		it.nextStripped(); // line number. skip
		return parseLine(it);
	}

	private MapEntry<FieldStruct> parseLineCommon(String line) throws IndexOutOfBoundsException {
		if (line.isEmpty() || line.charAt(0) == '#') {
			return null;
		}

		final StringIterator it = new StringIterator(line, '\t');
		final String key = it.nextStripped(); // key for the map
		return new MapEntry<>(key, parseLine(it));
	}

	private FieldStruct parseLine(StringIterator it) throws IndexOutOfBoundsException {
		/*
		 * <Size> <Type> <Extra?> <Name> <Description=null> <Editable=true> <Knowledge=KNOWN> <Color=BLACK>
		 * Extra is one of:
		 * - <Name of linked db> (only if Type == Link)
		 * - <FROM included>:<TO included> (only if Type == Link)
		 * - <Enum name>:<defaultValue> (only if Type == Enum)
		 * - <Index of field with dynamic string size> (only if Type == DynamicString)
		 * - missing in all other cases
		 */
		String token;
		token = it.nextStripped(); // size or common field?
		if (token.charAt(0) == '@') {
			final var commonField = DatStructure.getCommonField(token.substring(1));
			if (commonField != null) {
				return commonField;
			} else {
				return null;
			}
		}

		final int size = Integer.parseInt(token);

		token = it.nextStripped();
		final FieldType type = FieldType.parse(token);

		Integer[] arrValues = null;
		EnumValue[] enumValues = null;
		DatStructure linkToStruct = null;
		Integer defaultValue = null;
		int indexSize = -1;
		switch (type) {
			case RANGE:
				token = it.nextStripped();
				final int rangeSepar = token.indexOf(':');
				final int rangeFrom = Integer.valueOf(token.substring(0, rangeSepar));
				final int rangeTo = Integer.valueOf(token.substring(rangeSepar + 1));
				arrValues = IntStream.range(rangeFrom, rangeTo).boxed().toArray(Integer[]::new);
				break;
			case ENUM:
				token = it.nextStripped();
				enumValues = EnumValue.parseEnum(token);
				break;
			case LINK:
				token = it.nextStripped();
				final int linkSepar = token.indexOf(':');
				linkToStruct = datStructureMap.get(token.substring(0, linkSepar));
				defaultValue = Integer.valueOf(token.substring(linkSepar + 1));
				break;
			case DYNAMIC_STRING:
				token = it.nextStripped();
				indexSize = Integer.parseInt(token);
				break;
			default:
				break;
		}

		final String name = it.next();

		String description = null;
		boolean editable = true;
		Knowledge knowledge = Knowledge.KNOWN;
		Color color = Color.BLACK;
		if (it.hasNext()) {
			description = it.next();
			if (it.hasNext()) {
				editable = !it.nextStripped().equals("BLOCKED");
				if (it.hasNext()) {
					knowledge = Knowledge.parse(it.nextStripped(), Knowledge.KNOWN);
					if (it.hasNext()) {
						color = getColor(it.nextStripped(), Color.BLACK);
					}
				}
			}
		}

		return new FieldStruct(type, size, name, description, editable, knowledge, color, arrValues, enumValues, linkToStruct, defaultValue, indexSize);
	}



	private Color getColor(String name, Color defaultColor) {
		switch (name) {
			case "":
				return Color.BLACK;
			case "WHITE":
				return Color.WHITE;
			case "LIGHT_GRAY":
				return Color.LIGHT_GRAY;
			case "GRAY":
				return Color.GRAY;
			case "DARK_GRAY":
				return Color.DARK_GRAY;
			case "BLACK":
				return Color.BLACK;
			case "CYAN":
				return Color.CYAN;
			case "BLUE":
				return Color.BLUE;
			case "MAGENT":
				return Color.MAGENTA;
			case "PINK":
				return Color.PINK;
			case "RED":
				return Color.RED;
			case "ORANGE":
				return Color.ORANGE;
			case "YELLOW":
				return Color.YELLOW;
			case "GREEN":
				return Color.GREEN;
			default:
				return defaultColor;
		}
	}
}
