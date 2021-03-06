package mainPackage;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import thirdparty.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * This is used to handle connection to database for Chat class
 * @author Cevat
 */
public class ChatConnection {
    
    /**
     * Name of chat table in database
     */
    public final static String CHAT_DATA = "message";
    private final static String PLAYER_DATA = "user";
    private final static String SEAT_DATA = "seat";
    private final static String CHARACTER_DATA = "charac";
    
    /**
     * Gets the messages of given lobby
     * @param id ID of lobby
     * @return Arraylist of messages
     */
    public static ArrayList<Message> getMessages(long id){
        ResultSet r =DBInterface.getConnection().selectStuff("SELECT " + CHAT_DATA +
                ".body AS body, " + CHARACTER_DATA +
                ".name AS charac, " + PLAYER_DATA +
                ".username AS user FROM " + CHAT_DATA +
                " LEFT JOIN " + PLAYER_DATA +
                " ON " + PLAYER_DATA +
                ".id = " + CHAT_DATA +
                ".user LEFT JOIN " + SEAT_DATA +
                " ON " + SEAT_DATA +
                ".user = " + CHAT_DATA +
                ".user AND " + SEAT_DATA +
                ".lobby = " + CHAT_DATA +
                ".lobby LEFT JOIN " + CHARACTER_DATA +
                " ON " + SEAT_DATA +
                ".charac = " + CHARACTER_DATA +
                ".id WHERE " + CHAT_DATA +
                ".lobby = " + id + " GROUP BY " + CHAT_DATA +
                ".id");
        return new ArrayList<Message>(Arrays.asList(DBInterface.resultSetToMessageArray(r)));
    }

    /**
     * Gets a lobby's messages whose ID is bigger than given message
     * @param lobby ID of lobby
     * @param lastMessage ID  of message
     * @return ArrayList of messages
     */
    public static ArrayList<Message> getMessages(long lobby, long lastMessage){
        ResultSet r =DBInterface.getConnection().selectStuff("SELECT " + CHAT_DATA +
                ".body AS body, " + CHARACTER_DATA +
                ".name AS charac, " + PLAYER_DATA +
                ".username AS user FROM " + CHAT_DATA +
                " LEFT JOIN " + PLAYER_DATA +
                " ON " + PLAYER_DATA +
                ".id = " + CHAT_DATA +
                ".user LEFT JOIN " + SEAT_DATA +
                " ON " + SEAT_DATA +
                ".user = " + CHAT_DATA +
                ".user AND " + SEAT_DATA +
                ".lobby = " + CHAT_DATA +
                ".lobby LEFT JOIN " + CHARACTER_DATA +
                " ON " + SEAT_DATA +
                ".charac = " + CHARACTER_DATA +
                ".id WHERE " + CHAT_DATA +
                ".lobby = " + lobby + " AND " + CHAT_DATA +
                ".id > " + lastMessage + " GROUP BY " + CHAT_DATA +
                ".id");
        return new ArrayList<Message>(Arrays.asList(DBInterface.resultSetToMessageArray(r)));
    }

    /**
     * Sends message to database
     * @param message Body of message
     * @param lobby ID of lobby that message will be sent
     * @param user ID of player who is owner message
     * @return ID of created message
     */
    public static long sendMessage(String message, long lobby, long user){
        message = DBInterface.escapeString(message);
        DBInterface.getConnection().executeStuff("INSERT INTO " + CHAT_DATA + "(user, body, lobby) VALUES(" + user +", '" + message + "', " + lobby + ")");
        long[] temp = DBInterface.selectIntArray(CHAT_DATA, "id", "lobby", lobby);
        return temp[temp.length - 1];
    }
    
}
