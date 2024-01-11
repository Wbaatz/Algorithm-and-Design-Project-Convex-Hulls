package pack2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class ConvexHull_BruteForce extends JFrame {

    private ArrayList<Point> points;
    private boolean drawing;
    private Timer timer;
    public ConvexHull_BruteForce() {
        points = new ArrayList<>();
        drawing = false;
        setTitle("Draw Lines between Points");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);

        JPanel buttonPanel = new JPanel();
        JButton drawButton = new JButton("Draw Lines");
        drawButton.addActionListener(new DrawButtonListener());
        buttonPanel.add(drawButton);

        add(buttonPanel, BorderLayout.SOUTH);
        addMouseListener(new CustomMouseListener());
        
        
    }

    class CustomMouseListener extends MouseAdapter {
        public void mouseClicked(MouseEvent e) {
            if (!drawing) {
                points.add(e.getPoint());
                
                repaint();
            }
        }
    }

    class DrawButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (points.size() > 1) {
                drawing = true;
                repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Please create at least 2 points.");
            }
        }
    }

    public void paint(Graphics g) {
        super.paint(g);
        for (Point p : points) {
			g.setColor(Color.BLUE);
			g.fillOval(p.x, p.y, 5, 5); // Draw points as small circles

		}
        if (drawing && points.size() > 1) {
        	
        	int leftside=0;
        	int rightside=0;
        	int along=0;
        	boolean flag=true;
       ArrayList<PointSet<Point,Point>> pSet= new ArrayList<>();
        	for(Point pi:points) {
        		for(Point pj:points) {
            		if(pj!=pi) {
            			
            			for(Point pk:points) {
            				
            				
            				
            				if(pk!=pj &&pk!=pi) {
            					
            					int c1=(pj.y-pi.y)*(pk.x)+(pi.x-pj.x)*(pk.y);
            					int c=pi.x*pj.y-pi.y*pj.x;
            				
            					if((c1-c)<0) {
            						leftside++;
            					}
            					else if((c1-c)>0) {
            						rightside++;
            					}
            					else {
            						 along++;
            					}
            				}
            				
            			}
//            			
            			System.out.println(points.size()-2+" left: "+leftside+" right: "+rightside+" along: "+along);
            			System.out.println("p1:"+pi+"p2:"+pj);
            			if(leftside==points.size()-2||rightside==points.size()-2) {
            				 for(PointSet<Point,Point> pair :pSet) {
            					 if(pair.getFirst()==pi && pair.getSecond()==pj || pair.getFirst()==pj && pair.getSecond()==pi) {
            						 flag=false;
            					 }
            					 
            				 }
            				 if(flag==true) {
            						System.out.println("drawn the line");
                    				System.out.println("p1:"+pi+"p2:"+pj);
                    				 g.drawLine(pi.x, pi.y, pj.x, pj.y);
                    				 pSet.add(new PointSet<>(pi,pj));
            				 }
            			     flag=true;
            				 
            				
            			}
        				leftside=0;
        				rightside=0;
        				along=0;
            			
            			  try {
          					Thread.sleep(50);
          				} catch (InterruptedException e) {
          					// TODO Auto-generated catch block
          					e.printStackTrace();
          				}
            		}
            	}
        	}
        	
        	
        	

        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ConvexHull_BruteForce drawLines = new ConvexHull_BruteForce();
            drawLines.setVisible(true);
          
        });
    }
}

class PointSet<T,U>{
	private T p1;
	private U p2;
	
	public  PointSet(T p1,U p2) {
		this.p1=p1;
		this.p2=p2;
	}
	public T getFirst() {
		return this.p1;
	}
	public U getSecond() {
		return this.p2;
	}
	
	
}
