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
 *         &lt;element ref="{}now"/>
 *         &lt;element ref="{}next"/>
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
    "now",
    "next"
})
@XmlRootElement(name = "nowandnext")
public class Nowandnext {

    @XmlElement(required = true)
    protected Now now;
    @XmlElement(required = true)
    protected Next next;

    /**
     * Ruft den Wert der now-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Now }
     *     
     */
    public Now getNow() {
        return now;
    }

    /**
     * Legt den Wert der now-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Now }
     *     
     */
    public void setNow(Now value) {
        this.now = value;
    }

    /**
     * Ruft den Wert der next-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Next }
     *     
     */
    public Next getNext() {
        return next;
    }

    /**
     * Legt den Wert der next-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Next }
     *     
     */
    public void setNext(Next value) {
        this.next = value;
    }

}
