package courses;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Dao {
    
    public List<Courses> datas(){
        List<Courses> datas = new ArrayList<Courses>();
        try{
            Connection con = null;
            String url = "jdbc:mysql://localhost:3306/magizhchiProject";
            String user = "root";
            String pass = "root";
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pass);
            
            PreparedStatement ps = con.prepareStatement("select * from courses");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Courses pojo = new Courses();
                pojo.setId(rs.getString(1));
                pojo.setCourse(rs.getString(2));
                pojo.setAvailablity(rs.getBoolean(3));
                pojo.setFee(rs.getString(4));
                
                datas.add(pojo);
            }
        }catch(SQLException e){
            throw new RuntimeException(e.getMessage());
        }catch(ClassNotFoundException e){
            throw new RuntimeException(e.getMessage());
        }
        return datas;
    }
}
