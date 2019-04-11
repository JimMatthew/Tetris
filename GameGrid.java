
public interface GameGrid {

	void clearBoard();

	void fillBlock(int c, int x, int y);

	void removePieceFromDisplay();

	void renderPiece(int[][] piece, int x, int y);

	void renderPieceWithGhost(int[][] piece, int x, int y, int offset);

	void setBoard(int[][] board);

	void setNextPiece(int[][] piece);

	void setHoldPiece(int[][] piece);

	void clearHoldPiece();

	void clearShape();
}