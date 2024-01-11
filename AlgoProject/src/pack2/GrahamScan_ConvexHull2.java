package pack2;

import javax.crypto.spec.GCMParameterSpec;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GrahamScan_ConvexHull2 extends JFrame {

    private ArrayList<Point> points;
    private boolean drawing;
    private Timer timer;
    public GrahamScan_ConvexHull2() {
        points = new ArrayList<>();
        drawing = false;
        setTitle("Draw Lines between Points");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null);
        getContentPane().setBackground(Color.WHITE);
         
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
        	 g.setColor(Color.blue);
			
			g.fillOval(p.x, p.y, 5, 5); // Draw points as small circles
			
		}
        if (drawing && points.size() > 1) {
        	
        	

      
       ArrayList<Points2<Double,Point>> A1= new ArrayList<>();
       ArrayList<Point> A2= new ArrayList<>();
//       pSet.add(new PointSet1<>(pi,pj));
                    			
//                    				 g.drawLine(pi.x, pi.y, pj.x, pj.y);
        	int miny=0;
        	Point po = null;
        	Point beforeLast = null;
        	Point last = null;
        	Point TestingPoint = null;
        	double angle;
        	double tempAngle=0;
        	System.out.println(po);
                    	for(Point pi:points) {
                    		if (miny<pi.y) {
                    			miny=pi.y;
                    			po=pi;
                    		}
                    	
                    	}
                    	A1.add(new Points2<>(0.00,po));
                    	A2.add(po);
                    //....creating list of angle points pair.	
                    	for(Point pi:points) {
                    		if (po!=pi) {
                    			A1.add(new Points2<>(Math.atan2(pi.y-po.y, pi.x-po.x)*-1,pi));	
                    		}
                    	
                    	}
                    	
                    	//sort it
                    	 Comparator<Points2<Double, Point>> comparator = new Comparator<Points2<Double, Point>>() {
                             @Override
                             public int compare(Points2<Double, Point> ps1, Points2<Double, Point> ps2) {
                                 // Assuming the Double value is accessed through a method like getDoubleValue()
                                 // Replace getDoubleValue() with the appropriate method name
                                 Double doubleValue1 = ps1.getAngle();
                                 Double doubleValue2 = ps2.getAngle();
                                 
                                 // Compare Double values for ascending order
                                 return Double.compare(doubleValue1, doubleValue2);
                             }
                         };

                         // Sort the ArrayList using the custom Comparator
                         Collections.sort(A1, comparator);
                         for (Points2<Double, Point> pointSet : A1) {
                             System.out.println(pointSet.getAngle()+"("+pointSet.getPoint().x+","+pointSet.getPoint().y+")"); // Assuming PointSet class has a meaningful toString() method
                         }
                         
                         ///lets come to animation now.
                         AnimateIn(po,A1.get(1).getPoint(),g);
                         AnimateIn(A1.get(1).getPoint(),A1.get(2).getPoint(),g);
                         
                         
                         A2.add(A1.get(1).getPoint());
                         A2.add(A1.get(2).getPoint());
                         int M=A2.size();
                         for(int i=3;i<A1.size();i++) {
                        	 while(A2.size()>=2 && ccw(A1.get(i).getPoint(),A2.get(A2.size()-1),A2.get(A2.size()-2))!=1) {
                        		 AnimateIn(A2.get(A2.size()-1),A1.get(i).getPoint(),g);
                        		 AnimateOut(A2.get(A2.size()-1),A1.get(i).getPoint(),g);
                        		 AnimateOut(A2.get(A2.size()-2),A2.get(A2.size()-1),g);
                        		 A2.remove(A2.size()-1);
                        	 }
                         
                        	 	
                        	 A2.add(A1.get(i).getPoint());
                     
                          	 AnimateIn(A2.get(A2.size()-2),A2.get(A2.size()-1),g);
//                           	 AnimateOut(A2.get(A2.size()-2),A2.get(A2.size()-1),g);
                        	 	
                         }
                         for(int i=0;i<A2.size();i++) {
                        	 if(i==A2.size()-1) {
                        		 break;
                        	 }
//                        	 AnimateIn(A2.get(i),A2.get(i+1),g);
                        
           			
                        	  
                         }
                         g.drawLine(A2.get(A2.size()-1).x,A2.get(A2.size()-1).y, A2.get(0).x,A2.get(0).y);
//                         AnimateIn(A2.get(A2.size()-1),A2.get(0),g);
                         
                        

                    	 



        	

        }
    }

    
    public static void AnimateIn(Point p1,Point p2,Graphics g) {
      	 try {
   			Thread.sleep(20);
   		} catch (InterruptedException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
   		
   	  g.drawLine(p1.x, p1.y, p2.x, p2.y);   
       }
       
       public static void AnimateOut(Point p1,Point p2,Graphics g) {
         	 try {
      			Thread.sleep(20);
      		} catch (InterruptedException e) {
      			// TODO Auto-generated catch block
      			e.printStackTrace();
      		}
      		g.setColor(Color.white);
      	  g.drawLine(p1.x, p1.y, p2.x, p2.y);   
      	g.setColor(Color.blue);
          }
    
	public static int ccw(Point p1,Point p2,Point p3) {
		int num=(p3.y-p2.y)*(p2.x-p1.x)-(p2.y-p1.y)*(p3.x-p2.x);
		if(num>0) {
			return 1;
		}
		else if(num<0) {
			return -1;
		}
		else {
			return 0;
		}
	}
    
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GrahamScan_ConvexHull2 drawLines = new GrahamScan_ConvexHull2();
            drawLines.setVisible(true);
          
        });
    }
}

class Points2<A,P>{
	private A Angle;
    private P P1;
	
	public Points2(A Angle,P P1) {
		this.P1=P1;
		this.Angle=Angle;
		
	}
	public A getAngle() {
		return this.Angle;
	}
	public P getPoint() {
		return this.P1;
	}
	
	
	
}