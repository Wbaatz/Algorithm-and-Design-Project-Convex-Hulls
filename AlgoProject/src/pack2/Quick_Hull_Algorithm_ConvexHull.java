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

public class Quick_Hull_Algorithm_ConvexHull extends JFrame {

    private ArrayList<Point> points;
    private boolean drawing;
    private Timer timer;
    public Quick_Hull_Algorithm_ConvexHull() {
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
			
			g.fillOval(p.x, p.y, 5, 5); 
			
		}
        if (drawing && points.size() > 1) {
        	
        	

      

       ArrayList<Point_AxisPair<Integer,Point>> A2= new ArrayList<>();
       ArrayList<Point> Hull = new ArrayList<>();
        
        
                    	
                    	for(Point pi:points) {
                    		
                    			A2.add(new Point_AxisPair<>(pi.x,pi));	
                    		
                    	
                    	}
                    	
                    	
                    	 Comparator<Point_AxisPair<Integer, Point>> comparator = new Comparator<Point_AxisPair<Integer, Point>>() {
                             @Override
                             public int compare(Point_AxisPair<Integer, Point> ps1, Point_AxisPair<Integer, Point> ps2) {
                               
                                 Integer doubleValue1 = ps1.getx_Axis();
                                 Integer doubleValue2 = ps2.getx_Axis();
                                 
                                
                                 return Integer.compare(doubleValue1, doubleValue2);
                             }
                         };

                  
                         Collections.sort(A2, comparator);
                       
                        
                         ArrayList<Point> Sorted = new ArrayList<>();
                         for (Point_AxisPair<Integer, Point> pointSet : A2) {
                             Sorted.add(pointSet.getPoint());
                         }
                         Print(Sorted);
                         
                         //ALL SET now.lets go
                         
                         Point p1= Sorted.get(0);
                         Point p2= Sorted.get(A2.size()-1);
                         Hull.add(p1);
                         Hull.add(p2);
                         //animate this line
                         AnimateIn(p1,p2,g);
                         
                         
                         Sorted.remove(A2.size()-1);
                         Sorted.remove(0);
                         System.out.println("after deleting");
                         Print(Sorted);
                         SegmentData s1;
                         s1=create_segment(p1,p2,Sorted);
                         System.out.println("Above p1 and p2 line");
                         Print(s1.Above);
                         System.out.println("below p1 and p2 line");
                         Print(s1.Below);
                         System.out.println("below and above added");
                         ArrayList<Point> A1 = new ArrayList<>();
                         A1.addAll(s1.Above);
                         A1.addAll(s1.Below);
                         Print(A1);
                         
                         Hull.addAll(quickHull2(p1,p2,s1.Above,"Above",g));
                         Hull.addAll(quickHull2(p1,p2,s1.Below,"Below",g));
                         System.out.println("final ANswer");
                         Print(Hull);
                         Hull=removeDeuplicate(Hull);
                         System.out.println("duplicates removed");
                         Print(Hull);
                         System.out.println("Count:"+Hull.size());
                       
                         for (Point p : Hull) {
                        	 g.setColor(Color.RED);
                        	
                			g.fillOval(p.x, p.y, 5, 5); 
                			
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

	private void Print(ArrayList<Point> sorted) {
		 for (Point pointSet : sorted) {
             System.out.println("("+pointSet.x+","+pointSet.y+")"); 
         }
		
	}
	public static <T> ArrayList<T> removeDeuplicate(ArrayList<T> list){
		HashSet<T> set = new HashSet<>(list);
		return new ArrayList<>(set);
	}

	private Collection<? extends Point> quickHull2(Point p1, Point p2, ArrayList<Point> SegmentList, String flag,Graphics g) {
		
		ArrayList<Point> Hull2 = new ArrayList<>();
		int farthest_dist=-1;
		int distance=0;
		Point Farthest_point=null;
		if(SegmentList.isEmpty()||p1==null||p2==null) {
			return  Hull2;
		}
		
		for(Point s:SegmentList) {
			distance=find_distance(p1,p2,s);
			if(distance>farthest_dist) {
				farthest_dist=distance;
				Farthest_point=s;
			}
			
		}
	 
		 AnimateIn(p1,Farthest_point,g);
		 AnimateIn(p2,Farthest_point,g);
		
		Hull2.add(Farthest_point);
	
		SegmentList.remove(Farthest_point);
	
		
		ArrayList<Point> temp = new ArrayList<>();
		for(Point s:SegmentList) {
			if(isInside(p1,p2,Farthest_point,s)) {
				System.out.println("its inside");
				System.out.println("("+s.x+","+s.y+")");
				temp.add(s);
			
			}	
			}
		System.out.println("before");
		Print(SegmentList);
		for(Point s:temp) {
			if(isInside(p1,p2,Farthest_point,s)) {
				System.out.println("deleted");
				System.out.println("("+s.x+","+s.y+")");
				SegmentList.remove(s);
			
			}	
			}
	
		
		
		  SegmentData s2=create_segment(p1,Farthest_point,SegmentList);
		 
		  
		  SegmentData s3=create_segment(p2,Farthest_point,SegmentList);
		
			for(Point s:SegmentList) {
			 System.out.println("("+s.x+","+s.y+")");
				
			}
		

		  if(flag=="Above") {
			 
			  Hull2.addAll(quickHull2(p1,Farthest_point,s2.Above,"Above",g));
			  Hull2.addAll(quickHull2(Farthest_point,p2,s3.Above,"Above",g));
		  }
		  else {
			  Hull2.addAll(quickHull2(p1,Farthest_point,s2.Below,"below",g));
			  Hull2.addAll(quickHull2(Farthest_point,p2,s3.Below,"below",g)); 
		  }
		
		return Hull2;
	}

	private int find_distance(Point p1, Point p2, Point s) {
		int a=p1.y-p2.y;
		int b=p2.x-p1.x;
		int c=(p1.x*p2.y)-p2.x*p1.y;
		System.out.println("c is "+c);
		return (int) (Math.abs(a*s.x+b*s.y+c)/Math.sqrt(a*a+b*b));
	}

	private static  SegmentData create_segment(Point p1, Point p2, ArrayList<Point> sorted) {
	       SegmentData s2 = new SegmentData();
	       if((p2.x-p1.x)==0) {
	    	   return s2;
	       }
	       
	       int m=(p2.y-p1.y)/(p2.x-p1.x);
	       int c=-m*p1.x+p1.y;
	       
	       for (Point pointSet : sorted) {
	    	   
               if(pointSet.y>m*pointSet.x+c) {
            	   s2.Above.add(pointSet);
               }
               else if(pointSet.y<m*pointSet.x+c) {
            	   s2.Below.add(pointSet);
               }
           }
	       
	       
		return s2;
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
		    
		    public static float triangleArea(Point p1,Point p2,Point p3) {
				return (float) Math.abs((p1.x*(p2.y-p3.y)+p2.x*(p3.y-p1.y)+p3.x*(p1.y-p2.y))/2.0);
		    	
		    }
		    
		    public static boolean isInside(Point p1,Point p2,Point p3, Point p) {
		    	float A=triangleArea(p1,p2,p3);
		    	float A1=triangleArea(p,p2,p3);
		    	float A2=triangleArea(p1,p,p3);
		    	float A3=triangleArea(p1,p2,p);
		    	
		    	return (A==A1+A2+A3);
		    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Quick_Hull_Algorithm_ConvexHull drawLines = new Quick_Hull_Algorithm_ConvexHull();
            drawLines.setVisible(true);
          
        });
    }
}

class Point_AxisPair<A,P>{
	private A x_Axis;
    private P P1;
	
	public Point_AxisPair(A x_Axis,P P1) {
		this.P1=P1;
		this.x_Axis=x_Axis;
		
	}
	public A getx_Axis() {
		return this.x_Axis;
	}
	public P getPoint() {
		return this.P1;
	}
	
	
	
}

class SegmentData{
	  ArrayList<Point> Above ;
	  ArrayList<Point> Below ;
	  
	public  SegmentData(){
		Above = new ArrayList<>();
		Below = new ArrayList<>();
	  }
	  
	  
	  
	  
	  
}

