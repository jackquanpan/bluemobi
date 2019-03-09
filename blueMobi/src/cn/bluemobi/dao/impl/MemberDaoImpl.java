/**
 * 文件名  ：MemberDaoImpl.java
 * 
 * 描        述  ：
 * 修    改 人 ：huad
 * 修改时间  ：{date}
 * 跟踪单号  ： 跟踪单号
 *
 */
package cn.bluemobi.dao.impl;

import org.springframework.stereotype.Repository;

import cn.bluemobi.dao.MemberDao;

/**
 * 一句话功能简述 功能详细描述
 * 
 * @author huad version [版本号，2015年4月10日] see [相关类/方法] since [产品/模块版本]
 */
@Repository
public class MemberDaoImpl implements MemberDao {
	// @Autowired
	// private JdbcTemplate jdbcTemplate;
	//
	// /**
	// * 检查用户名和密码是否正确
	// */
	// @Override
	// public String checkMember(Member member)
	// {
	// StringBuilder sb = new StringBuilder();
	//
	// sb.append("select m.id from member m where 1 = 1");
	//
	// if (StringUtils.isNotBlank(member.getName()))
	// {
	// sb.append(" and m.name = '" + member.getName() + "' ");
	// }
	//
	// if (StringUtils.isNotBlank(member.getPhone()))
	// {
	// sb.append(" and m.phone = '" + member.getPhone() + "' ");
	// }
	//
	// sb.append(" and m.password = '" + member.getPassword() + "' ");
	//
	// return jdbcTemplate.queryForObject(sb.toString(), String.class);
	// }
	//
	// /**
	// * 更新用户密码
	// */
	// @Override
	// public boolean updateMemberPwd(Long id, String password)
	// {
	// String sql = "update member m set m.password = '" + password +
	// "' where m.id = '" + id + "'";
	//
	// int num = jdbcTemplate.update(sql);
	//
	// if (num > 0)
	// {
	// return true;
	// }
	// return false;
	// }
	//
	// /**
	// * 更新用户信息
	// */
	// @Override
	// public boolean update(Member member)
	// {
	// StringBuilder sb = new StringBuilder();
	//
	// sb.append("update member m set ");
	// if (StringUtils.isNotBlank(member.getName()))
	// {
	// sb.append("m.name = '" + member.getName() + "' ");
	// }
	//
	// if (StringUtils.isNotBlank(member.getPhone()))
	// {
	// sb.append("m.phone = '" + member.getPhone() + "' ");
	// }
	// if (StringUtils.isNotBlank(member.getEmail()))
	// {
	// sb.append("m.email = '" + member.getEmail() + "' ");
	// }
	//
	// sb.append("WHERE m.id = '" + member.getId() + "'");
	//
	// int num = jdbcTemplate.update(sb.toString());
	//
	// if (num > 0)
	// {
	// return true;
	// }
	// return false;
	// }
	//
	// @Override
	// public boolean updateIcon(String icon, String id)
	// {
	// String sql = "update member m set m.icon = '" + icon + "' where m.id = '"
	// + id + "'";
	//
	// int num = jdbcTemplate.update(sql);
	//
	// if (num > 0)
	// {
	// return true;
	// }
	// return false;
	// }
	//
	// /**
	// * 更新用户密码
	// */
	// public boolean updateMemberByPhone(String phone, String password)
	// {
	// String sql = "update member m set m.password = '" + password +
	// "' where m.phone = '" + phone + "'";
	//
	// int num = jdbcTemplate.update(sql);
	//
	// if (num > 0)
	// {
	// return true;
	// }
	// return false;
	//
	// }
	//
}
