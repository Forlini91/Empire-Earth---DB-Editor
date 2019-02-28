package gui;

import datmanager.DatFile;
import datmanager.DatFileManager;
import gui.DialogProgressBar.ProgressUpdater;

public class LoadThread extends Thread {

	private final DatFile datFile;
	private final int index;
	private final ProgressUpdater progressUpdater;

	private boolean completed = false;
	private Throwable error = null;

	public LoadThread(DatFile datFile, int index, ProgressUpdater progressUpdater) {
		this.datFile = datFile;
		this.index = index;
		this.progressUpdater = progressUpdater;
	}

	public boolean isCompleted() { return completed; }

	public boolean isFailed() { return !completed; }

	public DatFile getDatFile() { return datFile; }

	public Throwable getError() { return error; }

	@Override
	public void run() {
		if (!completed) { // Can run again until it succeed
			try {
				final DatFileManager dbManager = new DatFileManager(datFile);
				dbManager.read(progressUpdater, index);
				completed = true;
			} catch (final Throwable exc) {
				error = exc;
			}
		}
	}

}
