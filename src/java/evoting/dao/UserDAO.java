
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.UserDTO;
import evoting.dto.UserDetails;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class UserDAO {

    private static PreparedStatement ps,ps1,ps2,ps3 ;
    private static Statement st,st1;
    static {
        try {
            ps = DBConnection.getConnection().prepareStatement("select usertype from user_details where adharnumber=? and password=?");
            ps1 = DBConnection.getConnection().prepareStatement("select * from user_details where adharnumber=?");
            ps2=DBConnection.getConnection().prepareStatement("update user_details set username=? ,address=? ,city=? ,email=? ,mobilenumber=? where adharnumber=?");
            ps3=DBConnection.getConnection().prepareStatement("delete from user_details where adharnumber=?");
            st=DBConnection.getConnection().createStatement();
            st1=DBConnection.getConnection().createStatement();
        }
        
        catch (SQLException sqlex) {
            sqlex.printStackTrace();
        }
    }

    public static String validateUser(UserDTO user) throws SQLException
    {
        ps.setString(1, user.getUserid());
        ps.setString(2, user.getPassword());
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getString(1);
        }
        return null;
    }
    
    public static ArrayList<UserDetails> showUser() throws SQLException
    {
        ArrayList<UserDetails> userdetailslist= new ArrayList<>();
        
        ResultSet rs=st.executeQuery("select * from user_details order by adharnumber");
       
        while(rs.next())
        {
            UserDetails ud=new UserDetails();
            ud.setUserid(rs.getString(1));
            ud.setUsername(rs.getString(3));
            ud.setAddress(rs.getString(4));
            ud.setCity(rs.getString(5));
            ud.setEmail(rs.getString(6));
            ud.setMobile(rs.getLong(7));
            
            System.out.println("ud in getDetailsbyId"+ud);
            userdetailslist.add(ud);
            
        }
         return userdetailslist;
        }
    
    public static ArrayList<String> getUserId() throws SQLException
    {
        ResultSet rs=st1.executeQuery("select adharnumber from user_details");
        ArrayList<String> userIdList=new ArrayList<>();
        while(rs.next())
        {
         userIdList.add(rs.getString((1)));   
        }
        
        return userIdList;
    }
    
    public static UserDetails getUserDetailsById(String userid) throws SQLException
    {
        System.out.println(userid);
      ps1.setString(1,userid);
      ResultSet rs=ps1.executeQuery();
      UserDetails user =new UserDetails();
      if(rs.next())
      {     
            user.setUserid(rs.getString(1));
            user.setUsername(rs.getString(3));
            user.setAddress(rs.getString(4));
            user.setCity(rs.getString(5));
            user.setEmail(rs.getString(6));
            user.setMobile(rs.getLong(7));  
      }
        System.out.println("DAO ka user"+user);
      return user;
    }
    
   public static boolean updateUserInDB(UserDetails data) throws SQLException
   {
       ps2.setString(6,data.getUserid());
       ps2.setString(1,data.getUsername() );
        ps2.setString(2,data.getAddress() );
         ps2.setString(3,data.getCity() );
          ps2.setString(4,data.getEmail() );
           ps2.setLong(5,data.getMobile() );
           
           int i=ps2.executeUpdate();
           
           if( i!= -1)
           return true;
           else
               return false;
       
       
   }
   
   public static boolean deleteUserInDB(String userid) throws SQLException
   {
      ps3.setString(1,userid);
       return ps3.executeUpdate()!=0;
      
      
   }
       
}

    

