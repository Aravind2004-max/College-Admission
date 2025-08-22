
package enrolledStudents;


public class StdDetails {
    private String id;
    private String stdName;
    private String stdAge;
    private String cType;
    private String amtPaid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }

    public String getStdAge() {
        return stdAge;
    }

    public void setStdAge(String stdAge) {
        this.stdAge = stdAge;
    }

    public String getcType() {
        return cType;
    }

    public void setcType(String cType) {
        this.cType = cType;
    }

    public String getAmtPaid() {
        return amtPaid;
    }

    public void setAmtPaid(String amtPaid) {
        this.amtPaid = amtPaid;
    }
    
    @Override
    public String toString(){
        return "\n"+id +"\n"+"\n"+"\n"+"______________________________________________________"+"\n"+"\n"+"\n"+
               stdName +"\n"+"\n"+"\n"+"______________________________________________________"+"\n"+"\n"+"\n"+
               stdAge +"\n"+"\n"+"\n"+ "______________________________________________________"+"\n"+"\n"+"\n"+
               cType +"\n"+"\n"+"\n"+  "______________________________________________________"+"\n"+"\n"+"\n"+
               amtPaid +"\n";
    }
}
