package mainPackage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import thirdparty.*;

/**
 *
 * @author kaxell
 */
public class DBInterface {
	private static final String type = "mysql";
        private static final String adress = "160.153.75.104";
        private static final String port = "3306";
        private static final String database = "YourStoryDB";
        private static final String username = "albocoder";
        private static final String password = "@fI@6U!yMGRMv^]";
        
        private static final String VOTING_DATA = "voting";
        
	private static DBConn c = new DBConn(type, adress, port, database, username, password);
        private static Connection con = c.ConnectToDB();
        private static DBInter db = new DBInter(con);

    /**
     *
     * @return
     */
    public static DBInter getConnection(){
            return db;
        }

    /**
     *
     * @param term
     * @return
     */
    public static String escapeString(String term){
            term = term.replaceAll("'","''");
            return term;
        }
        
    /**
     *
     * @param r
     * @return
     */
    public static Character[] resultSetToCharacterArray(ResultSet r){
            try {
                r.last();
                int size = r.getRow();
                Character[] result = new Character[size];
                int index = 0;
                Character c;
                for(boolean go = r.first(); go; go = r.next()){
                    c = new Character(r.getString("description"), r.getString("name"), r.getLong("id"), r.getInt("occupied")== 1);
                    result[index++] = c;
                }
                return result;
            } catch (SQLException ex) {
                Logger.getLogger(DBInterface.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        
    /**
     *
     * @param r
     * @return
     */
    public static Message[] resultSetToMessageArray(ResultSet r){
            try {
                r.last();
                int size = r.getRow();
                Message[] result = new Message[size];
                int index = 0;
                Message m;
                for(boolean go = r.first(); go; go = r.next()){
                    m = new Message(r.getString("user"), r.getString("charac"), r.getString("body"));
                    result[index++] = m;
                }
                return result;
            } catch (SQLException ex) {
                Logger.getLogger(DBInterface.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        
    /**
     *
     * @param r
     * @return
     */
    public static Seat[] resultSetToSeatArray(ResultSet r){
            System.out.println("Middle2");
            try {
                r.last();
                int size = r.getRow();
                Seat[] result = new Seat[size];
                int index = 0;
                Seat s;
                for(boolean go = r.first(); go; go = r.next()){
                    s = new Seat(r.getLong("id"), r.getLong("lobby"), r.getLong("charac"));
                    System.out.println("Middle3");
                    if(r.getLong("user") > 0)
                        s.addPlayer(r.getByte("user"));
                    System.out.println("Middle4");
                    result[index++] = s;
                }
                System.out.println("Out2");
                return result;
            } catch (SQLException ex) {
                Logger.getLogger(DBInterface.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        
    /**
     *
     * @param r
     * @return
     */
    public static Player[] resultSetToPlayerArray(ResultSet r){
            try {
                r.last();
                int size = r.getRow();
                Player[] result = new Player[size];
                int index = 0;
                Player p;
                for(boolean go = r.first(); go; go = r.next()){
                    p = new Player(r.getLong("id"));
                    result[index++] = p;
                }
                return result;
            } catch (SQLException ex) {
                Logger.getLogger(DBInterface.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        
    /**
     *
     * @param r
     * @return
     */
    public static Lobby[] resultSetToLobbyArray(ResultSet r){
            try {
                r.last();
                int size = r.getRow();
                Lobby[] result = new Lobby[size];
                int index = 0;
                Lobby l;
                for(boolean go = r.first(); go; go = r.next()){
                    l = new Lobby(r.getString("name"), r.getLong("id"), r.getInt("quota"), r.getInt("state"), null, r.getLong("storyid"));
                    long[] votings = DBInterface.selectIntArray(VOTING_DATA, "id", "lobby", l.getID());
                    ArrayList<Long> temp = new ArrayList<Long>();
                    for(int i = 0; i < votings.length; i++){
                        temp.add(new Long(votings[i]));
                    }
                    l.setVoteID(temp);
                    result[index++] = l;
                }
                return result;
            } catch (SQLException ex) {
                Logger.getLogger(DBInterface.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }
        
    /**
     *
     * @param r
     * @return
     */
    public static Story[] resultSetToStoryArray(ResultSet r){
            try {
                r.last();
                int size = r.getRow();
                Story[] result = new Story[size];
                int index = 0;
                Story s;
                for(boolean go = r.first(); go; go = r.next()){
                    s = new Story(r.getString("description"), r.getString("timeline"), r.getLong("id"));
                    result[index++] = s;
                }
                return result;
            } catch (SQLException ex) {
                Logger.getLogger(DBInterface.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }

    /**
     *
     * @param r
     * @return
     */
    public static long[] resultSetToIntArray(ResultSet r){
            try {
                r.last();
                int size = r.getRow();
                long[] result = new long[size];
                int index = 0;
                for(boolean go = r.first(); go; go = r.next()){
                    result[index++] = r.getLong(1);
                }
                return result;
            } catch (SQLException ex) {
                Logger.getLogger(DBInterface.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }

    /**
     *
     * @param r
     * @return
     */
    public static String[] resultSetToStringArray(ResultSet r){
            try {
                r.last();
                int size = r.getRow();
                String[] result = new String[size];
                int index = 0;
                for(boolean go = r.first(); go; go = r.next()){
                    result[index++] = r.getString(1);
                }
                return result;
            } catch (SQLException ex) {
                Logger.getLogger(DBInterface.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }

    /**
     *
     * @param table
     * @param column
     * @param id
     * @param value
     * @return
     */
    public static boolean update(String table, String column, long id, String value){
            value = escapeString(value);
            return db.executeStuff("UPDATE " + table + " SET " + column + " = '" + value + "' WHERE id = " + id);
        }

    /**
     *
     * @param table
     * @param column
     * @param id
     * @param value
     * @return
     */
    public static boolean update(String table, String column, long id, int value){
            return db.executeStuff("UPDATE " + table + " SET " + column + " = " + value + " WHERE id = " + id);
        }

    /**
     *
     * @param table
     * @param column
     * @param id
     * @return
     */
    public static long selectInt(String table, String column, long id){
            try {
                ResultSet r = db.selectStuff("SELECT " + column + " FROM " + table + " WHERE id = " + id);
                r.first();
                return r.getLong(1);
            } catch (SQLException ex) {
                Logger.getLogger(DBInterface.class.getName()).log(Level.SEVERE, null, ex);
                return 0;
            }
        }

    /**
     *
     * @param table
     * @param column
     * @param id
     * @return
     */
    public static String selectString(String table, String column, long id){
            try {
                ResultSet r = db.selectStuff("SELECT " + column + " FROM " + table + " WHERE id = " + id);
                r.first();
                return r.getString(1);
            } catch (SQLException ex) {
                Logger.getLogger(DBInterface.class.getName()).log(Level.SEVERE, null, ex);
                return null;
            }
        }

    /**
     *
     * @param table
     * @param targetColumn
     * @param conditionColumn
     * @param conditionValue
     * @return
     */
    public static long[] selectIntArray(String table, String targetColumn, String conditionColumn, String conditionValue){
            conditionValue = escapeString(conditionValue);
            ResultSet r = db.selectStuff("SELECT " + targetColumn + " FROM " + table + " WHERE " + conditionColumn + " = '" + conditionValue + "'");
            return resultSetToIntArray(r);
        }

    /**
     *
     * @param table
     * @param targetColumn
     * @param conditionColumn
     * @param conditionValue
     * @return
     */
    public static long[] selectIntArray(String table, String targetColumn, String conditionColumn, long conditionValue){
            ResultSet r = db.selectStuff("SELECT " + targetColumn + " FROM " + table + " WHERE " + conditionColumn + " = " + conditionValue);
            return resultSetToIntArray(r);
        }

    /**
     *
     * @param table
     * @param targetColumn
     * @param conditionColumn
     * @param conditionValue
     * @return
     */
    public static String[] selectStringArray(String table, String targetColumn, String conditionColumn, String conditionValue){
            conditionValue = escapeString(conditionValue);
            ResultSet r = db.selectStuff("SELECT " + targetColumn + " FROM " + table + " WHERE " + conditionColumn + " = '" + conditionValue + "'");
            return resultSetToStringArray(r);
        }

    /**
     *
     * @param table
     * @param targetColumn
     * @param conditionColumn
     * @param conditionValue
     * @return
     */
    public static String[] selectStringArray(String table, String targetColumn, String conditionColumn, long conditionValue){
            ResultSet r = db.selectStuff("SELECT " + targetColumn + " FROM " + table + " WHERE " + conditionColumn + " = " + conditionValue);
            return resultSetToStringArray(r);
        }
}
