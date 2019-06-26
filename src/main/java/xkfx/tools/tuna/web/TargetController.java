package xkfx.tools.tuna.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xkfx.tools.tuna.model.Target;
import xkfx.tools.tuna.service.TargetService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/targets")
public class TargetController {

    private final TargetService targetService;

    @Autowired
    public TargetController(TargetService service) {
        targetService = service;
    }

    @GetMapping
    public ResponseEntity<?> listTarget() {
        List<Target> list = targetService.listAll();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveTarget(Target target) {
        targetService.saveTarget(target);
        return new ResponseEntity<>(target, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<?> removeCard(Long targetId) {
        targetService.removeTarget(targetId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
