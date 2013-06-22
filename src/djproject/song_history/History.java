//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.06.22 um 07:58:22 PM CEST 
//


package djproject.song_history;

import java.util.ArrayList;
import java.util.List;
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
 *         &lt;element ref="{}song" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{}nowandnext"/>
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
    "song",
    "nowandnext"
})
@XmlRootElement(name = "history")
public class History {

    protected List<Song> song;
    @XmlElement(required = true)
    protected Nowandnext nowandnext;

    /**
     * Gets the value of the song property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the song property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSong().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Song }
     * 
     * 
     */
    public List<Song> getSong() {
        if (song == null) {
            song = new ArrayList<Song>();
        }
        return this.song;
    }

    /**
     * Ruft den Wert der nowandnext-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Nowandnext }
     *     
     */
    public Nowandnext getNowandnext() {
        return nowandnext;
    }

    /**
     * Legt den Wert der nowandnext-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Nowandnext }
     *     
     */
    public void setNowandnext(Nowandnext value) {
        this.nowandnext = value;
    }

}
