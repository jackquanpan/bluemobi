/**
 * 订单列表的javascript函数
 */

function doSearch(){
	$("#priTB").datagrid("load", serializeObject($("#searchForm")));
}
		
function loadGrid(){
	$("#priTB").datagrid({
    	//在载入请求数据数据之前触发，如果返回false可终止载入数据操作。
        onBeforeLoad:function(){
            $(this).datagrid('rejectChanges');
           },
    });
}

$(function(){
	loadGrid();
});

function exportExcel(){
	$("#searchForm").submit();
}
//
function viewOrder(orderNumber){
	parent.addTab("订单详情",$("base")[0].href+"order/getOrderDetail.htm?orderNumber="+orderNumber);
}

function editReceive(obj){
	var receiveId = $(obj).attr("field");
	alertMessage(receiveId);
}

function cancelOrder(orderId){
	var baseUrl = $("base")[0].href;
	$.ajax({	
		url : baseUrl+"order/cancelOrder.htm",
		data:{
			"orderId" : orderId
		},
		dataType : 'json',
		type:'POST',
		success	: function(data){
			if(data == '1'){
				alertMessage("订单取消成功!");
				loadGrid();
			}else{
				alertMessage("订单取消失败!");
			}
		},
		error :	function(){
			alertMessage("订单取消失败!");
		}
	});
}

function deleteOrder(orderNumber){
	var baseUrl = $("base")[0].href;
	$.ajax({
		url : baseUrl+"order/deleteOrder.htm",
		data:{
			"orderNumber" : orderNumber
		},
		dataType:'json',
		type:'POST',
		success:function(data){
			if(data == '1'){
				alertMessage("订单删除成功!");
				loadGrid();
				
			}else{
				alertMessage("订单删除失败!");
			}
		},
		error:function(){
			alertMessage("订单删除失败!");
		}
	});
}

function alertMessage(value,fun){
	$.messager.alert("提示",value,"info",fun);
}

function showReceiveAddress(value,row){
	return row.shopAddress.receiveAddress;
}
function showReceiveName(value,row){
	return row.shopAddress.receiveName;
}
function showReceivePhone(value,row){
	return row.shopAddress.receivePhone;
}
function showCreateDate(value,row){
	var val = row.createDate;
	
	if (val == undefined) {
        return "";
    }
    /*json格式时间转js时间格式*/
	val = val.substr(0, val.length - 2);
    return val;
}

//设置奇偶行的颜色
function setStyler(value, row, index){
	if(index / 2 == 0){
		return 'background-color:blue;';
	}else{
		return 'background-color:red;';
	}
}
				
function showStatus(value,row,index){
	if(value=="1"){
       	return "待支付";				     
     }
     else if(value=="2"){
       	return "交易完成";				     
     }
     else if(value=="3"){
       	return "支付待评价";				     
     }
     else if(value=="4"){
       	return "交易失败";			     
     }
}
				
//val:指当前单元格的指,row:当前行对象,index当前行的索引
function showPayType(value,row,index){
	if(value == "1"){
		return "支付宝";
	}else if(value == "2"){
		return "银联";
	}else{
		return "-";
	}
}

//格式化操作对象
function formatOper(value,row,index){	
	var status = row.payStatus;
	var orderNumber = row.orderNumber;
	var orderId = row.id;
	return "<span class='button' onclick='viewOrder("+orderNumber+")'>查看</span>";
}


//商户端格式化操作对象
function formatbusOper(value,row,index){
	var status = row.payStatus;
	var orderNumber = row.orderNumber;	
	var orderId = row.id;
	if(status == "1"){
		return "<span class='button' onclick='viewBusOrder("+orderNumber+")'>查看</span>|<span class='button' onclick='cancelOrder("+orderId+")'>取消订单</span>|<span class='button' onclick='deleteOrder("+orderNumber+")'>删除</span>";
	}else{
		return "<span class='button' onclick='viewBusOrder("+orderNumber+")'>查看</span>|<span class='button' onclick='deleteOrder("+orderNumber+")'>删除</span>";
	}
}
//商户端查看订单详情，可修改未支付价格
function viewBusOrder(orderNumber){	
	parent.addTab("订单详情",$("base")[0].href+"bussiness/getMyOrderDetail.htm?orderNumber="+orderNumber);
}

