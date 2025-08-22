
package registration;

import courses.Courses;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.management.RuntimeErrorException;

public class Dao {
    
    public static Connection getConnection(){
        Connection con = null;
        String url = "jdbc:mysql://localhost:3306/magizhchiproject";
        String user = "root";
        String pwd = "root";
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, user, pwd);
        }catch(SQLException | ClassNotFoundException e){
            throw new RuntimeException(e.getMessage());
        }
        return con;
    }
    
    public boolean registry(Registeration datas){
        Connection con = null;
        PreparedStatement ps = null;
        String sql = null;
        boolean sts = false;
        
        try{
            if(con == null){
                con = Dao.getConnection();
            }
            sql = "call registery(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
            ps = con.prepareCall(sql);
            ps.setString(1,datas.getName());
            ps.setString(2,datas.getAge());
            ps.setString(3,datas.getGender());
            ps.setString(4,datas.getBoard());
            ps.setString(5,datas.getsName());
            ps.setString(6,datas.getMark());
            ps.setString(7,datas.getBoard1());
            ps.setString(8,datas.getsName1());
            ps.setString(9,datas.getMark1());
            ps.setString(10,datas.getfName());
            ps.setString(11,datas.getmName());
            ps.setString(12,datas.getFo());
            ps.setString(13,datas.getMo());
            ps.setString(14,datas.getAddr());
            int res = ps.executeUpdate();
            
           sts = res > 0;
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        finally{
            if(con != null){
                try{
                    con.close();
                    con = null;
                }catch(Exception e){
                    throw new RuntimeException(e.getMessage());
                }
            }
            if(ps != null){
                try{
                    ps.close();
                    ps = null;
                }catch(Exception e){
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
        return sts;
    }
    
    public List<CourseCom> comboCourses(){
        List<CourseCom> courses= new ArrayList<>();
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try{
            if(con == null){
                con = Dao.getConnection();
            }
            ps = con.prepareCall("call getCourseCombo");
            rs = ps.executeQuery();
            
            while(rs.next()){
                CourseCom pojo = new CourseCom();
                pojo.setCourse(rs.getString(1));
                courses.add(pojo);
            }
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }finally{
            if(con != null){
                try{
                    con.close();
                    con = null;
                }catch(Exception e){
                    throw new RuntimeException(e.getMessage());
                }
            }
            if(ps != null){
                try{
                    ps.close();
                    ps = null;
                }catch(Exception e){
                    throw new RuntimeException(e.getMessage());
                }
            }
            if(rs != null){
                try{
                    rs.close();
                    rs = null;
                }catch(Exception e){
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
        return courses;
    }
    
    public List<CourseAmt> courseAmt(){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<CourseAmt> amt = new ArrayList<>();
        
        try{
            if(con == null){
                con = Dao.getConnection();
            }
            ps = con.prepareCall("call getAmt");
            rs = ps.executeQuery();
            while(rs.next()){
                CourseAmt pojo = new CourseAmt();
                pojo.setCourseName(rs.getString(1));
                pojo.setCourseAmt(rs.getString(2));
                amt.add(pojo);
            }
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }finally{
            if(con != null){
                try{
                    con.close();
                    con = null;
                }catch(Exception e){
                    throw new RuntimeException(e.getMessage());
                }
            }
            if(ps != null){
                try{
                    ps.close();
                    ps = null;
                }catch(Exception e){
                    throw new RuntimeException(e.getMessage());
                }
            }
            if(rs != null){
                try{
                    rs.close();
                    rs = null;
                }catch(Exception e){
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
        return amt;
    }
    
    public boolean finallRegistry(FinallRegistration datas){
        Connection con = null;
        PreparedStatement ps = null;
        String sql = null;
        boolean sts = false;
        try{
            if(con == null){
                con = Dao.getConnection();
            }
            sql = "call finallRegistry(?,?,?,?,?,?,?,?,?,?,?)";
            ps = con.prepareCall(sql);
            ps.setString(1,datas.getGraduationType());
            ps.setString(2,datas.getCourseType());
            ps.setString(3,datas.getStdName());
            ps.setString(4,datas.getStdAge());
            ps.setString(5,datas.getStdPh());
            ps.setString(6,datas.getStdMail());
            ps.setString(7,datas.getUgClg());
            ps.setString(8,datas.getUgCgpa());
            ps.setString(9,datas.getFeeType());
            ps.setString(10,datas.getAmount());
            ps.setInt(11,0);
            int result = ps.executeUpdate();
            sts = result > 0;
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }finally{
            if(con != null){
                try{
                    con.close();
                    con = null;
                }catch(Exception e){
                    throw new RuntimeException(e.getMessage());
                }
            }
            if(ps != null){
                try{
                    ps.close();
                    ps = null;
                }catch(Exception e){
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
        return sts;
    }
    
    public String getId(){
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = null;
        String id = "";
        try{
            if(con == null){
                con = Dao.getConnection();
            }
            sql = "call getId()";
            ps = con.prepareCall(sql);
            rs = ps.executeQuery();
            
            if(rs.next()){
                StudentId pojo = new StudentId();
                pojo.setId(Integer.toString(rs.getInt(1)));
                id = pojo.getId();
            }else{
                id = "0";
            }
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }finally{
            if(con != null){
                try{
                    con.close();
                    con = null;
                }catch(Exception e){
                    throw new RuntimeException(e.getMessage());
                }
            }
            if(ps != null){
                try{
                    ps.close();
                    ps = null;
                }catch(Exception e){
                    throw new RuntimeException(e.getMessage());
                }
            }
            if(rs != null){
                try{
                    rs.close();
                    rs = null;
                }catch(Exception e){
                    throw new RuntimeException(e.getMessage());
                }
            }
        }
        return id;
    }
}
