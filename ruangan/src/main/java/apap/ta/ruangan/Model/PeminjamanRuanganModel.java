package apap.ta.ruangan.Model;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "peminjamanruangan")
public class PeminjamanRuanganModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 200)
    @Column(name = "waktu_mulai",nullable = false)
    private String waktu_mulai;

    @NotNull
    @Size(max = 200)
    @Column(name = "waktu_selesai",nullable = false)
    private String waktu_selesai;

    @NotNull
    @Column(name = "tanggal_mulai", nullable = false)
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private Date tanggal_mulai;

    @NotNull
    @Column(name = "tanggal_selesai", nullable = false)
    @DateTimeFormat(iso=DateTimeFormat.ISO.DATE)
    private Date tanggal_selesai;

    @NotNull
    @Size(max = 200)
    @Column(name = "tujuan",nullable = false)
    private String tujuan;

    @NotNull
    @Size(max = 200)
    @Column(name = "keterangan",nullable = false)
    private String keterangan;

    @NotNull
    @Column(name = "jumlah_peserta",nullable = false)
    private Long jumlah_peserta;


    @Column(name = "is_disetujui",nullable = false)
    private Boolean is_disetujui;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uuid_user_peminjam")
    @OnDelete(action = OnDeleteAction.CASCADE)
    UserModel userModelPeminjam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "uuid_user_penyetuju")
    @OnDelete(action = OnDeleteAction.CASCADE)
    UserModel userModelPenyetuju;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_ruang")
    @OnDelete(action = OnDeleteAction.CASCADE)
    RuanganModel ruanganModel;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWaktu_mulai() {
        return waktu_mulai;
    }

    public void setWaktu_mulai(String waktu_mulai) {
        this.waktu_mulai = waktu_mulai;
    }

    public String getWaktu_selesai() {
        return waktu_selesai;
    }

    public void setWaktu_selesai(String waktu_selesai) {
        this.waktu_selesai = waktu_selesai;
    }

    public Date getTanggal_mulai() {
        return tanggal_mulai;
    }

    public void setTanggal_mulai(Date tanggal_mulai) {
        this.tanggal_mulai = tanggal_mulai;
    }

    public Date getTanggal_selesai() {
        return tanggal_selesai;
    }

    public void setTanggal_selesai(Date tanggal_selesai) {
        this.tanggal_selesai = tanggal_selesai;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public Long getJumlah_peserta() {
        return jumlah_peserta;
    }

    public void setJumlah_peserta(Long jumlah_peserta) {
        this.jumlah_peserta = jumlah_peserta;
    }

    public Boolean getIs_disetujui() {
        return is_disetujui;
    }

    public void setIs_disetujui(Boolean is_disetujui) {
        this.is_disetujui = is_disetujui;
    }

    public UserModel getUserModelPeminjam() {
        return userModelPeminjam;
    }

    public void setUserModelPeminjam(UserModel userModelPeminjam) {
        this.userModelPeminjam = userModelPeminjam;
    }

    public UserModel getUserModelPenyetuju() {
        return userModelPenyetuju;
    }

    public void setUserModelPenyetuju(UserModel userModelPenyetuju) {
        this.userModelPenyetuju = userModelPenyetuju;
    }

    public RuanganModel getRuanganModel() {
        return ruanganModel;
    }

    public void setRuanganModel(RuanganModel ruanganModel) {
        this.ruanganModel = ruanganModel;
    }
}
