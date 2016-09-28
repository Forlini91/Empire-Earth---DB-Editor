package datstructure;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation to mark the situation of the files
 * @author MarcoForlini
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface DatStructureParse {
	
	/** Parse state of the Vanilla version of the file */
	ParseState Vanilla();

	/** Parse state of the AOC version of the file */
	ParseState AOC();

	/**
	 * Possible parse states
	 * @author MarcoForlini
	 */
	public static enum ParseState {

		/** This file doesn't exists in this version of the game */
		NO_FILE ("No file"),

		/** There are some unknown fields */
		MISSING_UNKNOWN ("Missing unknown"),

		/** All "used" unknown have been parsed, but there are some still "unused" unknown fields */
		UNKNOWN_PARSED ("Unknown parsed"),

		/** All fields are known */
		COMPLETE ("Complete")
		;
		
		private String name;
		
		private ParseState(String name){
			this.name = name;
		}
		
		@Override
		public String toString(){
			return name;
		}
	}
	
}
