/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dto;

public class UsersShowDTO {
    private String userid;
     private String username;
      private String address; 
      private String city;
      private String email;
      private Long mobilenumber;

    public UsersShowDTO() {
        
    }

    @Override
    public String toString() {
        return "UsersShowDTO{" + "userid=" + userid + ", username=" + username + ", address=" + address + ", city=" + city + ", email=" + email + ", mobilenumber=" + mobilenumber + '}';
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getMobilenumber() {
        return mobilenumber;
    }

    public void setMobilenumber(Long mobilenumber) {
        this.mobilenumber = mobilenumber;
    }

    public UsersShowDTO(String userid, String username, String address, String city, String email, Long mobilenumber) {
        this.userid = userid;
        this.username = username;
        this.address = address;
        this.city = city;
        this.email = email;
        this.mobilenumber = mobilenumber;
    }
      
      
      
}
