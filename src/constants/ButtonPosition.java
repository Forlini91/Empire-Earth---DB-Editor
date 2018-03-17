package constants;

/**
 * Tech type codes used in a Technology entry. They defines the technology type.
 *
 * @author MarcoForlini
 */
public enum ButtonPosition implements EnumValue {

	C_1_NONE ("None", -1),
	C00_Row1_Pos01 ("Row 1, Position  1,", 0),
	C01_Row1_Pos02 ("Row 1, Position  2,", 1),
	C02_Row1_Pos03 ("Row 1, Position  3,", 2),
	C03_Row1_Pos04 ("Row 1, Position  4,", 3),
	C04_Row1_Pos05 ("Row 1, Position  5,", 4),
	C05_Row1_Pos06 ("Row 1, Position  6,", 5),
	C06_Row1_Pos07 ("Row 1, Position  7,", 6),
	C07_Row1_Pos08 ("Row 1, Position  8,", 7),
	C08_Row1_Pos09 ("Row 1, Position  9,", 8),
	C09_Row1_Pos10 ("Row 1, Position 10,", 9),
	C10_Row1_Pos11 ("Row 1, Position 11,", 10),
	C11_Row1_Pos12 ("Row 1, Position 12,", 11),
	C12_Row1_Pos13 ("Row 1, Position 13,", 12),
	C13_Row1_Pos14 ("Row 1, Position 14,", 13),
	C14_Row1_Pos15 ("Row 1, Position 15,", 14),
	C15_Row1_Pos16 ("Row 1, Position 16,", 15),
	C16_Row2_Pos01 ("Row 2, Position  1,", 16),
	C17_Row2_Pos02 ("Row 2, Position  2,", 17),
	C18_Row2_Pos03 ("Row 2, Position  3,", 18),
	C19_Row2_Pos04 ("Row 2, Position  4,", 19),
	C20_Row2_Pos05 ("Row 2, Position  5,", 20),
	C21_Row2_Pos06 ("Row 2, Position  6,", 21),
	C22_Row2_Pos07 ("Row 2, Position  7,", 22),
	C23_Row2_Pos08 ("Row 2, Position  8,", 23),
	C24_Row2_Pos09 ("Row 2, Position  9,", 24),
	C25_Row2_Pos10 ("Row 2, Position 10,", 25),
	C26_Row2_Pos11 ("Row 2, Position 11,", 26),
	C27_Row2_Pos12 ("Row 2, Position 12,", 27),
	C28_Row2_Pos13 ("Row 2, Position 13,", 28),
	C29_Row2_Pos14 ("Row 2, Position 14,", 29),
	C30_Row2_Pos15 ("Row 2, Position 15,", 30),
	C31_Row2_Pos16 ("Row 2, Position 16,", 31),
	C32_Row3_Pos01 ("Row 3, Position  1,", 32),
	C33_Row3_Pos02 ("Row 3, Position  2,", 33),
	C34_Row3_Pos03 ("Row 3, Position  3,", 34),
	C35_Row3_Pos04 ("Row 3, Position  4,", 35),
	C36_Row3_Pos05 ("Row 3, Position  5,", 36),
	C37_Row3_Pos06 ("Row 3, Position  6,", 37),
	C38_Row3_Pos07 ("Row 3, Position  7,", 38),
	C39_Row3_Pos08 ("Row 3, Position  8,", 39),
	C40_Row3_Pos09 ("Row 3, Position  9,", 40),
	C41_Row3_Pos10 ("Row 3, Position 10,", 41),
	C42_Row3_Pos11 ("Row 3, Position 11,", 42),
	C43_Row3_Pos12 ("Row 3, Position 12,", 43),
	C44_Row3_Pos13 ("Row 3, Position 13,", 44),
	C45_Row3_Pos14 ("Row 3, Position 14,", 45),
	C46_Row3_Pos15 ("Row 3, Position 15,", 46),
	C47_Row3_Pos16 ("Row 3, Position 16,", 47),
	C48_Row4_Pos01 ("Row 4, Position  1,", 48),
	C49_Row4_Pos02 ("Row 4, Position  2,", 49),
	C50_Row4_Pos03 ("Row 4, Position  3,", 50),
	C51_Row4_Pos04 ("Row 4, Position  4,", 51),
	C52_Row4_Pos05 ("Row 4, Position  5,", 52),
	C53_Row4_Pos06 ("Row 4, Position  6,", 53),
	C54_Row4_Pos07 ("Row 4, Position  7,", 54),
	C55_Row4_Pos08 ("Row 4, Position  8,", 55),
	C56_Row4_Pos09 ("Row 4, Position  9,", 56),
	C57_Row4_Pos10 ("Row 4, Position 10,", 57),
	C58_Row4_Pos11 ("Row 4, Position 11,", 58),
	C59_Row4_Pos12 ("Row 4, Position 12,", 59),
	C60_Row4_Pos13 ("Row 4, Position 13,", 60),
	C61_Row4_Pos14 ("Row 4, Position 14,", 61),
	C62_Row4_Pos15 ("Row 4, Position 15,", 62),
	C63_Row4_Pos16 ("Row 4, Position 16,", 63),;

	/** Name to be shown in the UI */
	public final String	name;

	/** Code used in the dat files */
	public final int	code;

	ButtonPosition (String name, int code) {
		this.name = name;
		this.code = code;
	}

	@Override
	public String getName () {
		return name;
	}

	@Override
	public int getCode () {
		return code;
	}

	@Override
	public ButtonPosition parseValue (int code) {
		switch (code) {
			case -1:
				return C_1_NONE;
			case 0:
				return C00_Row1_Pos01;
			case 1:
				return C01_Row1_Pos02;
			case 2:
				return C02_Row1_Pos03;
			case 3:
				return C03_Row1_Pos04;
			case 4:
				return C04_Row1_Pos05;
			case 5:
				return C05_Row1_Pos06;
			case 6:
				return C06_Row1_Pos07;
			case 7:
				return C07_Row1_Pos08;
			case 8:
				return C08_Row1_Pos09;
			case 9:
				return C09_Row1_Pos10;
			case 10:
				return C10_Row1_Pos11;
			case 11:
				return C11_Row1_Pos12;
			case 12:
				return C12_Row1_Pos13;
			case 13:
				return C13_Row1_Pos14;
			case 14:
				return C14_Row1_Pos15;
			case 15:
				return C15_Row1_Pos16;
			case 16:
				return C16_Row2_Pos01;
			case 17:
				return C17_Row2_Pos02;
			case 18:
				return C18_Row2_Pos03;
			case 19:
				return C19_Row2_Pos04;
			case 20:
				return C20_Row2_Pos05;
			case 21:
				return C21_Row2_Pos06;
			case 22:
				return C22_Row2_Pos07;
			case 23:
				return C23_Row2_Pos08;
			case 24:
				return C24_Row2_Pos09;
			case 25:
				return C25_Row2_Pos10;
			case 26:
				return C26_Row2_Pos11;
			case 27:
				return C27_Row2_Pos12;
			case 28:
				return C28_Row2_Pos13;
			case 29:
				return C29_Row2_Pos14;
			case 30:
				return C30_Row2_Pos15;
			case 31:
				return C31_Row2_Pos16;
			case 32:
				return C32_Row3_Pos01;
			case 33:
				return C33_Row3_Pos02;
			case 34:
				return C34_Row3_Pos03;
			case 35:
				return C35_Row3_Pos04;
			case 36:
				return C36_Row3_Pos05;
			case 37:
				return C37_Row3_Pos06;
			case 38:
				return C38_Row3_Pos07;
			case 39:
				return C39_Row3_Pos08;
			case 40:
				return C40_Row3_Pos09;
			case 41:
				return C41_Row3_Pos10;
			case 42:
				return C42_Row3_Pos11;
			case 43:
				return C43_Row3_Pos12;
			case 44:
				return C44_Row3_Pos13;
			case 45:
				return C45_Row3_Pos14;
			case 46:
				return C46_Row3_Pos15;
			case 47:
				return C47_Row3_Pos16;
			case 48:
				return C48_Row4_Pos01;
			case 49:
				return C49_Row4_Pos02;
			case 50:
				return C50_Row4_Pos03;
			case 51:
				return C51_Row4_Pos04;
			case 52:
				return C52_Row4_Pos05;
			case 53:
				return C53_Row4_Pos06;
			case 54:
				return C54_Row4_Pos07;
			case 55:
				return C55_Row4_Pos08;
			case 56:
				return C56_Row4_Pos09;
			case 57:
				return C57_Row4_Pos10;
			case 58:
				return C58_Row4_Pos11;
			case 59:
				return C59_Row4_Pos12;
			case 60:
				return C60_Row4_Pos13;
			case 61:
				return C61_Row4_Pos14;
			case 62:
				return C62_Row4_Pos15;
			case 63:
				return C63_Row4_Pos16;
			default:
				return null;
		}
	}

	@Override
	public String toString () {
		return buildUIName ();
	}

}
