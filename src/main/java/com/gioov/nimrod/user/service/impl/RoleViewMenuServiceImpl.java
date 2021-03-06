package com.gioov.nimrod.user.service.impl;

import com.gioov.nimrod.user.entity.*;
import com.gioov.nimrod.user.mapper.*;
import com.gioov.nimrod.user.service.RoleAuthorityService;
import com.gioov.nimrod.user.service.RoleViewMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author godcheese [godcheese@outlook.com]
 * @date 2018-02-22
 */
@Service
public class RoleViewMenuServiceImpl implements RoleViewMenuService {
    @Autowired
    private RoleViewMenuMapper roleViewMenuMapper;
    @Autowired
    private ViewMenuMapper viewMenuMapper;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int grantAllByRoleIdAndViewMenuIdList(Long roleId, List<Long> viewMenuIdList) {
        // 最终被添加的 viewMenuIdList
        List<Long> viewMenuIdListResult = new ArrayList<>();
        ViewMenuEntity viewMenuEntity;
        for (Long viewMenuId : viewMenuIdList) {
            viewMenuEntity = viewMenuMapper.getOne(viewMenuId);
            if (viewMenuEntity != null) {
                viewMenuIdListResult.add(viewMenuId);
            }
        }
        // viewMenuIdList 全部写入数据库
        if (!viewMenuIdListResult.isEmpty()) {
            roleViewMenuMapper.insertAllByRoleIdAndViewMenuIdList(roleId, viewMenuIdListResult);
        }
        return viewMenuIdListResult.size();
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public int revokeAllByRoleIdAndViewMenuIdList(Long roleId, List<Long> viewMenuIdList) {
        // 最终被添加的 viewMenuIdList
        List<Long> viewMenuIdListResult = new ArrayList<>();
//        ViewMenuEntity viewMenuEntity;
//        for (Long viewMenuId : viewMenuIdList) {
//            viewMenuEntity = viewMenuMapper.getOne(viewMenuId);
//            if (viewMenuEntity != null) {
//                viewMenuIdListResult.add(viewMenuId);
//            }
//        }
        int result = 0;
        // viewMenuIdList 全部写入数据库
        if (!viewMenuIdList.isEmpty()) {
            result = roleViewMenuMapper.deleteAllByRoleIdAndViewMenuIdList(roleId, viewMenuIdList);
        }
        return result;
    }
}
