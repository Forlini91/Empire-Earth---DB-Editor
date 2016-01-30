package datmanager;

import java.io.File;

import datstructure.DatStructure;

/**
 * A DatFile is a File which also hold informations about the structure of the file.
 * @author MarcoForlini
 *
 */
public class DatFile extends File {
	private static final long serialVersionUID = 4028033199491184179L;
	
	/** The structure of the file. */
	public final DatStructure datStructure;
	
	/**
	 * Creates a new <code>DatFile</code> instance by converting the given
	 * pathname string into an abstract pathname.  If the given string is
	 * the empty string, then the result is the empty abstract pathname.
	 *
	 * @param   pathname  A pathname string
	 * @param	datStructure	The structure of the file
	 * @throws  NullPointerException
	 *          If the <code>pathname</code> argument is <code>null</code>
	 */
	public DatFile(String pathname, DatStructure datStructure){
		super(pathname);
		this.datStructure = datStructure;
	}
}