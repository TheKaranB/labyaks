import javax.xml.bind.annotation.*;
import java.util.*;

@XmlRootElement( name = "herd")
@XmlAccessorType (XmlAccessType.FIELD)
public class Herd {

    //@XmlElementWrapper(name = "herd")
    @XmlElement(name = "labyak")
    public ArrayList<Labyak> labyaks = null;

    public List<Labyak> getLabyaks() {
        return labyaks;
    }

    public void setLabyaks(ArrayList<Labyak> labyaks) {
        this.labyaks = labyaks;
    }
}
