package xkfx.tools.tuna.service;

import org.hibernate.validator.constraints.Range;
import xkfx.tools.tuna.model.Card;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;


public interface CardService {

    List<Card> listCard(@NotNull(message = "targetId不能为空")
                        @Range(min = 1000L, message = "targetId不规范") Long targetId,
                        @NotNull(message = "offset不能为空")
                        @Range(min = 0, max = Short.MAX_VALUE, message = "offset不规范") Integer offset,
                        @NotNull(message = "limit不能为空")
                        @Range(min = 1, max = Short.MAX_VALUE, message = "limit不规范") Integer limit);

    void saveCard(@Valid Card card);

    void removeCard(@Range(min = 1000L, message = "cardId不规范") Long cardId);

    Map<String, Object> getCardReviewVo(@NotNull(message = "targetId不能为空")
                                        @Range(min = 1000L, message = "targetId不规范") Long targetId,
                                        @NotNull(message = "pageSize不能为空")
                                        @Range(min = 1, max = Short.MAX_VALUE, message = "pageSize不规范") Integer pageSize,
                                        @NotNull(message = "pageNo不能为空")
                                        @Range(min = 1, max = Short.MAX_VALUE, message = "pageNo不规范") Integer pageNo);
}
