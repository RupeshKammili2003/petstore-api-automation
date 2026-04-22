package payLoads;

import javax.xml.bind.annotation.XmlRootElement;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

@XmlRootElement(name = "Pet")               // creates root <Pet> tag
public class PetPoJoXML {

    private String name;
    private List<String> photoUrls;
    private long id;

    // Default constructor — mandatory for JAXB
    public PetPoJoXML() {}

    public PetPoJoXML(String name, List<String> photoUrls, long id) {
        this.name = name;
        this.photoUrls = photoUrls;
        this.id = id;
    }

    @XmlElement(name = "id")                // creates <id> element
    public long getId() {
        return id;
    }
    public void setId(long id) {            // no annotation on setter
        this.id = id;
    }

    @XmlElement(name = "name")              // creates <name> element
    public String getName() {
        return name;
    }
    public void setName(String name) {      // no annotation on setter
        this.name = name;
    }

    @XmlElementWrapper(name = "photoUrls") // creates wrapper <photoUrls> tag
    @XmlElement(name = "photoUrl")         // each item becomes <photoUrl>
    public List<String> getPhotoUrls() {
        return photoUrls;
    }
    public void setPhotoUrls(List<String> photoUrls) {  // no annotation on setter
        this.photoUrls = photoUrls;
    }
}