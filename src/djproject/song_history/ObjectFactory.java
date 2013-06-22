//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.06.22 um 07:58:22 PM CEST 
//


package djproject.song_history;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the djproject.song_history package. 
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
    private final static QName _TimePlayedAt_QNAME = new QName("", "time_played_at");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: djproject.song_history
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link History }
     * 
     */
    public History createHistory() {
        return new History();
    }

    /**
     * Create an instance of {@link Song }
     * 
     */
    public Song createSong() {
        return new Song();
    }

    /**
     * Create an instance of {@link Nowandnext }
     * 
     */
    public Nowandnext createNowandnext() {
        return new Nowandnext();
    }

    /**
     * Create an instance of {@link Now }
     * 
     */
    public Now createNow() {
        return new Now();
    }

    /**
     * Create an instance of {@link Next }
     * 
     */
    public Next createNext() {
        return new Next();
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
     * Create an instance of {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "", name = "time_played_at")
    public JAXBElement<XMLGregorianCalendar> createTimePlayedAt(XMLGregorianCalendar value) {
        return new JAXBElement<XMLGregorianCalendar>(_TimePlayedAt_QNAME, XMLGregorianCalendar.class, null, value);
    }

}
