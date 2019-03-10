package xkfx.tools.tuna.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xkfx.tools.tuna.model.Card;

import javax.validation.ValidationException;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CardServiceTest {

    @Autowired
    private CardService cardService;

    @Test
    public void listCard() throws Exception {
        Boolean flag = false;
        try {
            List<Card> cards = cardService.listCard(null, 0, 3);
        } catch (ValidationException e) {
            flag = true;
            assertEquals("targetId不能为空", e.getMessage());
        }
        assertTrue(flag);
    }

    @Test
    public void saveCard() throws Exception {
        Card card = new Card();
        card.setTargetId(1000L);
        card.setFront("       ");
        card.setBack(String.valueOf(new Date()));
        Boolean flag = false;
        try {
            cardService.saveCard(card);
        } catch (ValidationException e) {
            flag = true;
            assertEquals("front不能为空", e.getMessage());
        }
        assertTrue(flag);
    }
}