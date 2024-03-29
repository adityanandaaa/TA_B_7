package apap.ta.ruangan.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "fasilitas")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class FasilitasModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 200)
    @Column(name = "nama",nullable = false)
    private String nama;

    @NotNull
    @Column(name = "jumlah",nullable = false)
    private Long jumlah;

    @OneToMany(mappedBy = "fasilitasModel",cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<RuanganFasilitasModel> listRuangan;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public Long getJumlah() {
        return jumlah;
    }

    public void setJumlah(Long jumlah) {
        this.jumlah = jumlah;
    }

    public List<RuanganFasilitasModel> getListRuangan() {
        return listRuangan;
    }

    public void setListRuangan(List<RuanganFasilitasModel> listRuangan) {
        this.listRuangan = listRuangan;
    }
}


