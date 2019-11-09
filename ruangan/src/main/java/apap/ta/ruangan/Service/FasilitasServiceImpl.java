package apap.ta.ruangan.Service;

import apap.ta.ruangan.Model.FasilitasModel;
import apap.ta.ruangan.Repository.FasilitasDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FasilitasServiceImpl implements FasilitasService {

    @Autowired
    private FasilitasDb fasilitasDb;

    @Override
    public List<FasilitasModel> getFasilitasList(){
        return fasilitasDb.findAll();
    }
}
