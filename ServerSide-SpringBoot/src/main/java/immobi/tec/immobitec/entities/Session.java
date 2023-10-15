package immobi.tec.immobitec.entities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class Session {
    static int id_user;

    static String name ;
    static String lastname ;
    static String password ;
    static String email ;
    static Date birthday ;

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        Session.role = role;
    }

    static int phoneNumber ;
    static float wallet ;
    static String Picture ;
static String role;
    public static int getId_user() {
        return id_user;
    }

    public static void setId_user(int id_user) {
        Session.id_user = id_user;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Session.name = name;
    }

    public static String getLastname() {
        return lastname;
    }

    public static void setLastname(String lastname) {
        Session.lastname = lastname;
    }

    public static String getPassword() {
        return password;
    }

    public static void setPassword(String password) {
        Session.password = password;
    }

    public static String getEmail() {
        return email;
    }

    public static void setEmail(String email) {
        Session.email = email;
    }

    public static Date getBirthday() {
        return birthday;
    }

    public static void setBirthday(Date birthday) {
        Session.birthday = birthday;
    }

    public static int getPhoneNumber() {
        return phoneNumber;
    }

    public static void setPhoneNumber(int phoneNumber) {
        Session.phoneNumber = phoneNumber;
    }

    public static float getWallet() {
        return wallet;
    }

    public static void setWallet(float wallet) {
        Session.wallet = wallet;
    }

    public static String getPicture() {
        return Picture;
    }

    public static void setPicture(String picture) {
        Picture = picture;
    }

}
