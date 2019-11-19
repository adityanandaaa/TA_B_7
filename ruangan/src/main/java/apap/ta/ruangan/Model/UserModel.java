package apap.ta.ruangan.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user")
public class UserModel  implements Serializable {

    @Id
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;

    @NotNull
    @Size(max = 50)
    @Column(name ="username",nullable = false)
    private String username;

    @NotNull
    @Lob
    @Column(name = "password",nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="id_role",referencedColumnName = "id",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private RoleModel role;

    @OneToMany(mappedBy = "userModelPeminjam",cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<PeminjamanRuanganModel> peminjamanRuanganPeminjamList;

    @OneToMany(mappedBy = "userModelPenyetuju",cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<PeminjamanRuanganModel> peminjamanRuanganPenyetujuList;


    @OneToMany(mappedBy = "userModel",cascade = CascadeType.REMOVE)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<PengadaanFasilitasModel> pengadaanFasilitasList;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleModel getRole() {
        return role;
    }

    public void setRole(RoleModel role) {
        this.role = role;
    }

    public List<PeminjamanRuanganModel> getPeminjamanRuanganPeminjamList() {
        return peminjamanRuanganPeminjamList;
    }

    public void setPeminjamanRuanganPeminjamList(List<PeminjamanRuanganModel> peminjamanRuanganPeminjamList) {
        this.peminjamanRuanganPeminjamList = peminjamanRuanganPeminjamList;
    }

    public List<PeminjamanRuanganModel> getPeminjamanRuanganPenyetujuList() {
        return peminjamanRuanganPenyetujuList;
    }

    public void setPeminjamanRuanganPenyetujuList(List<PeminjamanRuanganModel> peminjamanRuanganPenyetujuList) {
        this.peminjamanRuanganPenyetujuList = peminjamanRuanganPenyetujuList;
    }

    public List<PengadaanFasilitasModel> getPengadaanFasilitasList() {
        return pengadaanFasilitasList;
    }

    public void setPengadaanFasilitasList(List<PengadaanFasilitasModel> pengadaanFasilitasList) {
        this.pengadaanFasilitasList = pengadaanFasilitasList;
    }


}
