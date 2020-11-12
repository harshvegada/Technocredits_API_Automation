package pojos.responsePojos.error;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Error {

	@JsonProperty("error")
	private String error;

	@JsonProperty("error")
	public String getError() {
		return error;
	}

	@JsonProperty("error")
	public void setError(String error) {
		this.error = error;
	}

}