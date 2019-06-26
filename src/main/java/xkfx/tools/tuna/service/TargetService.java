package xkfx.tools.tuna.service;

import xkfx.tools.tuna.model.Target;

import java.util.List;

public interface TargetService {

    List<Target> listAll();

    Target getByPrimaryKey(Long id);

    void saveTarget(Target target);

    void removeTarget(Long targetId);
}
