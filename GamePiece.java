import java.util.ArrayList;
import java.util.Random;

public class GamePiece {

	private int currentPiece[][] = new int[4][4];
	private int nextPiece[][] = new int[4][4];
	private int holdPiece[][] = new int[4][4];
	private int[] order = { 0, 1, 2, 3, 4, 5, 6 };
	private int arrayCounter = 0;
	private boolean holdPieceExists = false;
	private ArrayList<int[][]> pieces = new ArrayList<int[][]>();
	public enum rotation {
		Clockwise, CounterClockwise
	}

	private int[][] T = new int[][] { { 0, 1, 0, 0 }, { 1, 1, 1, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };

	private int[][] J = new int[][] { { 2, 0, 0, 0 }, { 2, 2, 2, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };

	private int[][] I = new int[][] { { 0, 0, 0, 0 }, { 3, 3, 3, 3 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };

	private int[][] L = new int[][] { { 0, 0, 4, 0 }, { 4, 4, 4, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };

	private int[][] O = new int[][] { { 5, 5, 0, 0 }, { 5, 5, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };

	private int[][] S = new int[][] { { 0, 6, 6, 0 }, { 6, 6, 0, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };

	private int[][] Z = new int[][] { { 7, 7, 0, 0 }, { 0, 7, 7, 0 }, { 0, 0, 0, 0 }, { 0, 0, 0, 0 } };

	public GamePiece() {
		pieces.add(T);
		pieces.add(J);
		pieces.add(I);
		pieces.add(L);
		pieces.add(O);
		pieces.add(S);
		pieces.add(Z);
		shuffleArray(order);
		currentPiece = getNextPieceinArray();
		nextPiece = getNextPieceinArray();
	}

	public int[][] getGamePiece() {
		return currentPiece;
	}

	public int[][] getNextPiece() {
		return nextPiece;
	}

	private int[][] getNextPieceinArray() {
		if (arrayCounter == 7) {
			arrayCounter = 0;
			shuffleArray(order);
		}
		return pieces.get(order[arrayCounter++]);
	}

	public void nextPiece() {
		currentPiece = nextPiece;
		nextPiece = getNextPieceinArray();
	}

	public void holdPiece() {
		int[][] tmpPiece = currentPiece;
		if (!holdPieceExists) {
			nextPiece();
			holdPieceExists = true;
		} else {
			currentPiece = holdPiece;
		}
		holdPiece = tmpPiece;
	}

	public void clearHoldPiece() {
		holdPiece = new int[4][4];
		holdPieceExists = false;
	}

	public int[][] getHoldPiece() {
		return holdPiece;
	}

	private void shuffleArray(int[] ar) {
		Random rnd = new Random();
		for (int i = 6; i > 0; i--) {
			int index = rnd.nextInt(i + 1);
			int a = ar[index];
			ar[index] = ar[i];
			ar[i] = a;
		}
	}

	public void rotatePiece(rotation rotation) {
		currentPiece = pieceRotation(rotation);
	}

	public int[][] pieceRotation(rotation rotation) {
		int mSize = 0;
		int[][] newPiece = new int[4][4];
		for (int g = 0; g < 4; g++) {
			for (int h = 0; h < 4; h++) {
				newPiece[g][h] = 0;
				if (currentPiece[g][h] != 0) {
					if (g > mSize) {
						mSize = g;
					}
					if (h > mSize) {
						mSize = h;
					}
				}
			}
		}
		for (int i = 0; i <= mSize; i++) {
			for (int j = 0; j <= mSize; j++) {
				if (rotation.equals(GamePiece.rotation.Clockwise)) {
					newPiece[i][j] = currentPiece[mSize - j][i];
				}
				if (rotation.equals(GamePiece.rotation.CounterClockwise)) {
					newPiece[i][j] = currentPiece[j][mSize - i];
				}
			}
		}
		return newPiece;
	}
}
