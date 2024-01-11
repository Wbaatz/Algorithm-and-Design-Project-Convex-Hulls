package pack2;

import javax.crypto.spec.GCMParameterSpec;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Convex_hullMonotone_chain extends JFrame {

    private ArrayList<Point> points;
    private boolean drawing;
    private Timer timer;
    public Convex_hullMonotone_chain() {
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

  
  
   
    //////////......................................
    
    public class ConvexHull {
//    	 ArrayList<Points3<Point>> A1= new ArrayList<>();
//    
        // Cross product of two vectors OA and OB
        // returns positive for counter clockwise
        // turn and negative for clockwise turn
        static long crossProduct(Point O, Point A, Point B)
        {
            return (A.x - O.x) * (B.y - O.y)
                - (A.y - O.y) * (B.x - O.x);
        }
        // Returns a list of points on the convex hull
        // in counter-clockwise order
        static ArrayList<Point> convexHull(ArrayList<Point> A,Graphics g)
        {
            int n = A.size(), k = 0;
     
            if (n <= 3)
                return A;
     
            ArrayList<Point> ans = new ArrayList<>();
     
            // Sort points lexicographically
            
            
            Comparator <Point>  comparator = new Comparator <Point>() {

				@Override
				public int compare(Point o1, Point o2) {
					 return Long.compare(o1.x, o2.x) != 0
			                ? Long.compare(o1.x, o2.x)
			                : Long.compare(o1.y, o2.y);
				}

	
            };
            System.out.println("before sorting");
           Print(A);
          
           
            Collections.sort(A, comparator);
            System.out.println("After sorting");
            Print(A);
          
         
            // Build lower hull
            for (int i = 0; i < n; ++i) {
                // If the point at K-1 position is not a part
                // of hull as vector from ans[k-2] to ans[k-1]
                // and ans[k-2] to A[i] has a clockwise turn
                while (k >= 2&& crossProduct(ans.get(k - 2),ans.get(k - 1), A.get(i))<= 0)
                {
                    AnimateIn(A.get(i),ans.get(k - 1),g);
             AnimateOut(ans.get(k - 1),A.get(i),g);
     		 AnimateOut(ans.get(k - 2),ans.get(k - 1),g);
                    ans.remove(--k);
                }
                ans.add(A.get(i));
               
                k++;
            }
     
            for(int i=0;i<ans.size();i++) {
            	 if(i==ans.size()-1) {
            		 break;
            	 }
           
            	 
            	AnimateIn(ans.get(i),ans.get(i+1),g);
            	 
             }
            
            // Build upper hull
            for (int i = n - 2, t = k; i >= 0; --i) {
     
                // If the point at K-1 position is not a part
                // of hull as vector from ans[k-2] to ans[k-1]
                // and ans[k-2] to A[i] has a clockwise turn
                while (k > t
                       && crossProduct(ans.get(k - 2),
                                       ans.get(k - 1), A.get(i))
                              <= 0)
                {
                	  AnimateIn(ans.get(k - 1),A.get(i),g);
                AnimateOut(ans.get(k - 1),A.get(i),g);
       		 AnimateOut(ans.get(k - 2),ans.get(k - 1),g);
                    ans.remove(--k);
                }
                ans.add(A.get(i));
               
                k++;
            }
     
            // Resize the array to desired size
            ans.remove(ans.size() - 1);
     

            for(int i=0;i<ans.size();i++) {
            	 if(i==ans.size()-1) {
            		 break;
            	 }
           
            	 
            	AnimateIn(ans.get(i),ans.get(i+1),g);
            	 
             }
            
            return ans;
        }
    }
    ///////.......
    
    
    
    
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
                    	
//                    	Point[] hull = convex_hull(p).clone();
//                		
//                		for (int i = 0; i < hull.length; i++) {
//                			if (hull[i] != null)
//                				System.out.print(hull[i]);
//                		}
                    	ConvexHull ch = new ConvexHull();
                    	
                    	 ArrayList<Point> p=ch.convexHull(points,g);
                    	
                		

                        
                        for (Point p1 : p) {
                       	 g.setColor(Color.RED);
                       	
               			g.fillOval(p1.x, p1.y, 5, 5); 
               			
               		}
                      
                       
                         
//                         for(int i=0;i<p.size();i++) {
//                         	 if(i==p.size()-1) {
//                         		 break;
//                         	 }
//                        
//                         	 
//                         	AnimateIn(p.get(i),p.get(i+1),g);
//                         	 
//                          }
                   	 g.setColor(Color.blue);
                         AnimateIn(p.get(p.size()-1),p.get(0),g);
                         

        }
    }

	




	
    private static void Print(ArrayList<Point> sorted) {
		 for (Point pointSet : sorted) {
            System.out.println("("+pointSet.x+","+pointSet.y+")"); 
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
            Convex_hullMonotone_chain drawLines = new Convex_hullMonotone_chain();
            drawLines.setVisible(true);
          
        });
    }
}

	
	



	  
	  
	  
	  


