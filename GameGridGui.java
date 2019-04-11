import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JPanel;

import TetrisDisplayAndUtils.ButtonPresses;

import java.awt.Canvas;

@SuppressWarnings("serial")
public class GameGridGui extends JPanel implements GameGrid {

	private int gridSize = 30;
	private Graphics2D g2;
	private HashMap<Rectangle, Color> boardShapesMap = new HashMap<Rectangle, Color>();
	private HashMap<Rectangle, Color> shapesMap = new HashMap<Rectangle, Color>();
	private HashMap<Rectangle, Color> nextPieceMap = new HashMap<Rectangle, Color>();
	private HashMap<Rectangle, Color> holdPieceMap = new HashMap<Rectangle, Color>();
	private HashMap<Rectangle, Color> ghostShapesMap = new HashMap<Rectangle, Color>();
	private HashMap<Integer, Color> colorMap = new HashMap<Integer, Color>();

	GameGridGui() {
		createColorMap();
		setPreferredSize(new Dimension((gridSize * 10) + 100, (gridSize * 20)));
		repaint();
	}

	GameGridGui(int blockSize) {
		createColorMap();
		gridSize = blockSize;
		setPreferredSize(new Dimension((gridSize * 10) + 100, (gridSize * 20) + 1));
		repaint();
	}

	private void addBlocksToMap(int[][] piece, HashMap<Rectangle, Color> map, int x, int y) {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (piece[i][j] != 0) {
					map.put(new Rectangle((20 * j) + x, (20 * i + y), 20, 20), findColor(piece[i][j]));
				}
			}
		}
	}

	@Override
	public void clearBoard() {
		boardShapesMap = new HashMap<Rectangle, Color>();
		repaint();
	}

	@Override
	public void clearHoldPiece() {
		holdPieceMap = new HashMap<Rectangle, Color>();
	}

	public void clearShape() {
		shapesMap = new HashMap<Rectangle, Color>();
		ghostShapesMap = new HashMap<Rectangle, Color>();
		repaint();
	}

	private void createColorMap() {
		colorMap.put(1, Color.MAGENTA);
		colorMap.put(2, Color.blue);
		colorMap.put(3, Color.cyan);
		colorMap.put(4, Color.orange);
		colorMap.put(5, Color.yellow);
		colorMap.put(6, Color.green);
		colorMap.put(7, Color.red);
	}

	private void drawInvertedRectMap(HashMap<Rectangle, Color> map) {
		for (Map.Entry<Rectangle, Color> entry : map.entrySet()) {
			g2.setPaint(entry.getValue());
			g2.draw(entry.getKey());
		}
	}

	private void drawRectMap(HashMap<Rectangle, Color> map) {
		for (Map.Entry<Rectangle, Color> entry : map.entrySet()) {
			g2.setPaint(entry.getValue());
			g2.fill(entry.getKey());
			g2.setPaint(Color.black);
			g2.draw(entry.getKey());
		}
	}

	@Override
	public void fillBlock(int c, int x, int y) {
		Rectangle rec = new Rectangle(gridSize * x, gridSize * y, gridSize, gridSize);
		repaint();
	}

	private Color findColor(int piece) {
		return colorMap.getOrDefault(piece, Color.cyan);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g2 = (Graphics2D) g;
		g2.fill(new Rectangle(0, 0, 10 * gridSize + 1, 20 * gridSize + 1));
		g2.setFont(new Font("Tahoma", Font.BOLD, 14));
		g2.drawString("Next Piece:", (gridSize * 10) + 10, 80);
		g2.drawString("Hold Piece:", (gridSize * 10) + 10, 280);

		drawRectMap(boardShapesMap);
		drawInvertedRectMap(ghostShapesMap);
		drawRectMap(shapesMap);
		drawRectMap(holdPieceMap);
		drawRectMap(nextPieceMap);
	}

	@Override
	public void removePieceFromDisplay() {
		clearShape();
	}

	@Override
	public void renderPiece(int[][] piece, int x, int y) {
		clearShape();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (piece[i][j] != 0) {
					shapesMap.put(new Rectangle(gridSize * (x + j), gridSize * (y + i), gridSize, gridSize),
							findColor(piece[i][j]));
				}
			}
		}
		repaint();
	}

	@Override
	public void renderPieceWithGhost(int[][] piece, int x, int y, int offset) {
		clearShape();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if (piece[i][j] != 0) {
					shapesMap.put(new Rectangle(gridSize * (x + j), gridSize * (y + i), gridSize, gridSize),
							findColor(piece[i][j]));
					ghostShapesMap.put(
							new Rectangle(gridSize * (x + j), gridSize * (y + i + offset), gridSize, gridSize),
							findColor(piece[i][j]));
				}
			}
		}
		repaint();
	}

	@Override
	public void setBoard(int[][] board) {
		clearBoard();
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 10; j++) {
				if (board[i][j] != 0) {
					boardShapesMap.put(new Rectangle(gridSize * j, gridSize * i, gridSize, gridSize),
							findColor(board[i][j]));
				}
			}
		}
		repaint();
	}

	@Override
	public void setHoldPiece(int[][] piece) {
		holdPieceMap = new HashMap<Rectangle, Color>();
		addBlocksToMap(piece, holdPieceMap, (gridSize * 10) + 10, 300);
	}

	@Override
	public void setNextPiece(int[][] piece) {
		nextPieceMap = new HashMap<Rectangle, Color>();
		addBlocksToMap(piece, nextPieceMap, (gridSize * 10) + 10, 100);
	}
}
