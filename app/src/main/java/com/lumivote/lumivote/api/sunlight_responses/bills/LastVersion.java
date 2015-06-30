
package com.lumivote.lumivote.api.sunlight_responses.bills;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class LastVersion {

    @SerializedName("version_code")
    @Expose
    private String versionCode;
    @SerializedName("issued_on")
    @Expose
    private String issuedOn;
    @SerializedName("version_name")
    @Expose
    private String versionName;
    @SerializedName("bill_version_id")
    @Expose
    private String billVersionId;
    @Expose
    private Urls_ urls;
    @Expose
    private Integer pages;

    /**
     * 
     * @return
     *     The versionCode
     */
    public String getVersionCode() {
        return versionCode;
    }

    /**
     * 
     * @param versionCode
     *     The version_code
     */
    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    /**
     * 
     * @return
     *     The issuedOn
     */
    public String getIssuedOn() {
        return issuedOn;
    }

    /**
     * 
     * @param issuedOn
     *     The issued_on
     */
    public void setIssuedOn(String issuedOn) {
        this.issuedOn = issuedOn;
    }

    /**
     * 
     * @return
     *     The versionName
     */
    public String getVersionName() {
        return versionName;
    }

    /**
     * 
     * @param versionName
     *     The version_name
     */
    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    /**
     * 
     * @return
     *     The billVersionId
     */
    public String getBillVersionId() {
        return billVersionId;
    }

    /**
     * 
     * @param billVersionId
     *     The bill_version_id
     */
    public void setBillVersionId(String billVersionId) {
        this.billVersionId = billVersionId;
    }

    /**
     * 
     * @return
     *     The urls
     */
    public Urls_ getUrls() {
        return urls;
    }

    /**
     * 
     * @param urls
     *     The urls
     */
    public void setUrls(Urls_ urls) {
        this.urls = urls;
    }

    /**
     * 
     * @return
     *     The pages
     */
    public Integer getPages() {
        return pages;
    }

    /**
     * 
     * @param pages
     *     The pages
     */
    public void setPages(Integer pages) {
        this.pages = pages;
    }

}
