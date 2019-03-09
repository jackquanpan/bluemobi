package cn.bluemobi.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.bluemobi.dao.BaseDao;

/**
 * 设计师结算定时器类
 * 
 * @author Chenwq
 * 
 */
@Component("SaleSettlementTask")
public class SaleSettlementTask {

	@Autowired
	private BaseDao dao;

	// @Scheduled(cron = "0 0 0 1,16 * ?")
	// @Scheduled(cron = "0/5 * * * * ?")
	// public void settle() {
	//
	// try {
	// String sql = "SELECT  s.`id` AS studioId,"
	// +
	// "(SELECT SUM(t.`quantity`) FROM item_order t WHERE TO_DAYS(t.`deal_date`) BETWEEN TO_DAYS(?) AND TO_DAYS(?) AND t.`studio_id` = s.`id` AND (t.order_status=4 OR (t.order_status=5 AND t.return_item=2) OR (t.order_status=6 AND t.return_item=2 ))) AS totalSaleQuantity, "
	// +
	// "IFNULL((SELECT SUM(t.`total_cost_money`) FROM item_order t WHERE TO_DAYS(t.`deal_date`) BETWEEN TO_DAYS(?) AND TO_DAYS(?) AND t.`studio_id` = s.`id` AND (t.order_status=4 OR (t.order_status=5 AND t.return_item=2) OR (t.order_status=6 AND t.return_item=2 ))),0.00) AS totalSale, "
	// +
	// "IFNULL((SELECT SUM(t.`gold`) FROM item_order t WHERE TO_DAYS(t.`deal_date`) BETWEEN TO_DAYS(?) AND TO_DAYS(?) AND t.`studio_id` = s.`id` AND (t.order_status=4 OR (t.order_status=5 AND t.return_item=2) OR (t.order_status=6 AND t.return_item=2 ))),0.00) AS totalGold, "
	// +
	// "IFNULL((SELECT SUM(t.`coupon`) FROM item_order t WHERE TO_DAYS(t.`deal_date`) BETWEEN TO_DAYS(?) AND TO_DAYS(?) AND t.`studio_id` = s.`id` AND (t.order_status=4 OR (t.order_status=5 AND t.return_item=2) OR (t.order_status=6 AND t.return_item=2 ))),0.00) AS totalCoupon,"
	// +
	// "IFNULL(s.`commission`,0.00) as commission FROM studio s WHERE s.`status` = 1";
	//
	// List<Object> params = new ArrayList<Object>();
	// Date current = new Date();
	// Calendar c = Calendar.getInstance();
	// c.setTime(current);
	//
	// String date = "";
	//
	// if (c.get(Calendar.DAY_OF_MONTH) > 15) {// 16日统计
	// int m = c.get(Calendar.MONTH) + 1;// 月数
	// date = TimeHelper.formatDate(current, "yyyyMM") + "01（" + m
	// + "月1日-" + m + "月15日）";
	// // 开始时间
	// c.set(Calendar.DAY_OF_MONTH, 1);
	// Date startDate = c.getTime();
	// // 结束时间
	// c.set(Calendar.DAY_OF_MONTH, 15);
	// Date endDate = c.getTime();
	//
	// params.add(startDate);
	// params.add(endDate);
	// params.add(startDate);
	// params.add(endDate);
	// params.add(startDate);
	// params.add(endDate);
	// params.add(startDate);
	// params.add(endDate);
	// } else {// 1日统计
	// c.set(Calendar.DAY_OF_MONTH, 1);
	// c.add(Calendar.DATE, -1);
	// Date endDate = c.getTime();// 上一个月的最后一天
	//
	// int m = c.get(Calendar.MONTH) + 1;// 月数
	// date = TimeHelper.formatDate(endDate, "yyyyMM") + "02（" + m
	// + "月16日-" + m + "月" + c.get(Calendar.DAY_OF_MONTH)
	// + "日）";
	//
	// // 开始时间
	// c.set(Calendar.DAY_OF_MONTH, 1);
	// Date startDate = c.getTime();
	//
	// params.add(startDate);
	// params.add(endDate);
	// params.add(startDate);
	// params.add(endDate);
	// params.add(startDate);
	// params.add(endDate);
	// params.add(startDate);
	// params.add(endDate);
	// }
	//
	// List<StylistCheck> list = dao.findForList(sql, StylistCheck.class,
	// params.toArray());
	// // 循环生成设计师结算账单
	// for (StylistCheck s : list) {
	// String code = OrderHelp.getSettleCode();// 账单编码
	// double need_to_pay = 0.00;
	// if (!ValidateHelper.isNullOrEmpty(s.getCommission())) {
	// need_to_pay = s.getTotalSale() * s.getCommission();
	// }
	// s.setNeedToPay(s.getTotalSale() - need_to_pay);
	// s.setCheckDate(date);
	// s.setCheckCode(code);
	// s.setStatus(1);
	//
	// dao.executeByObject(
	// "INSERT INTO `stylist_check`(`check_code`,`check_date`,`studio_id`,`total_sale`,`total_sale_quantity`,`total_gold`,`total_coupon`,`commission`,`need_to_pay`,`status`,`create_date`) VALUES (:checkCode,:checkDate,:studioId,:totalSale,:totalSaleQuantity,:totalGold,:totalCoupon,:commission,:needToPay,:status,NOW())",
	// s);
	// }
	// } catch (Exception e) {
	// e.printStackTrace();
	// System.out.println("定时器生成设计时账单错误");
	// }
	//
	// }

	/**
	 * 每天量程查找数据库，15天不收货的商品自动确认 不能与上面的任务出现时间重叠，不然无法执行
	 */

	// @Scheduled(cron = "0 15 0 * * ?")
	public void witeDate() {
		try {
			// 自动
			String sql = " UPDATE item_order SET order_status=4,deal_date =NOW() WHERE order_status=3 and TO_SECONDS(NOW())- TO_SECONDS(send_date)>1296000 ";
			dao.executeByParams(sql);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("自动确认订单错误");
		}
	}

}
