package apap.ta.ruangan.Rest;

import java.util.Random;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GuruResponse{
    @JsonProperty("idUser")
    private String idUser;
    @JsonProperty("nig")
    private String nig;
    @JsonProperty("nama")
    private String nama;
    @JsonProperty("tempatLahir")
    private String tempat_lahir;
    @JsonProperty("tanggalLahir")
    private String tanggal_lahir;
    @JsonProperty("alamat")
    private String alamat;
    @JsonProperty("telepon")
    private String telepon;

    public String getIdUser() {
        return this.idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNig() {
        return this.nig;
    }

    public void setNig(String nig) {
        this.nig = nig;
    }

    public String getNama() {
        return this.nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getTanggal_lahir() {
        return this.tanggal_lahir;
    }

    public void setTanggal_lahir(String tanggal_lahir) {
        this.tanggal_lahir = tanggal_lahir;
    }

    public String getTempat_lahir() {
        return this.tempat_lahir;
    }

    public void setTempat_lahir(String tempat_lahir) {
        this.tempat_lahir = tempat_lahir;
    }

    public String getAlamat() {
        return this.alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTelepon() {
        return this.telepon;
    }

    public void setTelepon(String telepon) {
        this.telepon = telepon;
    }

    public void createNig() {
        final String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final int N = alphabet.length();
        final String number = "0123456789";
        final int M = number.length();
        Random r = new Random();
        String hurufRandom = "";
        String angkaRandom = "";
        for (int i = 0; i < 2; i++) {
            hurufRandom += alphabet.charAt(r.nextInt(N));
        }
        for(int i = 0; i < 3; i++){
            angkaRandom += number.charAt(r.nextInt(M));
        }
        String strDate = getTanggal_lahir();
        String strDateNip = strDate.substring(8, 10) + strDate.substring(5, 7) + strDate.substring(0, 4);
        String nig = "G" + strDateNip + hurufRandom + angkaRandom + idUser;
        this.setNig(nig);
    }
}