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

public class GrahamScan_ConvexHull extends JFrame {

    private ArrayList<Point> points;
    private boolean drawing;
    private Timer timer;
    public GrahamScan_ConvexHull() {
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
        	
        	

      
       ArrayList<Points<Double,Point>> A1= new ArrayList<>();
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
                    	A1.add(new Points<>(0.00,po));
                    //....creating list of angle points pair.	
                    	for(Point pi:points) {
                    		if (po!=pi) {
                    			A1.add(new Points<>(Math.atan2(pi.y-po.y, pi.x-po.x)*-1,pi));	
                    		}
                    	
                    	}
                    	
                    	//sort it
                    	 Comparator<Points<Double, Point>> comparator = new Comparator<Points<Double, Point>>() {
                             @Override
                             public int compare(Points<Double, Point> ps1, Points<Double, Point> ps2) {
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
                         for (Points<Double, Point> pointSet : A1) {
                             System.out.println(pointSet.getAngle()+"("+pointSet.getPoint().x+","+pointSet.getPoint().y+")"); // Assuming PointSet class has a meaningful toString() method
                         }
                    	
                         beforeLast=A1.get(1).getPoint();
                          last=A1.get(2).getPoint();
                     	 g.drawLine(po.x, po.y, beforeLast.x, beforeLast.y);                    	 
                    	 g.drawLine(beforeLast.x, beforeLast.y, last.x, last.y);
                    	 
                    	  try {
        					Thread.sleep(1000);
        				} catch (InterruptedException e) {
        					// TODO Auto-generated catch block
        					e.printStackTrace();
        				}
                    		  
                    	 int i=3;
                    	 int countercount=-1;
                    	 while(i<A1.size()) {
                    		 System.out.println("goloop");
                    		
                    		 TestingPoint=A1.get(i).getPoint();
                    		 System.out.println("beforelast: ("+beforeLast.x+","+beforeLast.y+")"+"last: ("+last.x+","+last.y+")"+"point: ("+ TestingPoint.x+","+ TestingPoint.y+")");
                   		 int CounterClockwise=ccw(TestingPoint ,last,beforeLast);

                    		 System.out.println(CounterClockwise);
                  			if(CounterClockwise==1) {
                  				countercount=-1;
                  				 System.out.println("counterwork");
                  				 g.drawLine(last.x, last.y, TestingPoint.x, TestingPoint.y);
                  				  try {
                  					Thread.sleep(200);
                  				} catch (InterruptedException e) {
                  					// TODO Auto-generated catch block
                  					e.printStackTrace();
                  				}
                  				 beforeLast=last;
                                  last=TestingPoint;
                                 
                                  i++;
                 			}
                  			else {
                  				countercount++;
                  				 System.out.println("counternotwork");
                  				g.setColor(Color.white);
                  				 g.drawLine(beforeLast.x, beforeLast.y, last.x, last.y);
                  				 System.out.println("("+beforeLast.x+","+beforeLast.y+")"+"("+last.x+","+last.y+")");
                  				  try {
                  					Thread.sleep(200);
                  				} catch (InterruptedException e) {
                  					// TODO Auto-generated catch block
                  					e.printStackTrace();
                  				}
                  				 g.setColor(Color.blue);
                  				last=beforeLast;
                  				beforeLast=A1.get(i-3-countercount).getPoint();
                  			}
                    		 
                    		 
                    	 }
                    	 g.drawLine( TestingPoint.x,  TestingPoint.y, A1.get(0).getPoint().x, A1.get(0).getPoint().y);
                    	 
                    	 
                    	 


////                    	pSet.add(last);
//                    	//.............................
//                    	g.setColor(Color.green);
////                    	 repaint();
//                    	 g.drawLine(po.x, po.y, beforeLast.x, beforeLast.y);
//                    	 
//                    	 g.drawLine(beforeLast.x, beforeLast.y, last.x, last.y);
//                    	 
////                    	g.setColor(Color.white);
////                    
////                    	 g.drawLine(po.x, po.y, beforeLast.x, beforeLast.y);
////                    	
////                    	 g.drawLine(beforeLast.x, beforeLast.y, last.x, last.y);
//                    	 for(Point pi:points) {
//                     		if(pi!=po&&pi!=last&&pi!=beforeLast) {
//                     			int CounterClockwise=ccw(beforeLast,last,pi);
//                     			if(CounterClockwise==1) {
//                     				 g.drawLine(last.x, last.y, pi.x, pi.y);
////                     				pSet.add(pi);
//                     				last=pi;
//                     				beforeLast=last;
//                     			}
//                     			else {
//                     				
//                     			}
//                     			
//                     		}
//                     	}
//                     	
//                     	
//                     	
//                     	
//                    	
//            			
//            			  try {
//          					Thread.sleep(50);
//          				} catch (InterruptedException e) {
//          					// TODO Auto-generated catch block
//          					e.printStackTrace();
//          				}
//            		
            	
        	
        	
        	
        	

        }
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
            GrahamScan_ConvexHull drawLines = new GrahamScan_ConvexHull();
            drawLines.setVisible(true);
          
        });
    }
}

class Points<A,P>{
	private A Angle;
    private P P1;
	
	public Points(A Angle,P P1) {
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