//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.06.06 um 01:34:26 PM CEST 
//


package djproject.wishes;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the djproject.wishes package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Id_QNAME = new QName("", "id");
    private final static QName _SongId_QNAME = new QName("", "song_id");
    private final static QName _Count_QNAME = new QName("", "count");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: djproject.wishes
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Wishes }
     * 
     */
    public Wishes createWishes() {
        return new Wishes();
    }

    /**
     * Create an instance of {@link Wish }
     * 
     */
    public Wish createWish() {
        return new Wish();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "id")
    public JAXBElement<Integer> createId(Integer value) {
        return new JAXBElement<Integer>(_Id_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "song_id")
    public JAXBElement<Integer> createSongId(Integer value) {
        return new JAXBElement<Integer>(_SongId_QNAME, Integer.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Integer }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "count")
    public JAXBElement<Integer> createCount(Integer value) {
        return new JAXBElement<Integer>(_Count_QNAME, Integer.class, null, value);
    }

}
