package xkfx.tools.tuna.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import xkfx.tools.tuna.model.Card;
import xkfx.tools.tuna.service.CardService;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping(value = "api/v1/cards")
public class CardController {

    private final CardService cardService;

    @Autowired
    public CardController(CardService service) {
        cardService = service;
    }

    @GetMapping
    public ResponseEntity<?> listCard(Long targetId, Integer offset, Integer limit) {
        List<Card> list = cardService.listCard(targetId, offset, limit);
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveCard(Card card) {
        cardService.saveCard(card);
        return new ResponseEntity<>(card, HttpStatus.CREATED);
    }
}
