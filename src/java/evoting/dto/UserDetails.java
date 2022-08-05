/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evoting.dto;


public class UserDetails {

    public UserDetails(String userid, String username, String address, String city, String email, long mobile) {
        this.userid = userid;
        this.username = username;
        this.address = address;
        this.city = city;
        this.email = email;
        this.mobile = mobile;
    }

    public UserDetails(String userid, String password, String username, String address, String city, String email, String gender) {
        this.userid = userid;
        this.password = password;
        this.username = username;
        this.address = address;
        this.city = city;
        this.email = email;
        this.gender = gender;
    }
 public UserDetails(String userid, String password, String username, String address, String city, String email, long mobile) {
        this.userid = userid;
        this.password = password;
        this.username = username;
        this.address = address;
        this.city = city;
        this.email = email;
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "UserDetails{" + "userid=" + userid + ", password=" + password + ", username=" + username + ", address=" + address + ", city=" + city + ", email=" + email + ", mobile=" + mobile + ", gender=" + gender + '}';
    }

    
    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public long getMobile() {
        return mobile;
    }

    public void setMobile(long mobile) {
        this.mobile = mobile;
    }

    private String userid;
    private String password;
    private String username;
    private String address;
    private String city; 
    private String email;
    private long mobile;
    private String gender;
        
public UserDetails()
{
    
}
   
}
