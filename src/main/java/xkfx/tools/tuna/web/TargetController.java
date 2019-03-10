package xkfx.tools.tuna.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xkfx.tools.tuna.model.Target;
import xkfx.tools.tuna.service.TargetService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/targets")
public class TargetController {

    private final TargetService target;

    @Autowired
    public TargetController(TargetService service) {
        target = service;
    }

    @GetMapping
    public ResponseEntity<?> listTarget() {
        List<Target> list = target.listAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }
}
