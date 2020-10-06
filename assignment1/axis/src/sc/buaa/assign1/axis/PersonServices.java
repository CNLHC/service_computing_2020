package sc.buaa.assigna.axis;
public class PersonServices {
    private String name =  "CNLHC";
    private boolean gender= false;
    private int age= 22;

    public  String sayHello() {
        return  "Hello world!" + name;
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
