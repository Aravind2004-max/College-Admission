
package enrolledStudents;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class Dao {
    public static Connection getCon(){
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/magizhchiproject";
        String user = "root";
        String pwd = "root";
        
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url,user,pwd);
        }catch(SQLException | ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        return con;
    }
    
    public List<StdDetails> enrolledStuds(int id){
        List<StdDetails> data = new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = null;
        
        try{
            if(con == null){
                con = Dao.getCon();
            }
            sql = "call enrolledStds(?)";
            ps = con.prepareCall(sql);
            ps.setInt(1,id);
            rs = ps.executeQuery();
            while(rs.next()){
                StdDetails pojo = new StdDetails();
                int stId = rs.getInt(1);
                pojo.setId(Integer.toString(stId));
                pojo.setStdName(rs.getString(2));
                pojo.setStdAge(rs.getString(3));
                pojo.setcType(rs.getString(4));
                pojo.setAmtPaid(rs.getString(5));
                
                data.add(pojo);
            }
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return data;
    }
}
