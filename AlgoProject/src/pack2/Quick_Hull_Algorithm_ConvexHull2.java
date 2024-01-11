package pack2;

import javax.crypto.spec.GCMParameterSpec;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Quick_Hull_Algorithm_ConvexHull2 extends JFrame {

    private ArrayList<Point> points;
    private boolean drawing;
    private Timer timer;
    public Quick_Hull_Algorithm_ConvexHull2() {
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

    public class QuickHull_Go
    {
        public ArrayList<Point> quickHull(ArrayList<Point> points,Graphics g)
        {
            ArrayList<Point> convexHull = new ArrayList<Point>();
            if (points.size() < 3)
                return (ArrayList) points.clone();
     
            int minPoint = -1, maxPoint = -1;
            int minX = Integer.MAX_VALUE;
            int maxX = Integer.MIN_VALUE;
            for (int i = 0; i < points.size(); i++)
            {
                if (points.get(i).x < minX)
                {
                    minX = points.get(i).x;
                    minPoint = i;
                }
                if (points.get(i).x > maxX)
                {
                    maxX = points.get(i).x;
                    maxPoint = i;
                }
            }
            Point A = points.get(minPoint);
            Point B = points.get(maxPoint);
            convexHull.add(A);
            convexHull.add(B);
            points.remove(A);
            points.remove(B);
            AnimateIn(A,B,g);
            ArrayList<Point> leftSet = new ArrayList<Point>();
            ArrayList<Point> rightSet = new ArrayList<Point>();
     
            for (int i = 0; i < points.size(); i++)
            {
                Point p = points.get(i);
                if (pointLocation(A, B, p) == -1)
                    leftSet.add(p);
                else if (pointLocation(A, B, p) == 1)
                    rightSet.add(p);
            }
            hullSet(A, B, rightSet, convexHull,g);
            hullSet(B, A, leftSet, convexHull,g);
     
            return convexHull;
        }
     
        public int distance(Point A, Point B, Point C)
        {
            int ABx = B.x - A.x;
            int ABy = B.y - A.y;
            int num = ABx * (A.y - C.y) - ABy * (A.x - C.x);
            if (num < 0)
                num = -num;
            return num;
        }
     
        public void hullSet(Point A, Point B, ArrayList<Point> set,
                ArrayList<Point> hull,Graphics g)
        {
            int insertPosition = hull.indexOf(B);
            if (set.size() == 0)
                return;
            if (set.size() == 1)
            {
                Point p = set.get(0);
                set.remove(p);
                AnimateIn(A,p,g);
                AnimateIn(p,B,g);
                hull.add(insertPosition, p);
                return;
            }
            int dist = Integer.MIN_VALUE;
            int furthestPoint = -1;
            for (int i = 0; i < set.size(); i++)
            {
                Point p = set.get(i);
                int distance = distance(A, B, p);
                if (distance > dist)
                {
                    dist = distance;
                    furthestPoint = i;
                }
            }
            Point P = set.get(furthestPoint);
            AnimateIn(A,P,g);
            AnimateIn(P,B,g);
            set.remove(furthestPoint);
            hull.add(insertPosition, P);
     
            // Determine who's to the left of AP
            ArrayList<Point> leftSetAP = new ArrayList<Point>();
            for (int i = 0; i < set.size(); i++)
            {
                Point M = set.get(i);
                if (pointLocation(A, P, M) == 1)
                {
                    leftSetAP.add(M);
                }
            }
     
            // Determine who's to the left of PB
            ArrayList<Point> leftSetPB = new ArrayList<Point>();
            for (int i = 0; i < set.size(); i++)
            {
                Point M = set.get(i);
                if (pointLocation(P, B, M) == 1)
                {
                    leftSetPB.add(M);
                }
            }
            hullSet(A, P, leftSetAP, hull,g);
            hullSet(P, B, leftSetPB, hull,g);
     
        }
     
        public int pointLocation(Point A, Point B, Point P)
        {
            int cp1 = (B.x - A.x) * (P.y - A.y) - (B.y - A.y) * (P.x - A.x);
            if (cp1 > 0)
                return 1;
            else if (cp1 == 0)
                return 0;
            else
                return -1;
        }
     

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
			
			g.fillOval(p.x, p.y, 5, 5); 
			
		}
        if (drawing && points.size() > 1) {
        	
        	

      

       ArrayList<Point_AxisPair<Integer,Point>> A2= new ArrayList<>();
       ArrayList<Point> Hull = new ArrayList<>();
        
        
                    	
                    	for(Point pi:points) {
                    		
                    			A2.add(new Point_AxisPair<>(pi.x,pi));	
                    		
                    	
                    	}
                    	  QuickHull_Go qh = new QuickHull_Go();
                        ArrayList<Point> p = qh.quickHull(points,g);
                        
                        for (Point p1 : p) {
                       	 g.setColor(Color.RED);
                       	
               			g.fillOval(p1.x, p1.y, 5, 5); 
               			
               		}
                      
                       
                         
//                         for(int i=0;i<Hull.size();i++) {
//                         	 if(i==Hull.size()-1) {
//                         		 break;
//                         	 }
//                        
//                         	 
//                         	AnimateIn(Hull.get(i),Hull.get(i+1),g);
//                         	 
//                          }
                         
                         

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
		    
		 
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Quick_Hull_Algorithm_ConvexHull2 drawLines = new Quick_Hull_Algorithm_ConvexHull2();
            drawLines.setVisible(true);
          
        });
    }
}

	
	



	  
	  
	  
	  


