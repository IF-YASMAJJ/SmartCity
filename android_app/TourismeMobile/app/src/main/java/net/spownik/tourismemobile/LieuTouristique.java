package net.spownik.tourismemobile;
/**
 * Created by yukaiwang on 30/04/15.
 */

import java.util.HashMap;
import java.util.Map;

public class LieuTouristique {

    private Integer Id;
    private String nom;
    private String type;
    private String typeDetail;
    private String classement;
    private String adresse;
    private String codepostal;
    private String commune;
    private String telephone;
    private String fax;
    private String email;
    private String siteweb;
    private String facebook;
    private String ouverture;
    private String tarifsenclair;
    private String tarifsmin;
    private String tarifsmax;
    private String producteur;
    private Double latitude;
    private Double longitude;
    private Integer abscisses;
    private Integer ordonnees;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    private Double distanceFromMe;

    public Double getDistanceFromMe() {
        return distanceFromMe;
    }

    public void setDistanceFromMe(Double distanceFromMe) {
        this.distanceFromMe = distanceFromMe;
    }

    /**
     *
     * @return
     * The Id
     */
    public Integer getId() {
        return Id;
    }

    /**
     *
     * @param Id
     * The Id
     */
    public void setId(Integer Id) {
        this.Id = Id;
    }

    /**
     *
     * @return
     * The nom
     */
    public String getNom() {
        return nom;
    }

    /**
     *
     * @param nom
     * The nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

    /**
     *
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     * The typeDetail
     */
    public String getTypeDetail() {
        return typeDetail;
    }

    /**
     *
     * @param typeDetail
     * The type_detail
     */
    public void setTypeDetail(String typeDetail) {
        this.typeDetail = typeDetail;
    }

    /**
     *
     * @return
     * The classement
     */
    public String getClassement() {
        return classement;
    }

    /**
     *
     * @param classement
     * The classement
     */
    public void setClassement(String classement) {
        this.classement = classement;
    }

    /**
     *
     * @return
     * The adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     *
     * @param adresse
     * The adresse
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    /**
     *
     * @return
     * The codepostal
     */
    public String getCodepostal() {
        return codepostal;
    }

    /**
     *
     * @param codepostal
     * The codepostal
     */
    public void setCodepostal(String codepostal) {
        this.codepostal = codepostal;
    }

    /**
     *
     * @return
     * The commune
     */
    public String getCommune() {
        return commune;
    }

    /**
     *
     * @param commune
     * The commune
     */
    public void setCommune(String commune) {
        this.commune = commune;
    }

    /**
     *
     * @return
     * The telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     *
     * @param telephone
     * The telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     *
     * @return
     * The fax
     */
    public String getFax() {
        return fax;
    }

    /**
     *
     * @param fax
     * The fax
     */
    public void setFax(String fax) {
        this.fax = fax;
    }

    /**
     *
     * @return
     * The email
     */
    public String getEmail() {
        return email;
    }

    /**
     *
     * @param email
     * The email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     *
     * @return
     * The siteweb
     */
    public String getSiteweb() {
        return siteweb;
    }

    /**
     *
     * @param siteweb
     * The siteweb
     */
    public void setSiteweb(String siteweb) {
        this.siteweb = siteweb;
    }

    /**
     *
     * @return
     * The facebook
     */
    public String getFacebook() {
        return facebook;
    }

    /**
     *
     * @param facebook
     * The facebook
     */
    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }

    /**
     *
     * @return
     * The ouverture
     */
    public String getOuverture() {
        return ouverture;
    }

    /**
     *
     * @param ouverture
     * The ouverture
     */
    public void setOuverture(String ouverture) {
        this.ouverture = ouverture;
    }

    /**
     *
     * @return
     * The tarifsenclair
     */
    public String getTarifsenclair() {
        return tarifsenclair;
    }

    /**
     *
     * @param tarifsenclair
     * The tarifsenclair
     */
    public void setTarifsenclair(String tarifsenclair) {
        this.tarifsenclair = tarifsenclair;
    }

    /**
     *
     * @return
     * The tarifsmin
     */
    public String getTarifsmin() {
        return tarifsmin;
    }

    /**
     *
     * @param tarifsmin
     * The tarifsmin
     */
    public void setTarifsmin(String tarifsmin) {
        this.tarifsmin = tarifsmin;
    }

    /**
     *
     * @return
     * The tarifsmax
     */
    public String getTarifsmax() {
        return tarifsmax;
    }

    /**
     *
     * @param tarifsmax
     * The tarifsmax
     */
    public void setTarifsmax(String tarifsmax) {
        this.tarifsmax = tarifsmax;
    }

    /**
     *
     * @return
     * The producteur
     */
    public String getProducteur() {
        return producteur;
    }

    /**
     *
     * @param producteur
     * The producteur
     */
    public void setProducteur(String producteur) {
        this.producteur = producteur;
    }

    /**
     *
     * @return
     * The latitude
     */
    public Double getLatitude() {
        return latitude;
    }

    /**
     *
     * @param latitude
     * The latitude
     */
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    /**
     *
     * @return
     * The longitude
     */
    public Double getLongitude() {
        return longitude;
    }

    /**
     *
     * @param longitude
     * The longitude
     */
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    /**
     *
     * @return
     * The abscisses
     */
    public Integer getAbscisses() {
        return abscisses;
    }

    /**
     *
     * @param abscisses
     * The abscisses
     */
    public void setAbscisses(Integer abscisses) {
        this.abscisses = abscisses;
    }

    /**
     *
     * @return
     * The ordonnees
     */
    public Integer getOrdonnees() {
        return ordonnees;
    }

    /**
     *
     * @param ordonnees
     * The ordonnees
     */
    public void setOrdonnees(Integer ordonnees) {
        this.ordonnees = ordonnees;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}