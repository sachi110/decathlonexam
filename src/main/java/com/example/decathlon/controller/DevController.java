package com.example.decathlon.controller;

import com.example.decathlon.model.Developer;
import com.example.decathlon.model.Developers;
import com.example.decathlon.model.DevelopersDTO;
import com.example.decathlon.repro.DevListRepor;
import com.example.decathlon.serviec.DevSevice;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.validation.Valid;
import java.util.*;

@RestController
public class DevController {
    @Autowired
    DevSevice devSevice;
    @Autowired
    DevListRepor devListRepor;
    @Autowired(required = true)
    RestTemplate restTemplate;
    private final Logger logger = LoggerFactory.getLogger(DevController.class);


    @Value("${url}")
    private String smsurl;

    @RequestMapping("/getall")
    public List<Developers> getdevloperData() {
        List<Developers> ls = devSevice.findall();

        for (Developers ds : ls) {
            ls.get(0).setDevelopers((devListRepor.findByteam_id(ds.getId())));
        }

        return ls;
    }


    @PostMapping("/team")
    Optional<Developers> addUser(@Valid @RequestBody Developers dev) {
        Developers developers = new Developers();


        if (dev != null && dev.getDevelopers().size() > 0) {

            developers.setTeam_name(dev.getTeam_name());
            developers = devSevice.saveDev(developers);
            logger.info(developers.toString());
        }
        logger.info(dev.getDevelopers().toString());

        for (Developer ds : dev.getDevelopers()) {
            ds.setTeam_id(developers.getId());

            devListRepor.save(ds);
        }


        return devSevice.findById(developers.getId());

    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;

    }

    @RequestMapping("/{team_id}")
    public String getAlert(@PathVariable String team_id) {

        String scont = devSevice.findByIdforalert(Long.parseLong(team_id));
        logger.info(scont);
        Map<String, String> params = new HashMap<String, String>();
        params.put("phone_number", scont);

        String result = restTemplate.getForObject(smsurl, String.class, params);
        logger.info(result);

        return result;

    }

}
