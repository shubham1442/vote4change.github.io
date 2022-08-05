/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.CandidateInfoDTO;
import evoting.dto.VoteDTO;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author ekta dange
 */
public class VoteDAO {
    private static PreparedStatement ps1,ps2,ps3,ps4;
    private static Statement st;
    static
    {
     try {
            
            ps1 = DBConnection.getConnection().prepareStatement("Select candidate_id from voting where voter_id=?");
            ps2 = DBConnection.getConnection().prepareStatement("Select candidate_id , username, party,symbol from candidate,user_details where candidate.user_id=user_details.adharnumber and candidate.candidate_id=?");
            ps3 = DBConnection.getConnection().prepareStatement("Insert into voting values(?,?)");
            ps4= DBConnection.getConnection().prepareStatement("Select candidate_id ,count(*) as votes_obt from voting group by candidate_id order by votes_obt desc");
            st=DBConnection.getConnection().createStatement();
            
            
        } catch (SQLException sq)  {
            System.out.println("yaha bhi aagyi");
            sq.printStackTrace();
        }
    }
    
    public static String getCandidateID(String userid) throws SQLException
    {
        ps1.setString(1,userid);
        ResultSet rs=ps1.executeQuery();
        if(rs.next())
        {
            return rs.getString(1);
        }
        return null;
    }
    
    public static CandidateInfoDTO getVote(String candidateid) throws Exception
    {
         ps2.setString(1,candidateid);
        ResultSet rs=ps2.executeQuery();
        CandidateInfoDTO cd=new CandidateInfoDTO();
        Blob blob;
        InputStream inputStream;
        byte[] buffer;
        byte[] imageBytes;
        int bytesRead;
        String base64Image;
        ByteArrayOutputStream outputStream;
        
        if(rs.next())
        {
            blob=rs.getBlob(4);
            inputStream=blob.getBinaryStream();
            outputStream=new ByteArrayOutputStream();
            buffer=new byte[4096];
            bytesRead=-1;
            while((bytesRead=inputStream.read(buffer))!=-1)
            {
             outputStream.write(buffer, 0, bytesRead);
                
            }
            imageBytes=outputStream.toByteArray();
           Base64.Encoder en=Base64.getEncoder();
            base64Image=en.encodeToString(imageBytes);
        
        cd.setSymbol(base64Image);
        cd.setCandidateId(candidateid);
        cd.setCandidatename(rs.getString(2));
        cd.setParty(rs.getString(3));
               
        }
        return cd;
    }
    
    public static boolean addVote(VoteDTO obj) throws SQLException 
            {
             ps3.setString(1,obj.getCandidateid());
             ps3.setString(2, obj.getVoterid());
             int i=ps3.executeUpdate();
             if(i==1)
             return true;
             else
                 return false;
               
            }
    
    public static Map<String,Integer> getResult() throws SQLException
    {
        Map<String,Integer> result=new LinkedHashMap<>();
        ResultSet rs=ps4.executeQuery();
        while(rs.next())
        {
            result.put(rs.getString(1),rs.getInt(2));
        }
        return result;
    }
    
    public static int getVoteCount() throws SQLException
    {
        ResultSet rs=st.executeQuery("Select count(*) from voting");
        if(rs.next())
        {
            return rs.getInt(1);
        }
        return 0;
    }
}

