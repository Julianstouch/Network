package ibicf;
import java.awt.Panel;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

public class Runner extends JFrame implements WindowListener
{
  private static final long serialVersionUID = 1L;
  public int DEFAULT_FPS = 10;
  public static Runner instance;
  public ThePanel MonPanneau;
  public Panel bpSol;
  private Thread maCervelleCreateur;
  private Thread maCervelleMvts;
  
  public static Runner getInstance()
  {
	  return instance;
  }
  
  public ThePanel getPanel()
  {
	  return MonPanneau;
  }
  
  public Runner()
  { 
	super("IBICF v 1.00");
	
    Walkman lesListeneurs = new Walkman();
    
    this.setSize(1280, 1014);
    MonPanneau = new ThePanel(DEFAULT_FPS,1273,980,1);
    MonPanneau.addKeyListener(lesListeneurs.getKL());
    MonPanneau.setVisible(true);
    this.getLayeredPane().add(MonPanneau,JLayeredPane.MODAL_LAYER); 
   
    setVisible(true);
    addWindowListener( this );
    setDefaultCloseOperation(3);
    setResizable(false);
 
    maCervelleCreateur = new Thread(new CervelleCreateur());
   
    maCervelleMvts = new Thread(new CervelleMoveur());
    }
  
  public void startingBlock()
  {
	  maCervelleCreateur.start();
	  maCervelleMvts.start();
  }
  
  public void windowActivated(WindowEvent e) 
  { //MonPanneau.resumeGame();  
  }

  public void windowDeactivated(WindowEvent e) 
  { ThePanel.pauseGame();  
  }

  public void windowDeiconified(WindowEvent e) 
  {  //MonPanneau.resumeGame();  
	  
  }

  public void windowIconified(WindowEvent e) 
  {  
	  ThePanel.pauseGame(); 	  
  }

  public void windowClosing(WindowEvent e)
  {  ThePanel.stopGame();  
  }
  

  public void windowClosed(WindowEvent e) {}
  public void windowOpened(WindowEvent e) {}
  
  public static void main(String args[])
  { 
	  instance = new Runner();
  }


}


