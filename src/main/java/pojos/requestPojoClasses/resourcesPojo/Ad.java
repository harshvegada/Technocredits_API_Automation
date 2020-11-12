
package pojos.requestPojoClasses.resourcesPojo;

import com.fasterxml.jackson.annotation.JsonProperty;


public class Ad {

    @JsonProperty("company")
    private String company;
    @JsonProperty("url")
    private String url;
    @JsonProperty("text")
    private String text;

    @JsonProperty("company")
    public String getCompany() {
        return company;
    }

    @JsonProperty("company")
    public void setCompany(String company) {
        this.company = company;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("text")
    public String getText() {
        return text;
    }

    @JsonProperty("text")
    public void setText(String text) {
        this.text = text;
    }

}
