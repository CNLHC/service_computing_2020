package sc.buaa.assignment3;

public class BasicInfo{
    private String name =  "CNLHC";
    private boolean gender= false;
    private int age= 23;

    public  String process() {
        return  "process" ;
    }
    public  String sayHello() {
        return  "Hello world! BasicInfo" ;
    }
    public  String getName() {
        return name;
    }
    public  int getAge() {
        return age;
    }
    public  boolean getGender() {
        return gender;
    }

    public void setName(String rname) {
        name = rname;
    }
    public void setAge(int rage) {
        age = rage;
    }
    public void setGender(boolean rgender) {
        gender = rgender;
    }
}
