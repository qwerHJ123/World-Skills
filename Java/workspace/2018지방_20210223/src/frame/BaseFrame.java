package frame;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;

public class BaseFrame extends JFrame {

	static Connection connection;
	static Statement statement;

	static String userName;
	static int userNo;

	static DefaultTableCellRenderer centerRender = new DefaultTableCellRenderer();
	static Border emptyBorder;
	static {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost/meal?serverTimezone=UTC", "user", "1234");
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		emptyBorder = BorderFactory.createEmptyBorder(10, 0, 10, 0);

		userName = "강해준"; // 로그인 안 했을 때 테스트용
		userNo = 1;

		centerRender.setHorizontalAlignment(JLabel.CENTER);
	}

	public BaseFrame(int width, int height, String title) {
		setSize(width, height);
		setTitle(title);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}

	public static void errorMsg(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Message", JOptionPane.ERROR_MESSAGE);
	}

	public static void informationMsg(String msg) {
		JOptionPane.showMessageDialog(null, msg, "Message", JOptionPane.INFORMATION_MESSAGE);
	}

	public static JLabel createLabel(JLabel label, Font font) {
		label.setFont(font);
		return label;
	}

	public static <T extends JComponent> T createComponent(T comp, int x, int y, int width, int height) {
		comp.setBounds(x, y, width, height);
		return comp;
	}

	public static <T extends JComponent> T createComponent(T comp, int width, int height) {
		comp.setPreferredSize(new Dimension(width, height));
		return comp;
	}

	public static JButton createButton(String txt, ActionListener e) {
		JButton button = new JButton(txt);
		button.addActionListener(e);
		return button;
	}

	public static JButton createButtonWithImage(ImageIcon icon, ActionListener e) {
		JButton button = new JButton(icon);
		button.addActionListener(e);
		return button;
	}

	public static JButton createButtonWithoutMargin(String txt, ActionListener e) {
		JButton button = new JButton(txt);
		button.addActionListener(e);
		button.setMargin(new Insets(0, 0, 0, 0));
		return button;
	}

	public static ImageIcon getImage(int width, int height, String path) {
		return new ImageIcon(
				Toolkit.getDefaultToolkit().getImage(path).getScaledInstance(width, height, Image.SCALE_SMOOTH));
	}

	public void openFrame(JFrame frame) {
		dispose();
		frame.setVisible(true);
	}
}
