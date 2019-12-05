package apap.ta.ruangan.Rest;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class PengajuanSuratResponse {
    private String status;

    private String message;

    private PengajuanSurat result;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PengajuanSurat getResult() {
        return result;
    }

    public void setResult(PengajuanSurat result) {
        this.result = result;
    }

}
