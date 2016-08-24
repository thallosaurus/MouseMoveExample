import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.AWTException;
import java.awt.Color;
import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.Point;
import java.awt.Robot;

public class MouseMoveExample extends JPanel implements MouseMotionListener {
	
	public static final long serialVersionUID = 42L;
	
	JLabel xPos = new JLabel();
	JLabel yPos = new JLabel();
	JLabel colorRed = new JLabel();
	JLabel colorGreen = new JLabel();
	JLabel colorBlue = new JLabel();
	
	public int xPositionAsInt() {
		Point i = MouseInfo.getPointerInfo().getLocation();
		return i.x;
	}
	
	public int yPositionAsInt() {
		Point i = MouseInfo.getPointerInfo().getLocation();
		return i.y;
	}
	
	public void mouseMoved(MouseEvent e) {
		Robot robert = null;
		
		try {
			//TODO Enable MotionListener outside window
			robert = new Robot();
		} catch (AWTException exception) {
			exception.printStackTrace();
		}
		
			PointerInfo i = MouseInfo.getPointerInfo();
			Point b = i.getLocation();
			Color col = robert.getPixelColor(b.x, b.y);
			//make Colors public
				colorRed.setText("Rot: " + String.valueOf(col.getRed()));
				colorGreen.setText("Grün: " + String.valueOf(col.getGreen()));
				colorBlue.setText("Blau: " + String.valueOf(col.getBlue()));
			//System.out.println("Mouse Moved!: " + b.x + "/" + b.y + "\nColor: Red: " + col.getGreen() + ", Green: " + col.getGreen()+ ", Blue: " + col.getBlue());
			xPos.setText("X-Koordinate: " + String.valueOf(xPositionAsInt()));
			yPos.setText("Y-Koordinate: " + String.valueOf(yPositionAsInt()));
	}
	
	public void mouseDragged(MouseEvent e) {
		Robot robert = null;
		try {
			//TODO Enable MotionListener outside window
			robert = new Robot();
		} catch (AWTException exception) {
			exception.printStackTrace();
		}
		
			PointerInfo i = MouseInfo.getPointerInfo();
			Point b = i.getLocation();
			Color col = robert.getPixelColor(b.x, b.y);
			//make Colors public
				colorRed.setText("Rot: " + String.valueOf(col.getRed()));
				colorGreen.setText("Grün: " + String.valueOf(col.getGreen()));
				colorBlue.setText("Blau: " + String.valueOf(col.getBlue()));
			//System.out.println("Mouse Moved!: " + b.x + "/" + b.y + "\nColor: Red: " + col.getGreen() + ", Green: " + col.getGreen()+ ", Blue: " + col.getBlue());
			xPos.setText("X-Koordinate: " + String.valueOf(xPositionAsInt()));
			yPos.setText("Y-Koordinate: " + String.valueOf(yPositionAsInt()));
	}
	
	public void createWindow() {
		JFrame window1 = new JFrame("MouseMotionListenerAPI Example");
		window1.setSize(500, 450);
		JPanel panel = new JPanel();
		window1.add(panel);
		panel.add(xPos);
		panel.add(yPos);
			panel.add(colorBlue);
			panel.add(colorGreen);
			panel.add(colorRed);
			
		//Creating a Picture
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("default.jpg"));
		} catch (IOException io) {
			io.printStackTrace();
		}
		
		JLabel pic = new JLabel();
		pic.setIcon(new ImageIcon(img));
		
		pic.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChosen = new JFileChooser();
				fileChosen.showOpenDialog(pic);
				BufferedImage imgBuffer = null;
				try {
					imgBuffer = ImageIO.read(fileChosen.getSelectedFile());
					pic.setIcon(new ImageIcon(imgBuffer));
				} catch (IOException ioe) {
					ioe.printStackTrace();
				}
			}
		});
		pic.addMouseMotionListener(this);
		pic.setToolTipText("Klicken, zum Bild wechseln");
		panel.add(pic);
		
		window1.addMouseMotionListener(this);
		//window1.pack();
		window1.setVisible(true);
	}
	
	public static void main(String[] args) {
		//bootstrapper
		try {
			MouseMoveExample obj = new MouseMoveExample();
			obj.createWindow();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
