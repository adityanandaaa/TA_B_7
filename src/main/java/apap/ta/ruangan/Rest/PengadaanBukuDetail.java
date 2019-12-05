package apap.ta.ruangan.Rest;
import apap.ta.ruangan.Model.UserModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PengadaanBukuDetail {

    @JsonProperty("judul")
    private String judul;

    @JsonProperty("pengarang")
    private String pengarang;

    @JsonProperty("penerbit")
    private String penerbit;

    @JsonProperty("jumlah")
    private int jumlah;

    @JsonProperty("harga")
    private int harga;

    //tanyain
    @JsonProperty("id")
    private String userId;

    public String getJudul() {
        return judul;
    }

    public void setJudul(String judul) {
        this.judul = judul;
    }

    public String getPengarang() {
        return pengarang;
    }

    public void setPengarang(String pengarang) {
        this.pengarang = pengarang;
    }

    public String getPenerbit() {
        return penerbit;
    }

    public void setPenerbit(String penerbit) {
        this.penerbit = penerbit;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public int getHarga() {
        return harga;
    }

    public void setHarga(int harga) {
        this.harga = harga;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
