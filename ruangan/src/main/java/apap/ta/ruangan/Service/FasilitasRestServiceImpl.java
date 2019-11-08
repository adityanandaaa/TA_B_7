package apap.ta.ruangan.Service;

import apap.ta.ruangan.Model.FasilitasModel;
import apap.ta.ruangan.Repository.FasilitasDb;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class FasilitasRestServiceImpl implements FasilitasRestService {

    @Autowired
    private FasilitasDb fasilitasDb;

    @Override
    public List<FasilitasModel> retrieveListFasilitasModel(){
        return fasilitasDb.findAll();
    }
}

