/*==================================================================
 * Author: Erin Avllazagaj AKA "Albocoder"
 * Website: http://erin.avllazagaj.ug.bilkent.edu.tr
 * Date: Dec/07/2016
 * Version: 1.0.0
 *==================================================================
 * Referrer: https://github.com/Albocoder/CS319-Group22
 *==================================================================
 * Changelog:
 *==================================================================
 * Description:
 * This class is the homepage view that the user will see when 
 * logged in.
 * */
import javax.swing.JFrame;
import javax.swing.border.EtchedBorder;
import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.awt.*;

@SuppressWarnings("serial")
public class HomeView extends JFrame implements Viewable{
	
	//private HomePage mainData;
	private ViewManager referrer;
	private JScrollPane onWaitLobbies;
	private JLabel profilePic;
	private JButton logoutButton;
	private JButton viewFinished;
	private JComboBox onGoing;
	private JButton createLobby;
	private JLabel onlineUsers;
	
	//newly added classes
	private ArrayList<JPanel> lobbies;
	private JPanel lobbiesContainer;
	private JPanel allTheThings;
	
	public HomeView(ViewManager ref){
		referrer = ref;
		onWaitLobbies = new JScrollPane();
		lobbiesContainer = new JPanel();
		lobbiesContainer.setLayout(new GridLayout(/*mainData.getLobbiesWaiting().size()*/9,1));
		for(int i =0; i < 1; i++/*Lobby l:mainData.getLobbiesWaiting()*/){
			JPanel tmpLobby = new JPanel(new BorderLayout());
			tmpLobby.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));
			//this sets up a padding from the border to the components
			tmpLobby.add(new JPanel(), BorderLayout.NORTH);
			tmpLobby.add(new JPanel(), BorderLayout.SOUTH);
			tmpLobby.add(new JPanel(), BorderLayout.EAST);
			tmpLobby.add(new JPanel(), BorderLayout.WEST);
			//here add the specifics for each lobby
			JPanel toFill = new JPanel(new BorderLayout());
			
			//adding the icon to the left
			JLabel icon = new JLabel();
			icon.setIcon(null/*l.getStory().getImage()*/);
			icon.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
			toFill.add(icon,BorderLayout.WEST);
			
			JLabel lobbyName = new JLabel("Best Lobby"/*l.getName()*/);
			toFill.add(lobbyName,BorderLayout.NORTH);
			
			JLabel lobbyQuota = new JLabel();
			lobbyQuota.setText("4"/*l.getQuota()*/+"/"+"5"/*l.getSeats().size()*/);
			toFill.add(lobbyQuota,BorderLayout.SOUTH);
			
			JLabel storyTimeline = new JLabel("Timeline: "/*+l.getStory().getTimeline()*/);
			toFill.add(storyTimeline,BorderLayout.CENTER);
			
			tmpLobby.add(toFill, BorderLayout.CENTER);
			
			//adding the listeners for the components
			//tmpLobby.addMouseListener(l);
			
			lobbiesContainer.add(tmpLobby);
			lobbies.add(tmpLobby);
		}
		onWaitLobbies.add(lobbiesContainer);
		profilePic = new JLabel();
		profilePic.setIcon(null/*mainData.getPlayer().getProfile().getPhoto()*/);
		logoutButton = new JButton("Logout");
		viewFinished = new JButton("Finished Games");
		onGoing = new JComboBox();
		onGoing.addItem("Ongoing Games");
		for(int i = 0; i < 1; i++/*Lobby l:mainData.getPlayer().getOngoingGames()*/){
			onGoing.addItem("Secret Lobby"/*l.getName()*/);
		}
		//add listeners for the combobox and the buttons(especially finished games button)
		createLobby = new JButton("Create Lobby");
		onlineUsers = new JLabel("Online users: "/*+mainData.getOnlineUsers()*/);
		
		// add all the things created above to one JPanel and add that JPanel to the JFrame
		allTheThings = new JPanel( new BorderLayout());// TODO - do this crap here
	}
	
	public void logout() {
		// TODO - implement HomeView.logout
		throw new UnsupportedOperationException();
	}

	public void createLobby() {
		// TODO - implement HomeView.createLobby
		throw new UnsupportedOperationException();
	}

	public void joinLobby(/*Lobby aLobby   ******** uncomment when Lobby is created ***********/) {
		// TODO - implement HomeView.joinLobby
		throw new UnsupportedOperationException();
	}

	@Override
	public void terminateView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hideView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showView() {
		// TODO Auto-generated method stub
		
	}

}
