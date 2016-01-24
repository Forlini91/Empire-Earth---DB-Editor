package gui.elements;

import java.io.File;
import java.util.Set;

import javax.swing.filechooser.FileFilter;

public class FileFilterEx extends FileFilter  {
	private final String description;
	private final Set<String> allowedFiles;

	public FileFilterEx(String description, Set<String> allowedFiles) {
		this.description = description;
		this.allowedFiles = allowedFiles;
	}

	@Override
	public boolean accept(File f) {
		if (f != null) {
			if (f.isDirectory()) {
				return true;
			}
			String fileName = f.getName().toLowerCase();
			for (String filter : allowedFiles) {
				if (fileName.equals(filter)) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return super.toString() + "[description=" + description + " db*.dat" + "]";
	}

	@Override
	public String getDescription () {
		return description;
	}
	
}

