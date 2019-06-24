package xkfx.tools.tuna.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import xkfx.tools.tuna.model.Card;
import xkfx.tools.tuna.service.CardService;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLConnection;
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

    @DeleteMapping
    public ResponseEntity<?> removeCard(Long cardId) {
        cardService.removeCard(cardId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping("/xlsx")
    public void downloadCards(HttpServletResponse resp, Long targetId) {
        String location = cardService.generateXlsxFile(targetId);
        File file = new File(location);
        if (file.exists()) {
            // get the mimetype
            String mimeType = URLConnection.guessContentTypeFromName(file.getName());
            if (mimeType == null) {
                // unknown mimetype so set the mimetype to application/octet-stream
                mimeType = "application/octet-stream";
            }
            resp.setContentType(mimeType);

            /**
             * In a regular HTTP response, the Content-Disposition response header is a
             * header indicating if the content is expected to be displayed inline in the
             * browser, that is, as a Web page or as part of a Web page, or as an
             * attachment, that is downloaded and saved locally.
             *
             */

            /**
             * Here we have mentioned it to show inline
             * 如果可能的话 就在浏览器上显示
             */
            // response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));

            // Here we have mentioned it to show as attachment
            // 直接让用户下载
            resp.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
            resp.setContentLength((int) file.length());
            try (InputStream inputStream = new BufferedInputStream(new FileInputStream(file))) {
                FileCopyUtils.copy(inputStream, resp.getOutputStream());
            } catch (IOException e) {
                throw new RuntimeException("下载电子表格时出错", e);
            }
        }
    }
}
