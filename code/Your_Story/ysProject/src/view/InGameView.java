package view;

import java.awt.*;
import java.awt.event.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.*;
import mainPackage.*;

public class InGameView extends JFrame implements Viewable {
	
	static Font font;
	static int LINE_HEIGHT;
	
	public InGameView(Lobby l, ViewManager ref) {
		font = new Font("Tahoma", Font.PLAIN, 12);
		LINE_HEIGHT = font.getSize() + 8;
		
		JPanel mainPanel = new JPanel();
		
		JScrollPane scrollPane = new JScrollPane(new ChatView());
		MessageTypingBoxView mv = new MessageTypingBoxView(scrollPane);
		
		JPanel panel = new JPanel();
		JPanel onlineUsers = new JPanel();
		JPanel story = new JPanel();
		JPanel buttonPanel = new JPanel();
		JButton voteButton = new JButton("Vote");
		JButton exitButton = new JButton("Exit");
		
		add(mainPanel);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(700, 400));
		setVisible(true);
		addComponentListener(new ComponentAdapter(){
			public void componentResized(ComponentEvent e) {
				scrollPane.setSize(new Dimension(mv.getWidth(), mv.getHeight() - mv.getBoxHeight()));
			}
		});
		
		mainPanel.setLayout(new BorderLayout());
		mainPanel.add(mv, BorderLayout.CENTER);
		mainPanel.add(panel, BorderLayout.EAST);
		
		panel.setPreferredSize(new Dimension(300, 400));
		panel.setLayout(new BorderLayout());
		panel.add(story, BorderLayout.NORTH);
		panel.add(onlineUsers, BorderLayout.CENTER);
		panel.add(buttonPanel, BorderLayout.SOUTH);
		
		story.add(new JLabel(new ImageIcon(createStoryImage())));
		
		onlineUsers.add(new JLabel(new ImageIcon(createOnlineUsersImage()), SwingConstants.CENTER));
		
		buttonPanel.setLayout(new FlowLayout());
		buttonPanel.add(voteButton);
		buttonPanel.add(exitButton);
		
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.getVerticalScrollBar().setUnitIncrement(15);
		scrollPane.getVerticalScrollBar().setBlockIncrement(0);
	}
	
	public BufferedImage createStoryImage() {
		int width = 300;
		String story = "This is for trial. There is Story Description here in real game...";
		ArrayList<String> messageContent = InGameView.fitString(story, width);
		int height = LINE_HEIGHT * (messageContent.size());
		
		BufferedImage img = new BufferedImage(width + 1, height + 1, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) img.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setColor(Color.RED);
		g.setFont(font);
		for (int i = 0; i < messageContent.size(); i++) {
			g.drawString(messageContent.get(i), 10, i * LINE_HEIGHT + 15);
		}
		return img;
	}
	
	public BufferedImage createOnlineUsersImage() {
		boolean[] userIsOnline = {true,true,false,true};
		String[] username = {"alboCoder", "cevatBaris", "Kaxell", "karaali"};
		int userNumber = 4;
		String online = "Online Users";
		Font headerFont = new Font("Tahoma", Font.BOLD, 24);
		
		FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
		int height = userNumber * LINE_HEIGHT + 50;
		int width = (int)(headerFont.getStringBounds(online, frc).getWidth()) + 20;
		for (String s : username) {
			int length = (int)(font.getStringBounds(s, frc).getWidth());
			if (length + 40 > width)
				width = length + 40;
		}
		
		BufferedImage img = new BufferedImage(width + 1, height + 1, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = (Graphics2D) img.createGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
		        RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.BLACK);
		g.drawRect(0, 0, width, height);
		g.setFont(headerFont);
		g.setColor(Color.DARK_GRAY);
		g.drawString(online, 10, 30);
		g.setFont(font);
		for (int i = 0; i < userNumber; i++) {
			if (userIsOnline[i]) {
				g.setColor(Color.GREEN);
			} else {
				g.setColor(Color.RED);
			}
			g.fillOval(30, i * LINE_HEIGHT + 55, 5, 5);
			g.setColor(Color.BLACK);
			g.drawString(username[i], 40, (i + 1) * LINE_HEIGHT + 40);
		}
		return img;
	}
	
	public static ArrayList<String> fitString(String s, int finalWidth) {
		ArrayList<String> modified = new ArrayList<String>();
		FontRenderContext frc = new FontRenderContext(new AffineTransform(), true, true);
		
		int index = -1;
		
		for (int i = 0; i < s.length(); i++) {
			int length = (int)(font.getStringBounds(s.substring(0, i + 1), frc).getWidth());
			if (s.charAt(i) == ' ' && length + 30 < finalWidth) {
				index = i;
			} else if (length + 30 >= finalWidth) {
				if (index == -1) {
					modified.add(s.substring(0, i));
					s = s.substring(i);
				} else {
					modified.add(s.substring(0, index));						
					s = s.substring(index + 1);
					index = -1;
				}
				i = 0;	
			} else if (s.charAt(i) == '\n') {
				modified.add(s.substring(0, i));
				s = s.substring(i + 1);
				index = -1;
				i = 0;
			} 
		}
		modified.add(s);
		return modified;
	}

//	public static void main (String [] args) {
//		java.awt.EventQueue.invokeLater(new Runnable() {
//            @Override
//            public void run() {
//                new InGameView(null);
//            }
//        });
//	}
	
	@Override
	public void terminateView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hideView() {
		setVisible(false);
	}

	@Override
	public void updateView() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void showView() {
		setVisible(true);
	}
}