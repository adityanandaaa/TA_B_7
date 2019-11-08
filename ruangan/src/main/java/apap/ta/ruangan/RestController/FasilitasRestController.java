package apap.ta.ruangan.RestController;


import apap.ta.ruangan.Model.FasilitasModel;
import apap.ta.ruangan.Service.FasilitasRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1")
public class FasilitasRestController {


    @Autowired
    private FasilitasRestService fasilitasRestService;

    @GetMapping(value = "/fasilitas")
    private List<FasilitasModel> retrieveListFasilitias(){
        return fasilitasRestService.retrieveListFasilitasModel();
    }
}
