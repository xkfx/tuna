package xkfx.tools.tuna.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xkfx.tools.tuna.dao.CardMapper;
import xkfx.tools.tuna.dao.TargetMapper;
import xkfx.tools.tuna.model.Card;
import xkfx.tools.tuna.model.Target;
import xkfx.tools.tuna.service.CardService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CardServiceImpl implements CardService {

    private final CardMapper cardMapper;

    private final TargetMapper targetMapper;

    @Autowired
    public CardServiceImpl(CardMapper mapper, TargetMapper targetMapper) {
        cardMapper = mapper;
        this.targetMapper = targetMapper;
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

    @Override
    public void removeCard(Long cardId) {
        cardMapper.deleteByPrimaryKey(cardId);
    }

    @Override
    public Map<String, Object> getCardReviewVo(Long targetId, Integer pageSize, Integer pageNo) {
        List<Card> list = cardMapper.listCard(targetId, (pageNo - 1) * pageSize, pageSize);
        Integer count = cardMapper.getCountByTargetId(targetId);
        Map<String, Object> nodes = new HashMap<>();
        nodes.put("cardList", list);
        nodes.put("totalNumberOfPages", (count + pageSize - 1) / pageSize);
        return nodes;
    }

    @Override
    public String generateXlsxFile(Long targetId) {
        // 1. 获取数据库数据
        Target target = targetMapper.selectByPrimaryKey(targetId);
        if (target == null) throw new RuntimeException("target不存在"); // TODO
        List<Card> cards = cardMapper.listCard(targetId, 0, (int) Short.MAX_VALUE);
        // 2. 生成电子表格
        File dir = new File("xlsx");
        if (!dir.exists() && !dir.mkdir()) {
            // 短路运算，仅当!dir.exists()为真，即文件夹不存在
            // 时才调用dir.mkdir()，若创建失败则返回false，继而
            // !dir.mkdir()为真，直接return不再执行。
            throw new RuntimeException("文件夹创建失败");
        }
        final String location = dir.getAbsolutePath() + File.separator + target.getName() + ".xlsx";
        try (Workbook workbook = new XSSFWorkbook();
             FileOutputStream outputStream = new FileOutputStream(location)) {
            Sheet sheet = workbook.createSheet("Cards");
            sheet.setColumnWidth(0, 5000);
            sheet.setColumnWidth(1, 10000);
//        CellStyle style = workbook.createCellStyle();
//        style.setWrapText(true);
            for (int i = 0; i != cards.size(); ++i) {
                Row row = sheet.createRow(i);
                Cell cell = row.createCell(0);
                cell.setCellValue(cards.get(i).getFront());
                cell = row.createCell(1);
                cell.setCellValue(cards.get(i).getBack());
            }
//        cell.setCellStyle(style);
            workbook.write(outputStream);
            return location;
        } catch (IOException e) {
            throw new RuntimeException("生成电子表格时出错", e);
        }
    }
}
