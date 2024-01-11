package pack1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class RayIntersection {

	public static void main(String[] args) {
		ArrayList<Point> points = new ArrayList<>();
		JFrame f = new JFrame();
		JPanel draw = new JPanel();
		f.setTitle("Point drawer");
		f.setSize(800, 800);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		 JPanel panel = new JPanel();
	       JLabel label = new JLabel("Now check whether the ray passing through point can intersect the line?");
	       JButton button = new JButton("Check it");
		JPanel drawingPanel = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				super.paintComponent(g);
				Point currentPoint = null;

				g.setColor(Color.red);
				if (!points.isEmpty()) {
//                	Point prevPoint=points.get(0);
					System.out.println(points.size());
					if(points.size()>=3) {
					      label.setVisible(true);
					       button.setVisible(true);
					}
					for (int i = 1; i < points.size(); i++) {
						Point prevPoint = points.get(i - 1);
						System.out.println("the points are " + (i + 1));
						if (i % 2 != 0) {
							currentPoint = points.get(i);
							g.drawLine(prevPoint.x, prevPoint.y, currentPoint.x, currentPoint.y);
							prevPoint = currentPoint;
						} else {
							prevPoint = currentPoint;

						}

					}
				}

				for (Point p : points) {
					g.setColor(Color.BLUE);
					g.fillOval(p.x, p.y, 3, 3); // Draw points as small circles

				}

			}
		};

		drawingPanel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (SwingUtilities.isLeftMouseButton(e)) {
					Point point = e.getPoint();
					points.add(point);
					drawingPanel.repaint(); // Refresh panel to draw the new point
				}
			}
		});
     
       
		button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean First_Condition=((points.get(0).y<=points.get(2).y)&&(points.get(2).y<points.get(1).y))||((points.get(1).y<=points.get(2).y)&&(points.get(2).y<points.get(0).y));
				int slope=(points.get(2).y-points.get(0).y)/(points.get(1).y-points.get(0).y);
				boolean Second_Condition= points.get(2).x<slope*(points.get(1).x-points.get(0).x)+points.get(0).x;
				
				boolean First_Condition_1=((points.get(0).x<=points.get(2).x)&&(points.get(2).x<points.get(1).x))||((points.get(1).x<=points.get(2).x)&&(points.get(2).x<points.get(0).x));
				int slope_1=(points.get(1).y-points.get(0).y)/(points.get(1).x-points.get(0).x);
				boolean Second_Condition_1= points.get(2).y<slope_1*(points.get(2).x-points.get(0).x)+points.get(0).y;
				
				
				boolean intersect=(First_Condition && Second_Condition)||(First_Condition_1 && Second_Condition_1);
				System.out.println(First_Condition+"first conditon");
				System.out.println(Second_Condition+"second dconditton");
				System.out.println(First_Condition_1+"first conditon");
				System.out.println(Second_Condition_1+"second dconditton");
				if(intersect==true) {
					label.setText("ok it intersects");
				}
				else {
					label.setText("it does not intersect");
				}
				
				
			}
			
		});

       label.setVisible(false);
       button.setVisible(false);
       panel.setLayout(new FlowLayout());

       // Add components to the panel
//       panel.add(drawingPanel);
       panel.add(label);
       panel.add(button);
       drawingPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); 
       panel.setBorder(BorderFactory.createEtchedBorder());
       JPanel panel2= new JPanel();
       panel2.add(new JLabel("make a line from y axis only."));
   
       f.setLayout(new BorderLayout());
     
       f.add(drawingPanel, BorderLayout.CENTER);
       f.add(panel, BorderLayout.SOUTH);
       f.add(panel2,BorderLayout.NORTH);

		f.setVisible(true);
	}
	
}
