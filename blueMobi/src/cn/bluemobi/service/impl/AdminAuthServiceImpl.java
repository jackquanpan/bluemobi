package cn.bluemobi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bluemobi.dao.BaseDao;
import cn.bluemobi.service.AdminAuthService;

/**
 * 
 * @author dingyl
 *
 */
@Service
public class AdminAuthServiceImpl implements AdminAuthService {
	@Autowired
	private BaseDao dao;
	
	/**
	 * by dingyl 删除管理员所对应的权限关联信息
	 */
	@Override
	public int delete(long id) {
		String sql="delete from group_auth where id=?";
		int result=dao.executeByParams(sql, id);
		return result;
	}

}
