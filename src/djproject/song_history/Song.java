//
// Diese Datei wurde mit der JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.6 generiert 
// Siehe <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Änderungen an dieser Datei gehen bei einer Neukompilierung des Quellschemas verloren. 
// Generiert: 2013.06.19 um 04:20:02 PM CEST 
//


package djproject.song_history;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element ref="{}id"/>
 *         &lt;element ref="{}song_id"/>
 *         &lt;element ref="{}time_played_at"/>
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
    "id",
    "songId",
    "timePlayedAt"
})
@XmlRootElement(name = "song")
public class Song {

    protected int id;
    @XmlElement(name = "song_id")
    protected int songId;
    @XmlElement(name = "time_played_at", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar timePlayedAt;

    /**
     * Ruft den Wert der id-Eigenschaft ab.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Legt den Wert der id-Eigenschaft fest.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Ruft den Wert der songId-Eigenschaft ab.
     * 
     */
    public int getSongId() {
        return songId;
    }

    /**
     * Legt den Wert der songId-Eigenschaft fest.
     * 
     */
    public void setSongId(int value) {
        this.songId = value;
    }

    /**
     * Ruft den Wert der timePlayedAt-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTimePlayedAt() {
        return timePlayedAt;
    }

    /**
     * Legt den Wert der timePlayedAt-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTimePlayedAt(XMLGregorianCalendar value) {
        this.timePlayedAt = value;
    }

}
