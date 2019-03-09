package cn.bluemobi.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bluemobi.entity.app.TiziInfo;
import cn.bluemobi.mapper.app.AppCommunityMapper;
import cn.bluemobi.mapper.app.AppLoginMapper;
import cn.bluemobi.service.AppCommunityService;
@Service
public class AppComunityServiceImpl implements AppCommunityService {
     @Autowired
     private AppCommunityMapper appcommunity;
     @Autowired
     private AppLoginMapper apploginmapper;
	
	@Override
	public Map<String, Object> createCommunity(Map<String, Object> parammap) {
		// TODO Auto-generated method stub
		appcommunity.addCommunity(parammap);
		return parammap;
	}

	@Override
	public List<TiziInfo> getTeiziListByFenYe() {
	
		return appcommunity.getTeiziListByFenYe();
	}

	@Override
	public Map<String, Object> LongApp(Map<String, Object>resultMa) {
	    return 	apploginmapper.LoginApp(resultMa);
		
	}

}
