package model;

import java.util.concurrent.TimeUnit;

import application.CommunicationPC;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import util.Point;
import view.GameOverviewController;

public class CommunicatorTask extends Task<Void> {

	private boolean gameRunning = true;
	private CommunicationPC communicationPC;
	private GameOverviewController gameOverviewController;
	private Determinator determinator;
	private Board board;

	private int playerNumber = 2;
	private int robotNumber = 1;

	public CommunicatorTask(CommunicationPC communicationPC, GameOverviewController gameOverviewController) {
		this.communicationPC = communicationPC;
		communicationPC.openConnection();
		this.gameOverviewController = gameOverviewController;
		board = new Board();
		determinator = new Determinator(board, robotNumber, playerNumber);
	}

	@Override
	protected Void call() throws Exception {

		while (gameRunning) {
			System.out.println("kuunnellaan vuoron va");
			communicationPC.receiveTurnChange();
			System.out.println("Turn change received");
			Point p = communicationPC.receiveDropPoint();
			System.out.println("Point received - x:" + p.x + " y:" + p.y);
			// con.addPlayerPiece(p);
			board.setPiece(p.x, playerNumber);
			Tester.printBoard(board);
			Point calculatedDropPoint = determinator.getNextMove();
			board.setPiece(calculatedDropPoint.x, robotNumber);
			communicationPC.sendDropPoint(calculatedDropPoint);
			Tester.printBoard(board);
			System.out.println("Sent point x: "+calculatedDropPoint.x + " y: "+calculatedDropPoint.y);

		}

		communicationPC.closeConnection();
		return null;
	}
}
