package xkfx.tools.tuna.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import xkfx.tools.tuna.model.Card;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CardMapperTest {

    @Autowired
    private CardMapper mapper;

    @Test
    public void listCard() throws Exception {
        Card card = new Card();
        card.setTargetId(1000L);
        card.setFront("this is a test data.");
        card.setBack(String.valueOf(new Date()));
        mapper.insert(card);
        List<Card> list = mapper.listCard(1000L, 0, 2);
        assertEquals(2, list.size());
        assertNotNull(card.getId());
    }

}