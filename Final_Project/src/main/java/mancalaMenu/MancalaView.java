package mancalaMenu;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class MancalaView extends JFrame implements ChangeListener{
	public MancalaView() {
		getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
	}

	private JButton[] pits;
	
	private JLabel[] stones;
	
	
	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO Auto-generated method stub
		
	}
}
