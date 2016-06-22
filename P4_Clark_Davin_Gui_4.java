import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Davin Clark P4 Feb 20, 2016 Time: 4 hours
 * 
 * This lab was a lot of fun to write, as well as to play with afterwards. I coded most of it on my
 * way to Tahoe, and that is where I got the idea of making random trees(since I saw lots of trees).
 * The program itself wasn't to difficult, though I did have to play with the proportions a lot to 
 * allow the sizes to be changed. This time I wrote my code a lot quicker, and I had a pretty good 
 * idea of what i was doing
 * 
 */

public class P4_Clark_Davin_Gui_4 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Forest a = new Forest();
	}

}

class Forest {

	
	JRadioButton fluffy;
	JRadioButton pointy;
	JRadioButton dead;
	ButtonGroup type;
	JRadioButton dark;
	JRadioButton light;
	JRadioButton sick;
	ButtonGroup color;
	JSlider size;
	JButton add;
	JTextArea text;
	Draw drawing;

	int flufCount = 0;
	int pointCount = 0;
	int deadCount = 0;
	int darkCount = 0;
	int lightCount = 0;
	int sickCount = 0;

	ArrayList<Tree> list = new ArrayList<Tree>();
	int Nsize = 5;
	Color Nc = new Color(0, 84, 21);
	String Ntype = "fluffy";
	int start = 0;

	Forest() {
		JFrame window = new JFrame("Davin Clark");

		// Decide what to do when the user closes the window
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Set the window size (see API)
		window.setBounds(200, 200, 1080, 430);

		// Prevent users from resizing the window
		window.setResizable(false);

		JPanel leftPan = new JPanel(new FlowLayout());
		leftPan.setPreferredSize(new Dimension(300, 100));

		JPanel leftSpacer = new JPanel();
		leftSpacer.setPreferredSize(new Dimension(200, 50));
		leftPan.add(leftSpacer);

		fluffy = new JRadioButton("Fluffy");
		pointy = new JRadioButton("Pointy");
		dead = new JRadioButton("Dead");
		fluffy.setSelected(true);
		type = new ButtonGroup();
		fluffy.addActionListener(new typeL());
		pointy.addActionListener(new typeL());
		dead.addActionListener(new typeL());
		type.add(fluffy);
		type.add(pointy);
		type.add(dead);
		JPanel typePan = new JPanel(new FlowLayout());
		typePan.add(fluffy);
		typePan.add(pointy);
		typePan.add(dead);
		typePan.setBorder(new TitledBorder("Type"));
		leftPan.add(typePan);

		dark = new JRadioButton("Dark");
		light = new JRadioButton("Light");
		sick = new JRadioButton("Sick");
		color = new ButtonGroup();
		dark.setSelected(true);
		dark.addActionListener(new colorL());
		light.addActionListener(new colorL());
		sick.addActionListener(new colorL());
		color.add(dark);
		color.add(light);
		color.add(sick);
		JPanel colPan = new JPanel(new FlowLayout());
		colPan.add(dark);
		colPan.add(light);
		colPan.add(sick);
		colPan.setBorder(new TitledBorder("Color"));
		leftPan.add(colPan);

		size = new JSlider();
		size.setMaximum(5);
		size.setMinimum(1);
		size.setMajorTickSpacing(1);
		size.setMinorTickSpacing(1);
		size.setPaintLabels(true);
		size.addChangeListener(new sizeL());
		JPanel sizePan = new JPanel(new FlowLayout());
		sizePan.add(size);
		sizePan.setBorder(new TitledBorder("Size"));
		leftPan.add(sizePan);

		add = new JButton("Add Tree");
		add.addActionListener(new addL());
		leftPan.add(add);

		JButton clear = new JButton("Clear");
		clear.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				drawing.repaint();
				flufCount = 0;
				pointCount = 0;
				deadCount = 0;
				darkCount = 0;
				lightCount = 0;
				sickCount = 0;
				text.setText("Just an empty field.\r\n\r\n" + getStats());
			}

		});
		leftPan.add(clear);

		JPanel midPan = new JPanel(new FlowLayout());

		drawing = new Draw();
		drawing.setPreferredSize(new Dimension(400, 400));
		midPan.add(drawing);

		text = new JTextArea("Just an empty field.\r\n\r\n" + getStats());
		text.setFont(new Font("Times New Roman", Font.PLAIN, 15));
		text.setEditable(false);
		JPanel rightPan = new JPanel(new FlowLayout());
		rightPan.setPreferredSize(new Dimension(300, 400));
		JPanel spaceR = new JPanel(); 
		spaceR.setPreferredSize(new Dimension(300, 100));
		rightPan.add(spaceR);
		rightPan.add(text);
		window.getContentPane().add(rightPan, BorderLayout.LINE_END);

		window.getContentPane().add(leftPan, BorderLayout.LINE_START);
		window.getContentPane().add(midPan, BorderLayout.CENTER);

		window.setVisible(true);
	}

	private String getStats() {
		return "Fluffy Count: " + flufCount + "   Dark Count: " + darkCount + "\r\nPointy Count: " + pointCount
				+ "   Light Count: " + lightCount + "\r\nDead Count: " + deadCount + "   Sick Count: " + sickCount
				+ "\r\nTotal: " + (flufCount + deadCount + pointCount);
	}

	private class colorL implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == dark) {
				Nc = new Color(0, 84, 21);
			}
			if (e.getSource() == light) {
				Nc = new Color(126, 214, 148);
			}
			if (e.getSource() == sick) {
				int r = (int) (Math.random() * 255);
				int g = (int) (Math.random() * 255);
				int b = (int) (Math.random() * 255);
				Nc = new Color(r, g, b);
			}
		}
	}

	private class typeL implements ActionListener {

		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == fluffy) {
				Ntype = "fluffy";

			}
			if (e.getSource() == pointy) {
				Ntype = "pointy";

			}
			if (e.getSource() == dead) {
				Ntype = "dead";
			}
		}
	}

	private class sizeL implements ChangeListener {

		@Override
		public void stateChanged(ChangeEvent e) {
			// TODO Auto-generated method stub
			Nsize = size.getValue();
		}
	}

	private class addL implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			boolean lit = false;

			if (sick.isSelected()) {
				int r = (int) (Math.random() * 255);
				int g = (int) (Math.random() * 255);
				int b = (int) (Math.random() * 255);
				Nc = new Color(r, g, b);
				sickCount++;
			}
			if (Nc.equals(new Color(0, 84, 21))) {
				darkCount++;
			}
			if (Nc.equals(new Color(126, 214, 148))) {
				lightCount++;
			}
			if (Ntype.equals("fluffy")) {
				flufCount++;
			} else if (Ntype.equals("pointy")) {
				pointCount++;
			} else {
				deadCount++;
			}
			list.add(new Tree(Nsize, Nc, Ntype));
			Graphics2D g = (Graphics2D) drawing.getGraphics();
			int change = 25-Nsize;
			for (int i = start; i < list.size(); i++) {
				list.get(i).draw(g, (int) (Math.random() * 400), (int) (Math.random() * 250) + 100+change);
			}

			start = list.size();
			String state = "";
			int total = (flufCount + deadCount + pointCount);
			if (total == 1) {
				state = "Does 1 tree count as a forest?";
			} else if (total == 2) {
				state = "Wow two trees, so many.";
			} else if (total == 3) {
				state = "Tfti to the TREEsome";
			} else if (total < 8) {
				state = "This forest is Oak-kay";
			} else if (total < 15) {
				state = "Tree + Tree = Sticks";
			} else if (total < 30) {
				state = "What a tree-rific group";

			} else {
				state = "Too many trees, you need to leaf";
			}

			if (sickCount > 9) {
				state += "\r\nThis Forest is LIT";
				lit = true;
			}
			state += "\r\n";
			text.setText(state + "\r\n" + getStats());
			if (lit) {
				g.setBackground(Color.BLACK);

				for (int i = 0; i < 8; i++) {
					int r = (int) (Math.random() * 255);
					int gr = (int) (Math.random() * 255);
					int b = (int) (Math.random() * 255);

					g.setColor(new Color(r, gr, b));
					g.setStroke(new BasicStroke(10));
					g.drawLine(160 + (10 * i), 100, 80 + 30 * i, 0);

				}

			}
		}
	}

	private class Draw extends JPanel {

		public void paintComponent(Graphics g) {

			super.paintComponent(g);
			// Draw stuff
			setBackground(new Color(169, 183, 153));
			Graphics2D g2 = (Graphics2D) g;
			g2.setColor(new Color(151, 223, 240));
			g2.fillRect(0, 0, 400, 100);

		}

	}

	private class Tree {
		private int size;
		private Color c;
		private String type;

		Tree(int s, Color col, String str) {
			size = s;
			c = col;
			type = str;
		}

		public void draw(Graphics2D g, int x, int y) {
			if (type.equals("pointy")) {
				g.setColor(new Color(102, 59, 59));
				g.fillRect(x - 2 * size, y - 60 + (30 * size), 4 * size, 3 * size);
				g.setColor(this.c);
				int mid = x;
				for (int i = 0; i < 30 * size; i += 2) {
					g.drawLine(mid, y - 60 + i, (int) (mid - (i * 0.25)), (y - 50 + i));
					g.drawLine(mid, y - 60 + i, (int) (mid + (i * 0.25)), (y - 50 + i));
				}
			} else if (type.equals("fluffy")) {
				g.setColor(new Color(225, 125, 125));
				g.fillRect(x - 2 * size, y + size * 15, 4 * size, 6 * size);
				g.setColor(this.c);
				for (int i = 0; i < 5 * size; i++) {
					int ranX = (int) (Math.random() * size * 10) + x - size * 10;
					int ranY = (int) (Math.random() * size * 10) + y;
					g.drawOval(ranX, ranY, size * 10, size * 10);
				}
			} else {
				g.setColor(new Color(100, 100, 100));
				g.fillRect(x, y, 4 * size, 3 * size);
			}
		}
	}

}
