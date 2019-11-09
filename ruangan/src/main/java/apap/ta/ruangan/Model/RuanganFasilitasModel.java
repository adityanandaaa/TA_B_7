package apap.ta.ruangan.Model;


import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "ruanganfasilitas")
public class RuanganFasilitasModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Column(name = "jumlah_fasilitas",nullable = false)
    private Long jumlah_fasilitas;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_fasilitas")
    @OnDelete(action = OnDeleteAction.CASCADE)
    FasilitasModel fasilitasModel;

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

    public Long getJumlah_fasilitas() {
        return jumlah_fasilitas;
    }

    public void setJumlah_fasilitas(Long jumlah_fasilitas) {
        this.jumlah_fasilitas = jumlah_fasilitas;
    }

    public FasilitasModel getFasilitasModel() {
        return fasilitasModel;
    }

    public void setFasilitasModel(FasilitasModel fasilitasModel) {
        this.fasilitasModel = fasilitasModel;
    }

    public RuanganModel getRuanganModel() {
        return ruanganModel;
    }

    public void setRuanganModel(RuanganModel ruanganModel) {
        this.ruanganModel = ruanganModel;
    }
}
