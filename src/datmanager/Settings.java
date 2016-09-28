package datmanager;

/**
 * Settings of the editor
 * @author MarcoForlini
 */
public class Settings {
	
	/** The editor version */
	public static final float VERSION = 1.52f;

	/** Enable/disable the debug mode */
	public static final boolean DEBUG = true;

	/** If false, disable the link system */
	public static final boolean LINK_SYSTEM = true;

	/** Max time (milliseconds) it will wait for loading to complete. If time exceed this value, the load is considered failed. */
	public static final int LOAD_MAX_WAIT = 5000;

}