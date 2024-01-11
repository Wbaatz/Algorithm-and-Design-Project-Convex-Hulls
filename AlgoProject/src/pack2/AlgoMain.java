package pack2;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import pack1.Intersection;
import pack1.Intersection_through_endpoints;
import pack1.RayIntersection;

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import java.awt.Font;

public class AlgoMain extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AlgoMain frame = new AlgoMain();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AlgoMain() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		SpringLayout sl_contentPane = new SpringLayout();
		contentPane.setLayout(sl_contentPane);
		
		JButton btnNewButton = new JButton("BruteForce");
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton, 31, SpringLayout.WEST, contentPane);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				 SwingUtilities.invokeLater(() -> {
			            ConvexHull_BruteForce drawLines = new ConvexHull_BruteForce();
			            drawLines.setVisible(true);
			          
			        });
			}
		});
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("Convex Hulls Demos");
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton, 6, SpringLayout.SOUTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblNewLabel, 10, SpringLayout.NORTH, contentPane);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblNewLabel, 10, SpringLayout.WEST, contentPane);
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("MontoneChain");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  SwingUtilities.invokeLater(() -> {
			            Convex_hullMonotone_chain drawLines = new Convex_hullMonotone_chain();
			            drawLines.setVisible(true);
			          
			        });
			}
		});
		sl_contentPane.putConstraint(SpringLayout.EAST, btnNewButton_1, 0, SpringLayout.EAST, btnNewButton);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("JarvisMarch");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				  SwingUtilities.invokeLater(() -> {
			            ConvexHull_JarvisMarch drawLines = new ConvexHull_JarvisMarch();
			            drawLines.setVisible(true);
			          
			        });
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton_2, 14, SpringLayout.SOUTH, btnNewButton);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnNewButton_2, 0, SpringLayout.EAST, btnNewButton);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("GrahamScan");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				   SwingUtilities.invokeLater(() -> {
			            GrahamScan_ConvexHull2 drawLines = new GrahamScan_ConvexHull2();
			            drawLines.setVisible(true);
			          
			        });
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton_3, 19, SpringLayout.SOUTH, btnNewButton_2);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnNewButton_3, 0, SpringLayout.EAST, btnNewButton);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("QuickHull");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 SwingUtilities.invokeLater(() -> {
			            Quick_Hull_Algorithm_ConvexHull2 drawLines = new Quick_Hull_Algorithm_ConvexHull2();
			            drawLines.setVisible(true);
			          
			        });
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton_1, 19, SpringLayout.SOUTH, btnNewButton_4);
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton_4, 18, SpringLayout.SOUTH, btnNewButton_3);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton_4, 0, SpringLayout.WEST, btnNewButton);
		contentPane.add(btnNewButton_4);
		
		JLabel lblIntersections = new JLabel("Intersections");
		sl_contentPane.putConstraint(SpringLayout.NORTH, lblIntersections, 0, SpringLayout.NORTH, lblNewLabel);
		sl_contentPane.putConstraint(SpringLayout.WEST, lblIntersections, 66, SpringLayout.EAST, lblNewLabel);
		lblIntersections.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 14));
		contentPane.add(lblIntersections);
		
		JButton btnNewButton_5 = new JButton("InterectLines");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Intersection I = new Intersection();
				I.main(null);
				
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton_5, 0, SpringLayout.NORTH, btnNewButton);
		sl_contentPane.putConstraint(SpringLayout.WEST, btnNewButton_5, 0, SpringLayout.WEST, lblIntersections);
		contentPane.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("RayIntersection");
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RayIntersection R = new RayIntersection();
				R.main(null);
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton_6, 0, SpringLayout.NORTH, btnNewButton_2);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnNewButton_6, 0, SpringLayout.EAST, btnNewButton_5);
		contentPane.add(btnNewButton_6);
		
		JButton btnNewButton_7 = new JButton("Intersection_Through_Endpoints");
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Intersection_through_endpoints E= new Intersection_through_endpoints();
				E.main(null);
			}
		});
		sl_contentPane.putConstraint(SpringLayout.NORTH, btnNewButton_7, 0, SpringLayout.NORTH, btnNewButton_3);
		sl_contentPane.putConstraint(SpringLayout.EAST, btnNewButton_7, -61, SpringLayout.EAST, contentPane);
		contentPane.add(btnNewButton_7);
	}
}
