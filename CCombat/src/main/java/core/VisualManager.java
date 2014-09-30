package core;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fixed.EnumEnvoyer;
import fixed.EnumPhaseJeu;
import graph.ImageDistributor;
import connect.CombatClientJNet;
import connect.CombatEnvoyer;
import connect.CombatRecevoir;

/**
 * 
 * @author jfeniou
 * 
 */
public class VisualManager
{

    private static VisualManager instance;

    public JButton btnNextRound; //btnGoFire,
    public JLabel labNumJoueur, labNomJoueur; // gameOver,
    public CombatClientJNet clientJNet;
    public String pseudoLui;

    public CombatManager cbtManag;

    private CombatEnvoyer envoyeur;
    private JFrame appZone;

    public static VisualManager getInstance()
    {
        return instance;
    }

    public VisualManager()
    {
        instance = this;
    }

    public void letsStart()
    {
        String pseudoMoi = JOptionPane.showInputDialog(null, "Votre pseudo ?", "Qui etes vous ?", JOptionPane.QUESTION_MESSAGE);

        if (pseudoMoi == null || "".equals(pseudoMoi))
        {
            pseudoMoi = "JohnDoe" + (new Random().nextInt(998) + 1);
        }

        String addrServeur = JOptionPane.showInputDialog(null, "Adresse du serveur", "Par defaut : 172.27.7.41", JOptionPane.QUESTION_MESSAGE);
        if (addrServeur == null || "".equals(addrServeur))
        {
            addrServeur = "172.27.7.41";
        }

        // switch on hardware acceleration if using OpenGL with pbuffers
        // System.setProperty("sun.java2d.opengl", "true");
        JFrame app = new JFrame("Zone Combat !");
        appZone = new JFrame("Zone Calcul !");

        Container contentPane = app.getContentPane();
        contentPane.setLayout(new BorderLayout());

        app.setPreferredSize(new Dimension(1024, 631));
        app.setVisible(true);

        JPanel panelIntro = new JPanel()
        {
            protected void paintComponent(Graphics g)
            {
                g.drawImage(ImageDistributor.getInstance().getIntro(), 0, 0, null);
            }
        };

        panelIntro.setLocation(0, 0);
        panelIntro.setSize(1024, 625);

        app.getLayeredPane().add(panelIntro, JLayeredPane.MODAL_LAYER);
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.validate();
        app.pack();
        app.setResizable(false);

        clientJNet = new CombatClientJNet(addrServeur, pseudoMoi);
        clientJNet.lancerClient();
        // leclient.numerojoueur;
        int numerojoueur = clientJNet.getItsMe().getNumero();

        cbtManag = new CombatManager(numerojoueur);
        ZoneCombat ttPanel = new ZoneCombat();
        try
        {
            CombatRecevoir lerecevoir = new CombatRecevoir(clientJNet.getTheGameInfo(), clientJNet.getOpponent());
            lerecevoir.start();

            try
            {
                Thread.sleep(2000);
            }
            catch (InterruptedException e1)
            {
                thereIsAnError("Visual sleep", true);
                e1.printStackTrace();
            }

            panelIntro.setVisible(false);
            panelIntro.setEnabled(false);
            
            ZoneCalcul theZone = new ZoneCalcul();
            ZonePortait lepaneau = new ZonePortait();

            //btnGoFire = new JButton();
            btnNextRound = new JButton();
            JButton btnZoneDebug = new JButton();
            
            
            labNomJoueur = new JLabel(pseudoMoi + ",");
            labNumJoueur = new JLabel("Vous etes joueur " + numerojoueur + ".");

            envoyeur.envoyer(EnumEnvoyer.pseudo, pseudoMoi);

            while (pseudoLui == null)
            {
            }

            if (numerojoueur == 1)
                app.setTitle("Combat of the day : " + pseudoMoi + " VS " + pseudoLui);
            else
                app.setTitle("Combat of the day : " + pseudoLui + " VS " + pseudoMoi);

            app.setPreferredSize(new Dimension(1006, 950));
            app.setVisible(true);
            app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            app.validate();
            app.pack();
            app.setResizable(false);
            app.doLayout();

            appZone.setPreferredSize(new Dimension(1006, 800));
            appZone.setVisible(false);
            // appZone.setUndecorated(true);
            // appZone.setDefaultCloseOperation();
            appZone.validate();
            appZone.pack();
            appZone.setResizable(true);
            appZone.doLayout();

            ttPanel.setSize(1000, 700);
            ttPanel.setLocation(0, 0);
            theZone.setSize(1000, 700);
            theZone.setLocation(0, 0);
            lepaneau.setSize(780, 218);
            lepaneau.setLocation(0, 700);

            ttPanel.setBackground(Color.BLUE);
            lepaneau.setBackground(Color.WHITE);
            theZone.setBackground(Color.BLACK);
            theZone.setVisible(true);

            app.getLayeredPane().add(lepaneau, JLayeredPane.MODAL_LAYER);
            app.getLayeredPane().add(ttPanel, JLayeredPane.MODAL_LAYER);
            appZone.getLayeredPane().add(theZone, JLayeredPane.MODAL_LAYER);

            CombatListeneur leListen = new CombatListeneur();
            // go.addActionListener(leListen.getAL());
            // app.addKeyListener(leListen.getKeyL());
            ttPanel.addMouseListener(leListen.getMouseListeneur());
            ttPanel.addMouseMotionListener(leListen.getMouseML());

            labNomJoueur.setLocation(781, 700);
            labNomJoueur.setSize(219, 50);
            labNomJoueur.setFont(new Font("Arial Black", Font.PLAIN, 20));
            labNomJoueur.setVisible(true);
            app.getLayeredPane().add(labNomJoueur, JLayeredPane.MODAL_LAYER);

            labNumJoueur.setLocation(781, 725);
            labNumJoueur.setSize(219, 50);
            labNumJoueur.setFont(new Font("Arial Black", Font.PLAIN, 20));
            labNumJoueur.setVisible(true);
            app.getLayeredPane().add(labNumJoueur, JLayeredPane.MODAL_LAYER);

//            if (numerojoueur == 1)
//            {
//                btnGoFire.setText("FIRE !");
//            }
//            else
//                btnGoFire.setText("READY !");
//
//            btnGoFire.addActionListener(leListen.getBtnReadyFireL());
//            btnGoFire.setLocation(855, 780);
//            btnGoFire.setSize(80, 20);
//            btnGoFire.setEnabled(false);
//            app.getLayeredPane().add(btnGoFire, JLayeredPane.MODAL_LAYER);

            btnZoneDebug.setText("Zone Debug");
            btnZoneDebug.addActionListener(leListen.getBtnDebugZoneL());
            btnZoneDebug.setLocation(800, 880);
            btnZoneDebug.setSize(130, 20);
            btnZoneDebug.setEnabled(true);
            app.getLayeredPane().add(btnZoneDebug, JLayeredPane.MODAL_LAYER);

            btnNextRound.setText("Next");
            btnNextRound.addActionListener(leListen.getBtnNextRoundL());            
            btnNextRound.setLocation(855, 820);
            btnNextRound.setSize(80, 20);
            btnNextRound.setEnabled(false);
            app.getLayeredPane().add(btnNextRound, JLayeredPane.MODAL_LAYER);
            
            // gameOver.setLocation(300,300);
            // gameOver.setSize(600,100);
            // gameOver.setFont(new Font("Arial Black",Font.PLAIN,68));
            // gameOver.setVisible(false);
            // app.getLayeredPane().add(gameOver,JLayeredPane.POPUP_LAYER);
            cbtManag.demarrer();
        }
        catch (Exception e)
        {
            thereIsAnError("Impossible de contacter l'autre joueur", true);
        }

    }

//    public void setEnablegoFire(boolean alors)
//    {
//        btnGoFire.setEnabled(alors);
//    }

    // public static void setGameOver(boolean b)
    // {
    // gameOver.setVisible(true);
    // }

    public void thereIsAnError(String message, Boolean stop)
    {
        if (stop)
        {
            if (cbtManag != null && !EnumPhaseJeu.phasesFinies.contains(cbtManag.phaseJeu))
                cbtManag.phaseJeu = EnumPhaseJeu.phaseErreur;
            JOptionPane.showMessageDialog(null, message + " \n JTrisCombat s'est arrete.");
        }
        else
            JOptionPane.showMessageDialog(null, message);
    }

    /**
     * 
     * @param lautrePseudo
     */
    public void majAutrePseudo(String lautrePseudo)
    {
        pseudoLui = lautrePseudo;
    }

    /**
     * 
     * @param adresseautrejoueur
     * @param i
     * @throws Exception
     */
    public void createEnvoyeur(String adresseautrejoueur, int i) throws Exception
    {
        envoyeur = new CombatEnvoyer(adresseautrejoueur, i);
    }

    /**
     * 
     */
    public void clickDebug()
    {
        appZone.setVisible(!appZone.isVisible());
    }

    /**
     * @return the envoyeur
     */
    public CombatEnvoyer getEnvoyeur()
    {
        return envoyeur;
    }
}
