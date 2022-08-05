/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dao;

import evoting.dbutil.DBConnection;
import evoting.dto.CandidateDTO;
import evoting.dto.CandidateDetailsDTO;
import evoting.dto.CandidateInfoDTO;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Base64;

/**
 *
 * @author ekta dange
 */
public class CandidateDAO {

    private static PreparedStatement ps, ps1, ps2, ps3,ps4,ps5,ps6,ps7;
    private static Statement st;
    static {
        try {
            st=DBConnection.getConnection().createStatement();
            ps = DBConnection.getConnection().prepareStatement("Select count(*) from candidate");
            ps1 = DBConnection.getConnection().prepareStatement("Select username from user_details where adharnumber=?");
            ps2 = DBConnection.getConnection().prepareStatement("Select distinct city from user_details ");
            ps3 = DBConnection.getConnection().prepareStatement("Insert into candidate values(?,?,?,?,?)");
            ps4= DBConnection.getConnection().prepareStatement("Select * from candidate where candidate_id=?");
            ps5=DBConnection.getConnection().prepareStatement("Select candidate_id , username, party,symbol from candidate,user_details where candidate.user_id=user_details.adharnumber and candidate.city=(select city from user_details where adharnumber=?)");
            ps6=DBConnection.getConnection().prepareStatement("Update candidate set party=?, city=?, symbol=? where candidate_id=?");
             ps7=DBConnection.getConnection().prepareStatement("Delete from candidate where candidate_id=?");
        
        } 
        catch (SQLException sq)  {
            System.out.println("yaha bhi aagyi");
            sq.printStackTrace();
        }
    }

    public static String getNewId() throws SQLException {
        ResultSet rs = ps.executeQuery();
        rs.next();
        {
            return "C" + (100 + rs.getInt(1) + 1);
        }
    }

    public static String getUserNameById(String uid) throws SQLException {
        ps1.setString(1, uid);
        ResultSet rs = ps1.executeQuery();

        if (rs.next()) {
            return rs.getString(1);
        } else {
            return null;
        }

    }

    public static ArrayList<String> getCity() throws SQLException {
        ArrayList<String> cityList = new ArrayList<>();
        ResultSet rs = ps2.executeQuery();
        while (rs.next()) {
            cityList.add(rs.getString(1));

        }
        return cityList;
    }

    public static boolean addCandidate(CandidateDTO obj) throws SQLException {
        ps3.setString(1, obj.getCandidateId());
        ps3.setString(3, obj.getParty());
        ps3.setString(2, obj.getUserid());
       // System.out.println("pagal hai ");
        //System.out.println(obj.getSymbol().toString());
        //System.out.println("kya ho rha hai reee");
        ps3.setBinaryStream(4, obj.getSymbol());
        //System.out.println("cha rhi kjsagfik");
        ps3.setString(5, obj.getCity());

        return ps3.executeUpdate() != 0;
    }

    public static ArrayList<String> getCandidateId() throws SQLException 
    {
        ArrayList<String> candidateList = new ArrayList<>();
        ResultSet rs = st.executeQuery("select candidate_id from candidate");
        while (rs.next()) {
            candidateList.add(rs.getString(1));

        }
        System.out.println("CandidateDAO ki candidateList"+candidateList);
        return candidateList;
    }
   
    public static CandidateDetailsDTO getDetailsById(String cid) throws Exception
    {
        ps4.setString(1,cid);
        ResultSet rs=ps4.executeQuery();
        CandidateDetailsDTO cd=new CandidateDetailsDTO();
        Blob blob;
        InputStream inputStream;
        byte[] buffer;
        byte[] imageBytes;
        int bytesRead;
        String base64image;
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
            base64image=en.encodeToString(imageBytes);
        
        cd.setSymbol(base64image);
        cd.setCandidateId(cid);
        cd.setCname(getUserNameById(rs.getString(2)));
        cd.setCity(rs.getString(5));
        cd.setParty(rs.getString(3));
        cd.setUserid(rs.getString(2));
        
        
        }
        return cd;
    }
    
    
    public static ArrayList<CandidateInfoDTO> viewCandidate(String adhar_id) throws Exception
    {
        ArrayList<CandidateInfoDTO> candidateList=new ArrayList<CandidateInfoDTO>();
       ps5.setString(1,adhar_id);
       
       ResultSet rs=ps5.executeQuery();
        Blob blob;
        InputStream inputStream;
        byte[] buffer;
        byte[] imageBytes;
        int bytesRead;
        String base64image;
        ByteArrayOutputStream outputStream;
        
       while(rs.next())
       {  
            blob=rs.getBlob(4);
            inputStream=blob.getBinaryStream();
            outputStream=new ByteArrayOutputStream();
            buffer=new byte[4096];
            bytesRead=-1;
            while((bytesRead=inputStream.read(buffer))!=-1)
            {
             outputStream.write(buffer,0,bytesRead);    
            }
            imageBytes=outputStream.toByteArray();
           Base64.Encoder en=Base64.getEncoder();
            base64image=en.encodeToString(imageBytes);
            
            CandidateInfoDTO cd=new CandidateInfoDTO();
        
        cd.setSymbol(base64image);
        cd.setCandidateId(rs.getString(1));
        cd.setCandidatename(rs.getString(2));
        cd.setParty(rs.getString(3));
        candidateList.add(cd);
       }
       return candidateList;     
    }
    
    public static boolean updateCandidate(CandidateDTO obj) throws SQLException
    {
        ps6.setString(4,obj.getCandidateId());
        ps6.setString(1, obj.getParty());
        ps6.setString(2, obj.getCity());
        ps6.setBinaryStream(3, obj.getSymbol());
        
        int i=ps6.executeUpdate();
         if( i!= -1)
           return true;
           else
               return false;
        
        
        
    } 
    public static boolean deleteCandidate(String cid) throws SQLException
    {
      ps7.setString(1, cid);
      int i=ps7.executeUpdate();
      if(i!= 1)
          return false;
      return true;
    }   
}
