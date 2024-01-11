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

public class Intersection_through_endpoints {

	public static void main(String[] args) {
		ArrayList<Point> points = new ArrayList<>();
		JFrame f = new JFrame();
		JPanel draw = new JPanel();
		f.setTitle("Point drawer");
		f.setSize(400, 400);
		f.setDefaultCloseOperation(f.EXIT_ON_CLOSE);
		 JPanel panel = new JPanel();
	       JLabel label = new JLabel("Now check whether these 2 lines intersect or not?");
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
					if(points.size()>=4) {
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
				
				boolean intersect;
				//point0 x1,y1
				//point1 x2,y2
				//point2 p1,q1
				//point3 p2,q2
				
				int a1=(points.get(1).y-points.get(0).y)*points.get(2).x+(points.get(0).x-points.get(1).x)*points.get(2).y+(points.get(1).x*points.get(0).y-points.get(0).x*points.get(1).y);
				if(a1<0) {
					int a2=(points.get(1).y-points.get(0).y)*points.get(3).x+(points.get(0).x-points.get(1).x)*points.get(3).y+(points.get(1).x*points.get(0).y-points.get(0).x*points.get(1).y);
					
					if(a2>=0) {
						label.setText("ok it intersects");
					}
					else if(a2<0) {
						label.setText("it does not intersect");
					}
				}
				else if(a1>0) {
					int a2=(points.get(1).y-points.get(0).y)*points.get(3).x+(points.get(0).x-points.get(1).x)*points.get(3).y+(points.get(1).x*points.get(0).y-points.get(0).x*points.get(1).y);
					if(a2<=0) {
						label.setText("ok it intersects");
					}
					else if(a2>0) {
						label.setText("it does not intersect");
					}
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
       
   
       f.setLayout(new BorderLayout());
     
       f.add(drawingPanel, BorderLayout.CENTER);
       f.add(panel, BorderLayout.SOUTH);

		f.setVisible(true);

	}

}
