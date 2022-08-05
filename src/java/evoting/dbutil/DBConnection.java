package evoting.dbutil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBConnection {
    
    private static Connection conn;
    static
    {
        try
       {
            Class.forName("oracle.jdbc.OracleDriver");
            conn= DriverManager.getConnection("jdbc:oracle:thin:@//Laptop-8IESLEN3:1521/XE","voteforchange","voteforchange");
            System.out.println("connection done");
       }
        catch(ClassNotFoundException ex)
        {
            ex.printStackTrace();
            
        }
       catch(SQLException e)
       {
           e.printStackTrace();
       } 
    }
    
    public static Connection getConnection()
    {
        return conn;
    }
    public static void closeConnection()
    {
        try
        {
            if(conn!=null)
                conn.close();
        }
        catch(SQLException sqex)
        {
            sqex.printStackTrace();
        }
    }
    
    
}
