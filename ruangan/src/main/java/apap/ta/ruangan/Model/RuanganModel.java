package apap.ta.ruangan.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "ruangan")
public class RuanganModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(max = 200)
    @Column(name = "nama",nullable = false)
    private String nama;

    @NotNull
    @Column(name = "kapasitas",nullable = false)
    private Long kapasitas;

    @OneToMany(mappedBy = "ruangan",fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private List<FasilitasModel> listFasilitas;

    @OneToMany(mappedBy = "ruanganModel",cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<PeminjamanRuanganModel> peminjamanRuanganList;

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

    public Long getKapasitas() {
        return kapasitas;
    }

    public void setKapasitas(Long kapasitas) {
        this.kapasitas = kapasitas;
    }

    public List<FasilitasModel> getListFasilitas() {
        return listFasilitas;
    }

    public void setListFasilitas(List<FasilitasModel> listFasilitas) {
        this.listFasilitas = listFasilitas;
    }

    public List<PeminjamanRuanganModel> getPeminjamanRuanganList() {
        return peminjamanRuanganList;
    }

    public void setPeminjamanRuanganList(List<PeminjamanRuanganModel> peminjamanRuanganList) {
        this.peminjamanRuanganList = peminjamanRuanganList;
    }
}
