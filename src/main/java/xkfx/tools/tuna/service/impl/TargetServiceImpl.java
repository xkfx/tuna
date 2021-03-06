package xkfx.tools.tuna.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xkfx.tools.tuna.dao.TargetMapper;
import xkfx.tools.tuna.model.Target;
import xkfx.tools.tuna.service.TargetService;

import java.util.List;

@Service
public class TargetServiceImpl implements TargetService {

    private final TargetMapper targetMapper;

    @Autowired
    public TargetServiceImpl(TargetMapper mapper) {
        targetMapper = mapper;
    }

    @Override
    public List<Target> listAll() {
        return targetMapper.selectAll();
    }

    @Override
    public Target getByPrimaryKey(Long id) {
        return targetMapper.selectByPrimaryKey(id);
    }

    @Override
    public void saveTarget(Target target) {
        targetMapper.insertSelective(target);
    }

    @Override
    public void removeTarget(Long targetId) {
        targetMapper.deleteByPrimaryKey(targetId);
    }
}
