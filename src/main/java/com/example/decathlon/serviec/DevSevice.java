package com.example.decathlon.serviec;

import com.example.decathlon.model.Developer;
import com.example.decathlon.model.Developers;
import com.example.decathlon.repro.DevListRepor;
import com.example.decathlon.repro.DevRepro;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class DevSevice {
    @Autowired
    DevRepro devRepro;
    @Autowired
    DevListRepor devListRepor;
    @Value("url")
    private String smsurl;
    private final Logger logger = LoggerFactory.getLogger(DevSevice.class);


    public List<Developers> findall() {
        return devRepro.findAll();
    }

    public Developers saveDev(Developers dev) {
        logger.info("inside the save dev method");
        devRepro.save(dev);
        return dev;
    }

    public String findByIdforalert(long team_id) {
        List<Developer> ls = devListRepor.findByteam_id(team_id);
        logger.info("data got " + ls);

        Random rd = new Random();
        int index = rd.nextInt(ls.size());
        logger.info("we selected the  index" + index);
        logger.info("we selected the  index" + ls.get(index));


        return ls.get(index).getPhone_number().toString();
    }

    public Optional<Developers> findById(long team_id) {


        return devRepro.findById(team_id);
    }

}
