package model;

import java.util.concurrent.TimeUnit;

import application.CommunicationPC;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import view.GameOverviewController;

public class CommunicatorTask extends Task<Void> {

	private boolean gameRunning = true;
	private CommunicationPC comm;
	private GameOverviewController controller;

	public CommunicatorTask() {
		this.comm = new CommunicationPC();
		this.controller = new GameOverviewController();
		comm.openConnection();
	}

	@Override
	protected Void call() throws Exception {
		
		while (gameRunning) {	
				//while (!comm.receiveTurnChange());
				System.out.println("threadii");
				TimeUnit.MILLISECONDS.sleep(1000);
			}

		comm.closeConnection();
		return null;
	}
}
