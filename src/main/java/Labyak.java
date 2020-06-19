import javax.xml.bind.annotation.*;

@XmlRootElement(name = "labyak")
@XmlAccessorType(XmlAccessType.FIELD)
public class Labyak {
    @XmlAttribute
    String name;
    @XmlAttribute
    Double age;
    @XmlAttribute
    String sex;
    Double ageLastShaven;

    //@XmlElementWrapper(name = "herd")

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Double getAge() {
        return age;
    }

    public void setAge(Double age) {
        this.age = age;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Double getAgeLastShaven() {
        return ageLastShaven;
    }

    public void setAgeLastShaven(Double ageLastShaven) {
        this.ageLastShaven = ageLastShaven;
    }
}
