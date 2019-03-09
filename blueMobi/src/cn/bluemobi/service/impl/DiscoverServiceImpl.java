package cn.bluemobi.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bluemobi.mapper.admin.AdminDiscoveryMapper;
import cn.bluemobi.service.DiscoveryService;
@Service
public class DiscoverServiceImpl implements DiscoveryService {
      @Autowired
      private AdminDiscoveryMapper admindiscoverymapper;
	@Override
	public Map<String, Object> getDiscoveryList(Integer pageNo, Integer pagesize) {
		// TODO Auto-generated method stub
		Integer startIndex=(pageNo-1)*pagesize;
		List<Map<String, Object>> discoveryList = admindiscoverymapper.getDiscoveryList(startIndex, pagesize);
		int count= admindiscoverymapper.countdiscovery();
		
		Map<String, Object>paramMap=new HashMap<String, Object>();
		paramMap.put("total", count);
		paramMap.put("rows", discoveryList);
		return paramMap ;
	}

	@Override
	public int countdiscovery() {
		
		return admindiscoverymapper.countdiscovery();
	}

	@Override
	public boolean addDisCovery(Map<String, Object> paramMap) {
		return admindiscoverymapper.insertDisCovery(paramMap)>=1;
	}

	@Override
	public boolean bachUpdateSatus(String idStr) {
		// TODO Auto-generated method stub
		return admindiscoverymapper.batchUpdate(idStr)>=1;
	}

	@Override
	public List<Map<String, Object>> findSystemUserList() {
		// TODO Auto-generated method stub
		return admindiscoverymapper.getSystemUserList();
	}

	@Override
	public List<Map<String, Object>> findDiscoveryListByCondition(Map<String, Object> paramMap) {
		// TODO Auto-generated method stub
		return admindiscoverymapper.getDiscoveryListByCondition(paramMap);
	}

	
}
