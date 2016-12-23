package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.swing.border.EtchedBorder;

/*==================================================================
 * Author: Erin Avllazagaj AKA "Albocoder"
 * Website: http://erin.avllazagaj.ug.bilkent.edu.tr
 * Date: Dec/07/2016
 * Version: 1.1.0
 *==================================================================
 * Referrer: https://github.com/Albocoder/CS319-Group22
 *==================================================================
 * Description:
 * This class is the one responsible for creating new lobbies with a 
 * name and a story and then when created automatically add the 
 * creator to the lobby or just goes back to home page if not created
 * */
import mainPackage.*;

public class LobbyCreatorView extends JFrame implements Viewable{
    private JScrollPane stories;
    private JButton creator;
    private Story[] availableStories;
    private ViewManager referrer;
    
    //added new stuff
    private JTextField lobbyName;
    private JLabel namePrompt;
    private ArrayList<JPanel> storyList;
    private JPanel allStories;
    
    //constants
    private final int COLOR_COLD_RAND = 155;
    
    public LobbyCreatorView(){
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception ex) {System.out.println("Error: "+ex.getMessage());}
        setTitle("Your Story - Lobby Creator");
        JPanel upperPanel = new JPanel(new BorderLayout());
        namePrompt = new JLabel("Lobby name: ");
        String name;
        if (HomePage.getPlayer().getProfile().getName().length()>13)
            name = HomePage.getPlayer().getProfile().getName().substring(0, 10)+"...";
        else 
            name = HomePage.getPlayer().getProfile().getName();
        lobbyName = new JTextField(name+"'s Lobby");
        upperPanel.add(namePrompt,BorderLayout.WEST);
        upperPanel.add(lobbyName,BorderLayout.CENTER);
        // initializing the scrolling list of stories
        allStories = new JPanel();
        storyList = new ArrayList<>();
        stories = new JScrollPane();
        showStories();
        /////////////////////////////////////////////
        
    }
    
    private void showStories(){
        storyList.removeAll(storyList);
        allStories.removeAll();
        Random r = new Random();
        
        allStories.setLayout(new GridLayout(/*someWHERE.getStories().size()*/10,1));
        for(int i = 0; i < 10; i++/*Seat s:someWHERE.getStories()*/){
            //if(!s.getIsOccupied())
            //    continue;
            JPanel emptyPanel = new JPanel();
            Color c = new Color(r.nextInt(COLOR_COLD_RAND),r.nextInt(COLOR_COLD_RAND),r.nextInt(COLOR_COLD_RAND));
            emptyPanel.setBackground(c);
            JPanel tmpLobby = new JPanel(new BorderLayout());
            tmpLobby.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
            tmpLobby.setBackground(c);
            //this sets up a padding from the border to the components
            tmpLobby.add(emptyPanel, BorderLayout.NORTH);
            tmpLobby.add(emptyPanel, BorderLayout.SOUTH);
            tmpLobby.add(emptyPanel, BorderLayout.EAST);
            tmpLobby.add(emptyPanel, BorderLayout.WEST);

            //here add the specifics for each lobby
            JPanel toFill = new JPanel(new BorderLayout());
            toFill.setBackground(c);

            //adding the icon to the left
            JLabel icon = new JLabel();
            
            try {
                BufferedImage playerImg = ImageIO.read(new File("./img/castleBlack.jpg"))/*s.getImage()*/;
                Image dimg = playerImg.getScaledInstance(72, 72,Image.SCALE_SMOOTH);
                ImageIcon imageIcon = new ImageIcon(dimg);
                icon.setIcon(imageIcon);
                icon.setBorder(BorderFactory.createLineBorder(Color.BLACK, 4));
            } catch (Exception e) {
                    e.printStackTrace();
            }
            toFill.add(icon,BorderLayout.WEST);

            JLabel plName = new JLabel("Timeline:"/*s.getTimeline()*/);
            try {
                Font customFont = Font.createFont(Font.TRUETYPE_FONT, new File("./fonts/HeaderFont.ttf")).deriveFont(25f);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("./fonts/HeaderFont.ttf")));
                plName.setFont(customFont);
            } catch (Exception e){/* do nothing */}
            plName.setForeground(Color.WHITE);
            plName.setHorizontalAlignment(JLabel.CENTER);
            plName.setVerticalAlignment(JLabel.NORTH);
            toFill.add(plName,BorderLayout.NORTH);

            JLabel storyTimeline = new JLabel("Description: "/*s.getDescription()*/);
            storyTimeline.setForeground(Color.WHITE);
            storyTimeline.setHorizontalAlignment(JLabel.LEFT);
            toFill.add(storyTimeline,BorderLayout.CENTER);

            tmpLobby.add(toFill, BorderLayout.CENTER);
            
            allStories.add(tmpLobby);
            storyList.add(tmpLobby);
        }
        allStories.updateUI();
    }
    
    public void createLobby(Story s) {
    }

    public void getStories() {
    }
        
    @Override
    public void terminateView() {
    }
    @Override
    public void hideView() {
    }
    @Override
    public void updateView() {/*** nothing to update ***/}
    @Override
    public void showView() {
    }
}