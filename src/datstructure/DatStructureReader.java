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

	private final File file;
	private final Map<String, DatStructure> datStructureMap;

	public DatStructureReader(File file, Map<String, DatStructure> datStructureMap) throws IOException {
		this.file = file;
		this.datStructureMap = datStructureMap;
	}

	private Stream<String> read() throws IOException, NumberFormatException, IndexOutOfBoundsException {
		return Files.lines(file.toPath()).map(String::strip);
	}

	public FieldStruct[] toArray() throws IOException, NumberFormatException, IndexOutOfBoundsException {
		return read().map(line -> parseLineIndex(file, line)).filter(Objects::nonNull).toArray(FieldStruct[]::new);
	}

	public Map<String, FieldStruct> toMap() throws IOException {
		return read().map(line -> parseLineCommon(file, line)).filter(Objects::nonNull).collect(Collectors.toMap(x -> x.getKey(), x -> x.getValue()));
	}

	private FieldStruct parseLineIndex(File file, String line) throws NumberFormatException, IndexOutOfBoundsException {
		if (line.isEmpty() || line.charAt(0) == '#') {
			return null;
		}

		final StringIterator it = new StringIterator(line, '\t');
		final String lineNum = it.nextStripped(); // line number
		return parseLine(file, lineNum, it);
	}

	private MapEntry<FieldStruct> parseLineCommon(File file, String line) throws NumberFormatException, IndexOutOfBoundsException {
		if (line.isEmpty() || line.charAt(0) == '#') {
			return null;
		}

		final StringIterator it = new StringIterator(line, '\t');
		final String key = it.nextStripped(); // key for the map
		return new MapEntry<>(key, parseLine(file, key, it));
	}

	private FieldStruct parseLine(File file, String key, StringIterator it) throws NumberFormatException, IndexOutOfBoundsException {
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
		if (size < 0) {
			throw new IllegalArgumentException("File " + file.getName() + ", line: " + key + " size can't be negative!");
		}

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
			case STRING:
			case LANGUAGE:
				break;
			default:
				if (size != 1 && size != 4) {
					throw new IllegalArgumentException("File " + file.getName() + ", line: " + key + " numeric/boolean types can't have size other than 1 or 4!");
				}
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
			case "MAGENTA":
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
		}

		if (name.charAt(0) == '#' && name.length() == 7) {
			try {
				final int r = Integer.parseInt(name.substring(1, 3), 16);
				final int g = Integer.parseInt(name.substring(3, 5), 16);
				final int b = Integer.parseInt(name.substring(5), 16);
				return new Color(r, g, b);
			} catch (final NumberFormatException e) {
				// Just jump outside this
			}
		}
		return defaultColor;
	}
}
