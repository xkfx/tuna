package xkfx.tools.tuna.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import xkfx.tools.tuna.model.Card;
import xkfx.tools.tuna.service.CardService;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/")
public class PageDataController {

    private final CardService cardService;

    @Autowired
    public PageDataController(CardService service) {
        cardService = service;
    }

    @GetMapping(value = "card_review_vo")
    public ResponseEntity<?> getCardReviewVo(Long targetId, Integer pageSize, Integer pageNo) {
        Map<String, Object> map = cardService.getCardReviewVo(targetId, pageSize, pageNo);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
