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

public class ConvexHull_JarvisMarch extends JFrame {

    private ArrayList<Point> points;
    private boolean drawing;
    private Timer timer;
    public ConvexHull_JarvisMarch() {
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
        	
       ArrayList<Point> Hull = new ArrayList<>();
       
 
        	int miny=0;
        	Point po = null;
            Point NextPoint;
        	double angle;
        	double tempAngle=0;
        	System.out.println(po);
                    	for(Point pi:points) {
                    		if (miny<pi.y) {
                    			miny=pi.y;
                    			po=pi;
                    		}
                    	
                    	}
                    	             
                    	 
                 //start dancing here...................
                    	  
                    	  while(true)
                    	  {
                    		  Hull.add(po);
                    	  NextPoint=points.get(0);
                    	
                    	  
                    	  AnimateIn(po,NextPoint,g);
                    
                    	   for (Point pointset : points) {
                             int c=ccw(pointset,NextPoint,po);
                             if(NextPoint==po ||c==1 ||(c==0 && dist(po,pointset)>dist(po,NextPoint))) {
                            	  AnimateOut(NextPoint,pointset,g); 
                            	  AnimateOut(po,NextPoint,g); 
                            	 NextPoint=pointset;
                            	 AnimateIn(po,NextPoint,g);
                             }
                             AnimateIn(NextPoint,pointset,g); 
                             AnimateOut(NextPoint,pointset,g); 
                        
                           }
                    	   AnimateIn(po,NextPoint,g);
                    	   po=NextPoint;
                           if(po==Hull.get(0)) {
                        	   break;
                           }
        }             
                    	  
                    	  
      //..............................................................
                    	  //.............LETS ANIMATE.........................
                    	  
                    	  for(int i=0;i<Hull.size();i++) {
                         	 if(i==Hull.size()-1) {
                         		 break;
                         	 }
                         	 try {
 								Thread.sleep(500);
 							} catch (InterruptedException e) {
 								// TODO Auto-generated catch block
 								e.printStackTrace();
 							}
            			
                         	 g.drawLine(Hull.get(i).x, Hull.get(i).y,Hull.get(i+1).x,Hull.get(i+1).y);   
                          }
                          g.drawLine(Hull.get(Hull.size()-1).x, Hull.get(Hull.size()-1).y, Hull.get(0).x, Hull.get(0).y);  	  
                    	  
                    	  
                    	  
                    	  
                    	  
                    	  
                    	  
                    	  
                    	  
                    	  
                    	  
                    	  
                    	  
                    	  
                    	  
                    	  
                    	  
                    	  
                    	  
 
//                    	  try {
//        					Thread.sleep(1000);
//        				} catch (InterruptedException e) {
//        					// TODO Auto-generated catch block
//        					e.printStackTrace();
//        				}
//                    		  
                    	
                    	 


        	
        	
        	
        	

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
    
	private int dist(Point po, Point nextPoint) {
		
		return (int) Math.sqrt(Math.pow(nextPoint.y-po.y, 2)+Math.pow(nextPoint.x-po.x, 2));
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
            ConvexHull_JarvisMarch drawLines = new ConvexHull_JarvisMarch();
            drawLines.setVisible(true);
          
        });
    }
}

class Point3<A,P>{
	private A Angle;
    private P P1;
	
	public Point3(A Angle,P P1) {
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
