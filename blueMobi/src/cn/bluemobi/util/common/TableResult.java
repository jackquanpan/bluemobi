/**
 * Project Name:xhl
 * File Name:TableResult.java
 * Package Name:cn.bluemobi.util.common
 * Date:2015-7-3下午4:21:34
 * Copyright (c) 2015, oscarwang1988@126.com All Rights Reserved.
 *
 */

package cn.bluemobi.util.common;

import java.util.List;

/**
 * Description: 返回结果构造对象<br/>
 * Date: 2015-7-3 下午4:21:34 <br/>
 * 
 * @author oscar_000
 * @version
 * @see
 */
public class TableResult {

    private final List<? extends Object> table;

    private final String[] headers;

    private final int status;// 标识

    private final String msg;

    public List<? extends Object> getTable() {
        return this.table;
    }

    public String[] getHeaders() {
        return this.headers;
    }

    public TableResult(List<? extends Object> table, String[] headers, int status, String msg) {
        this.table = table;
        this.headers = headers;
        this.status = status;
        this.msg = msg;
    }

    public TableResult(List<? extends Object> table) {
        this(table, null, 0, null);
    }

    public int getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

}
