package main;

import java.awt.*;
import javax.swing.*;

public class DrawWindow extends JFrame {
	 public DrawWindow() {
	        super("Number clasification");
	        getContentPane().setBackground(Color.WHITE);
	        setSize(500,500);
	        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        setLocationRelativeTo(null);
	    }
	 
	 
	    public void paint(Graphics g) {
	        super.paint(g);
	    }
}
