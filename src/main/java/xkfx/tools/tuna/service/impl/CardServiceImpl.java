package xkfx.tools.tuna.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xkfx.tools.tuna.dao.CardMapper;
import xkfx.tools.tuna.model.Card;
import xkfx.tools.tuna.service.CardService;

import java.util.List;

@Service
public class CardServiceImpl implements CardService {

    @Autowired
    private final CardMapper cardMapper;

    @Autowired
    public CardServiceImpl(CardMapper mapper) {
        cardMapper = mapper;
    }

    @Override
    public List<Card> listCard(Long targetId, Integer offset, Integer limit) {
        return cardMapper.listCard(targetId, offset, limit);
    }

    @Override
    public void saveCard(Card card) {
        // front不会为空，因为在接口处检查过了。
        card.setFront(card.getFront().trim());
        if (StringUtils.isBlank(card.getBack())) card.setBack(null);
        if (card.getBack() != null) {
            card.setBack(card.getBack().trim());
        }
        cardMapper.insertSelective(card);
    }
}
