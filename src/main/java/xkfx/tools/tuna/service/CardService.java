package xkfx.tools.tuna.service;

import org.hibernate.validator.constraints.Range;
import xkfx.tools.tuna.model.Card;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;


public interface CardService {

    List<Card> listCard(@NotNull(message = "targetId不能为空")
                        @Range(min = 1000L, message = "targetId不规范") Long targetId,
                        @NotNull(message = "offset不能为空")
                        @Range(min = 0, max = Short.MAX_VALUE, message = "offset不规范") Integer offset,
                        @NotNull(message = "limit不能为空")
                        @Range(min = 1, max = Short.MAX_VALUE, message = "limit不规范") Integer limit);

    void saveCard(@Valid Card card);
}
