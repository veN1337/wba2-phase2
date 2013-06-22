//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.06.22 um 07:58:22 PM CEST 
//


package djproject.song_history;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für anonymous complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}song"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "song"
})
@XmlRootElement(name = "next")
public class Next {

    @XmlElement(required = true)
    protected Song song;

    /**
     * Ruft den Wert der song-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Song }
     *     
     */
    public Song getSong() {
        return song;
    }

    /**
     * Legt den Wert der song-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Song }
     *     
     */
    public void setSong(Song value) {
        this.song = value;
    }

}
