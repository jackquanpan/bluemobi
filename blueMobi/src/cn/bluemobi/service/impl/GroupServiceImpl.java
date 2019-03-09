package cn.bluemobi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bluemobi.dao.BaseDao;
import cn.bluemobi.entity.Group;
import cn.bluemobi.entity.GroupAuth;
import cn.bluemobi.service.GroupService;
import cn.bluemobi.util.text.TextHelper;

/**
 * 
 * @author dingyl
 * 
 */
@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    private BaseDao dao;

    /**
     * by liyl 获取所有分组信息
     */
    @Override
    public List<Group> getAllGroups() {
        String sql = "select id,group_name as groupName,update_date as updateDate from `group`";
        List<Group> list = dao.findForList(sql, Group.class);
        return list;
    }

    @Override
    public int isExist(String groupName) {
        return dao.findForInt("SELECT COUNT(*) FROM  `group` WHERE group_name = ? ", groupName);
    }

    @Override
    public int isExist(String groupName, Long id) {
        return dao.findForInt("SELECT COUNT(*) FROM  `group` WHERE   group_name = ? AND id <> ?", groupName);
    }

    @Override
    public int deleteGroup(String id) {
        // 删除分组同时也要删除相应的角色权限
        dao.executeByParams("DELETE FROM group_auth WHERE group_id = ?", id);
        return dao.executeByParams("delete from `group` where id = ?", id);
    }

    @Override
    public Group findGroupById(Long id) {
        return dao.findForObject("select id,group_name AS groupName from `group` where id = ?", Group.class, id);
    }

    @Override
    public String getGroupAuthCode(Long id) {
        GroupAuth gt = dao.findForObject(
                " SELECT  GROUP_CONCAT(auth_id) AS authCode FROM group_auth WHERE group_id = ?  ", GroupAuth.class, id);
        return gt.getAuthCode();
    }

    @Override
    public int addGroup(String groupName, String authCode) {
        int r = 0;
        String[] auth = new String[0];

        if (!TextHelper.isNullOrEmpty(authCode)) {
            auth = authCode.split(",");
        }
        String sql1 = "INSERT INTO `group` SET group_name = ? , create_date=NOW(), update_date=NOW()";
        int result = 0;
        Long id = 0L;
        result = dao.executeByParams(sql1, groupName);
        if (result > 0) {
            id = dao.findForLong("select id from `group` where group_name = ? ", groupName);
        }
        for (int i = 0; i < auth.length; i++) {
            r = dao.executeByParams("INSERT INTO group_auth SET auth_id = ?, group_id = ?;", auth[i], id);
            if (r == 0)
                return r;
        }
        return r;
    }

    @Override
    public int updateGroup(String groupName, String authCode, Long id) {
        int r = 0;
        String[] auth = new String[0];
        if (!TextHelper.isNullOrEmpty(authCode)) {
            auth = authCode.split(",");
        }
        String sql1 = "update `group` SET group_name = ? ,  update_date=NOW() where id = ?";
        int result = 0;
        result = dao.executeByParams(sql1, groupName, id);
        if (result > 0) {
            dao.executeByParams("DELETE FROM group_auth WHERE group_id = ?", id);
        }
        for (int i = 0; i < auth.length; i++) {
            r = dao.executeByParams("INSERT INTO group_auth SET auth_id = ?, group_id = ?;", auth[i], id);
            if (r == 0)
                return r;
        }
        return r;
    }

}
