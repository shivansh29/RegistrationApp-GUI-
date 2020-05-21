package db;

import java.sql.*;
import javax.swing.*;

public class DbConnect {
    private static Connection c;
    private static Statement st;
    public static PreparedStatement insertclient,getClients,updateClient,deleteClient;
    static
    {
        try{
            Class.forName("com.mysql.jdbc.Driver");
        Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/reg","root","INCAPP");
        st=c.createStatement();
        insertclient=c.prepareStatement("insert into client_info(name,gender,dob,country,address,language)values(?,?,?,?,?,?)");
        getClients=c.prepareStatement("select * from client_info where name like ?");
        updateClient=c.prepareStatement("update client_info set name=?,gender=?,dob=?,country=?,address=?,language=? where cid=?");
        deleteClient=c.prepareStatement("delete from client_info where cid=?");
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null,ex);
        }
    }
    public static ResultSet checkLogin(String u,String p)
    {
        try{
            ResultSet rs=st.executeQuery("select * from admin_login where aid='"+u+"'and pass = '"+p+"'");
            if(rs.next())
            {
                return rs;
            }
            else
            {
                return null;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
            return null;
        }
    }
    public static void closeConnection()
    {
        try{
            if(c!=null)
            {
                c.close();
                c=null;
            }
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }
}
