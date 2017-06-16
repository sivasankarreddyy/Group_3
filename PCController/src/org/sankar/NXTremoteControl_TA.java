//Developed by Sankar for the project of Telepresence Robot - Ubiquitous Computing Project - Summer 2017



package org.sankar;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

import lejos.pc.comm.NXTConnector;

public class NXTremoteControl_TA extends JFrame
{
  public static JButton quit, connect;
  public static JButton forward,reverse, leftTurn, rightTurn, stop, speedUp, slowDown;
  public static JLabel L1,L2,L3,L4,L5,L6,L7,L8,L9,L10;
  public static ButtonHandler bh = new ButtonHandler();
  public static DataOutputStream outData;
  public static NXTConnector link;
  
  public NXTremoteControl_TA()
  { 
    setTitle ("Control");
    setBounds(650,350,500,500);
    setLayout(new GridLayout(4,5));
   
    L1 = new JLabel("");
    add(L1); 
    forward = new JButton("Forward");
    forward.addActionListener(bh);
    forward.addMouseListener(bh);
    forward.addKeyListener(bh);
    add(forward);
    L2 = new JLabel("");
    add(L2);
    L3 = new JLabel("");
    add(L3);
    speedUp = new JButton("Accelerate");
    speedUp.addActionListener(bh);
    speedUp.addMouseListener(bh);
    speedUp.addKeyListener(bh);
    add(speedUp);

    leftTurn = new JButton("Left");
    leftTurn.addActionListener(bh);
    leftTurn.addMouseListener(bh);
    leftTurn.addKeyListener(bh);
    add(leftTurn);
    stop = new JButton("Stop");
    stop.addActionListener(bh);
    stop.addMouseListener(bh);
    stop.addKeyListener(bh);
    add(stop);
    
    rightTurn = new JButton("Right");
    rightTurn.addActionListener(bh);
    rightTurn.addMouseListener(bh);
    rightTurn.addKeyListener(bh);
    add(rightTurn);
    L4 = new JLabel("");
    add(L4);
    slowDown = new JButton("Decelerate");
    slowDown.addActionListener(bh);
    slowDown.addMouseListener(bh);
    slowDown.addKeyListener(bh);
    add(slowDown);
    
    L5 = new JLabel("");
    add(L5);
    reverse = new JButton("Reverse");
    reverse.addActionListener(bh);
    reverse.addMouseListener(bh);
    reverse.addKeyListener(bh);
    add(reverse);
    
    L6 = new JLabel("");
    add(L6);
    L7 = new JLabel("");
    add(L7);
    L8 = new JLabel("");
    add(L8);

    connect = new JButton(" Connect ");
    connect.addActionListener(bh);
    connect.addKeyListener(bh);
    add(connect);

    L9 = new JLabel("");
    add(L9);
    L10 = new JLabel("");
    add(L10);
    
    quit = new JButton("Quit");
    quit.addActionListener(bh);
    add(quit);

  }
  
  public static void main(String[] args)
  {
     NXTremoteControl_TA NXTrc = new NXTremoteControl_TA();
     NXTrc.setVisible(true);
     NXTrc.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
  }
  
  private static class ButtonHandler implements ActionListener, MouseListener, KeyListener
  {
    public void actionPerformed(ActionEvent ae)
    {
      if(ae.getSource() == quit)  {System.exit(0);}
      if(ae.getSource() == connect) {try {
		connect();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}}
      
      try{
         if(ae.getSource() == speedUp) {outData.writeInt(6);}
         if(ae.getSource() == slowDown) {outData.writeInt(7);}
         outData.flush(); 
        } 
         catch (IOException ioe) {
        System.out.println("\nIO Exception writeInt");
         }
      
     }
    public void mouseClicked(MouseEvent arg0) {}

   public void mouseEntered(MouseEvent arg0) {}

   public void mouseExited(MouseEvent arg0) {}

   public void mousePressed(MouseEvent moe) 
   {   
         try {
            if(moe.getSource() == forward)outData.writeInt(1);
            if(moe.getSource() == reverse)outData.writeInt(2);
            if(moe.getSource() == leftTurn)outData.writeInt(3);
            if(moe.getSource() == rightTurn)outData.writeInt(4);
            if(moe.getSource() == speedUp)outData.writeInt(6);
            if(moe.getSource() == slowDown)outData.writeInt(7);
         
            outData.flush(); 
            }
         catch (IOException ioe) {
            System.out.println("\nIO Exception writeInt");
         }
        
   }

   public void mouseReleased(MouseEvent moe) 
   {
       try {
         if(moe.getSource() == forward ||
            moe.getSource() == reverse ||
            moe.getSource() == leftTurn ||
            moe.getSource() == rightTurn)
           {outData.writeInt(5);}
         if(moe.getSource() == slowDown)outData.writeInt(60);
         if(moe.getSource() == speedUp)outData.writeInt(70);
          
         outData.flush(); 
          } 
        catch (IOException ioe) {
           System.out.println("\nIO Exception writeInt");
        }
      
   }
   public void keyPressed(KeyEvent ke) {}

   public void keyTyped(KeyEvent ke) 
   {
      try {
         if(ke.getKeyChar() == 'w')outData.writeInt(1);
          if(ke.getKeyChar() == 's')outData.writeInt(2);
         if(ke.getKeyChar() == 'a')outData.writeInt(3);
         if(ke.getKeyChar() == 'd')outData.writeInt(4);
          if(ke.getKeyChar() == 'i')outData.writeInt(6);
          if(ke.getKeyChar() == 'k')outData.writeInt(7);
         
         outData.flush(); 
         }
      catch (IOException ioe) {
         System.out.println("\nIO Exception writeInt");
         }
        
   }
   
   public void keyReleased(KeyEvent ke) 
   {
       try {
          if(ke.getKeyChar() == 'w'){outData.writeInt(10);}
          if(ke.getKeyChar() == 's'){outData.writeInt(20);}
          if(ke.getKeyChar() == 'a'){outData.writeInt(30);} 
          if(ke.getKeyChar() == 'd'){outData.writeInt(40);}
          if(ke.getKeyChar() == 'i'){outData.writeInt(60);}
          if(ke.getKeyChar() == 'k'){outData.writeInt(70);}
         if(ke.getKeyChar() == 'q'){System.exit(0);}
          
          outData.flush();
          } 
         
        catch (IOException ioe) {
           System.out.println("\nIO Exception writeInt");
        }
   }

  }
  
  public static void connect() throws IOException
  {
     link = new NXTConnector();
    
     //if (!link.connectTo("usb://"))
     if (!link.connectTo("btspp://0016530AA682"))
     {
        System.out.println("\nNo NXT found using Bluetooth");
        }
     outData = new DataOutputStream(link.getOutputStream());
     System.out.println(outData);
     System.out.println("\nNXT is Connected");   
     
  }
  
  public static void disconnect()
  {
     try{
        outData.close();
        link.close();
        } 
     catch (IOException ioe) {
        System.out.println("\nIO Exception writing bytes");
     }
     System.out.println("\nClosed data streams");
     
  }
}
