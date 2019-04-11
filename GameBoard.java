
public class GameBoard {

	private int gameGrid[][] = new int[20][10];

	public GameBoard() {
		clearGameBoard();
	}

	public void addPieceToBoard(int[][] piece, int x, int y) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (piece[i][j] != 0) {
					addSpaceToGrid(piece[i][j], x + j, y + i);
				}
			}
		}
	}

	public void addSpaceToGrid(int c, int x, int y) {
		gameGrid[y][x] = c;
	}

	public boolean canPieceFit(int[][] piece, int x, int y) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (piece[i][j] != 0) {
					if (x + j >= 10 || x + j < 0 || y + i >= 20) {
						return false;
					}
					if (!isSpaceOpen(x + j, y + i)) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public int checkAndClearLinesGetNumCleared() {
		int lines = 0;
		for (int i = 0; i < 20; i++) {
			if (isRowFull(19 - i)) {
				moveLinesDown(19 - i);
				lines++;
				i--;
			}
		}
		return lines;
	}

	public void clearGameBoard() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 20; j++) {
				gameGrid[j][i] = 0;
			}
		}
	}

	public int[][] getBoard() {
		return gameGrid;
	}

	private boolean isRowFull(int row) {
		for (int i = 0; i < 10; i++) {
			if (gameGrid[row][i] == 0) {
				return false;
			}
		}
		return true;
	}

	public boolean isSpaceOpen(int x, int y) {
		if (gameGrid[y][x] == 0) {
			return true;
		} else {
			return false;
		}
	}

	private void moveLinesDown(int line) {
		for (int i = 0; i < line; i++) {
			for (int j = 0; j < 10; j++) {
				gameGrid[line - i][j] = gameGrid[line - i - 1][j];
			}
		}
	}
}
