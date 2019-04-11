
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.SystemColor;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.JButton;
import java.awt.Cursor;
import javax.swing.UIManager;

public class ItemBuilder {
	
	//private static int defaultFontSize = 18;
	//private static String defaultFont = "Tahoma";
	 
	private static Font defaultLabelFont = new Font("Tahoma", Font.PLAIN, 18);

	public static JTextField makeField(int x, int y, int w, int h) {
		
		return makeField(x,y,w,h, defaultLabelFont);
	}
	
	public static JTextField makeField(int x, int y, int w, int h, int s) {
		
		return makeField(x, y, w, h, new Font("Tahoma", Font.BOLD, s));
	}
	
	public static JTextField makeField(int x, int y, int w, int h, Font f) {
		JTextField field = new JTextField();
		field.setBorder(null);
		field.setEditable(false);
		field.setBounds(x, y, w, h);
		field.setFont(f);
		return field;
	}
	
	public static JLabel makeLabel(String text, int x, int y, int w, int h) {

		return makeLabel(text, x, y, w, h, defaultLabelFont);
	}
	
	public static JLabel makeLabel(String text, int x, int y, int w, int h, int s) {
	
		return makeLabel(text, x, y, w, h, new Font("Tahoma", Font.BOLD, s));
	}
	
	public static JLabel makeLabel(String text, int x, int y, int w, int h, Font s) {
		JLabel label = new JLabel(text);
		label.setBounds(x, y, w, h);
		label.setFont(s);
		return label;
	}
	
	public static JButton makeButton(String text, int x, int y, int w, int h) {
		JButton button = new JButton(text);
		button.setBounds(x, y, w, h);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setBorder(UIManager.getBorder("Button.border"));
		button.setBackground(SystemColor.scrollbar);
		button.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
		button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		return button;
	}
	
	public static JButton makeMenuButton(String text, int x, int y, int w, int h) {
		JButton button = new JButton(text);
		button.setBounds(x, y, w, h);
		button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		button.setBorder(UIManager.getBorder("Button.border"));
		button.setBackground(SystemColor.scrollbar);
		button.getInputMap().put(KeyStroke.getKeyStroke("SPACE"), "none");
		button.setFont(new Font("Tahoma", Font.PLAIN, 12));
		return button;
	}
}
