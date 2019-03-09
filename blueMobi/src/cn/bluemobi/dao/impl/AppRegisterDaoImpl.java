/**
 * 文件名  ：AppRegisterDaoImpl.java
 * 
 * 描        述  ：
 * 修    改 人 ：huad
 * 修改时间  ：{date}
 * 跟踪单号  ： 跟踪单号
 *
 */
package cn.bluemobi.dao.impl;

import org.springframework.stereotype.Repository;

import cn.bluemobi.dao.AppRegisterDao;

/**
 * 一句话功能简述 功能详细描述
 * 
 * @author huad version [版本号，2015年4月9日] see [相关类/方法] since [产品/模块版本]
 */
@Repository
public class AppRegisterDaoImpl implements AppRegisterDao {
	// @Autowired
	// private JdbcTemplate jdbcTemplate;
	//
	// /**
	// * 保存会员信息
	// */
	// @Override
	// public String saveMember(Member member)
	// {
	// String baseSql =
	// "insert into member (name,password,phone,create_date,is_used";
	// StringBuilder filed = new StringBuilder();
	// StringBuilder value = new StringBuilder();
	//
	// filed.append(baseSql);
	// if (0 != member.getLogintype())
	// {
	// filed.append(",login_type");
	// value.append(",'" + member.getLogintype() + "'");
	// }
	//
	// if (StringUtils.isNotBlank(member.getIcon()))
	// {
	// filed.append(",icon");
	// value.append(",'" + member.getIcon() + "'");
	// }
	//
	// if (null != member.getThirdid())
	// {
	// filed.append(",third_id");
	// value.append(",'" + member.getThirdid() + "'");
	// }
	//
	// filed.append(
	// ") values('" + member.getName() + "','" + member.getPassword() + "','" +
	// member.getPhone() + "',NOW(),1")
	// .append(value);
	//
	// String sql = filed.append(")").toString();
	// int x = jdbcTemplate.update(sql);
	//
	// String sqlId = "select id from member where phone = '" +
	// member.getPhone() + "' and password = '"
	// + member.getPassword() + "' and name = '" + member.getName() + "'";
	// String id = "";
	// if (x > 0)
	// {
	// id = jdbcTemplate.queryForObject(sqlId, String.class);
	// }
	//
	// return id;
	// }
	//
	// /**
	// * 保存手机注册验证码
	// */
	// @Override
	// public void savePhoneCode(PhoneCode phoneCode)
	// {
	// String sql = "insert into member_code(phone,code,create_date) values('" +
	// phoneCode.getPhone() + "','"
	// + phoneCode.getCode() + "',NOW())";
	// jdbcTemplate.execute(sql);
	// }
	//
	// /**
	// * 验证手机号是否已注册
	// */
	// @Override
	// public boolean phoneIsExist(String phone)
	// {
	// String sql = "select count(*) from member m where m.phone = '" + phone +
	// "'";
	//
	// int num = jdbcTemplate.queryForInt(sql);
	//
	// if (num > 0)
	// {
	// return false;
	// }
	//
	// return true;
	// }
	//
	// /**
	// * 验证用户名是否已注册
	// */
	// @Override
	// public boolean nameIsExist(String name)
	// {
	// String sql = "select count(*) from member m where m.name = '" + name +
	// "'";
	//
	// int num = jdbcTemplate.queryForInt(sql);
	//
	// if (num > 0)
	// {
	// return false;
	// }
	// return true;
	// }
	//
	// /**
	// * 获得上次验证码
	// */
	// @Override
	// public PhoneCode getLastCode(String phone)
	// {
	// String sql =
	// "SELECT phone,code,create_date FROM member_code where phone = '" + phone
	// + "' order by create_date DESC";
	//
	// List<?> list = jdbcTemplate.queryForList(sql);
	//
	// return TransferPhoneCode(list);
	//
	// }
	//
	// private PhoneCode TransferPhoneCode(List<?> list)
	// {
	// if (list != null && list.size() > 0)
	// {
	// @SuppressWarnings("unchecked")
	// Map<String, Object> obj = (Map<String, Object>)list.get(0);
	// PhoneCode p = new PhoneCode();
	// p.setPhone(appendToStr(obj.get("phone")));
	// p.setCode(appendToStr(obj.get("code")));
	// p.setCreateDate(appendToStr(obj.get("create_date")));
	// return p;
	// }
	// return null;
	// }
	//
	// private String appendToStr(Object obj)
	// {
	// if (obj == null)
	// {
	// return "";
	// }
	// return obj.toString();
	// }
}
