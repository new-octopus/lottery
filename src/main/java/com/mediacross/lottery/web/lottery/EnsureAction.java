package com.mediacross.lottery.web.lottery;

import java.util.Map;

import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mediacross.lottery.common.Constants.LotteryStatus;
import com.mediacross.lottery.common.error.AppException;
import com.mediacross.lottery.service.LotteryService;
import com.mediacross.lottery.utils.DesUtil;
import com.mediacross.lottery.web.BaseAction;

@Repository
public class EnsureAction extends BaseAction {
	@Autowired
	private LotteryService lotteryService;

	@Override
	public DynaBean execute(Map paramMap) throws AppException {
		String lotteryNo = DesUtil.decrypt((String) paramMap.get("lottery_no"),
				config.getClientToken());
		lotteryService.updateLottery(lotteryNo, LotteryStatus.HAS_GET);
		return null;
	}
	
	@Override
	public boolean validate(Map paramMap) throws AppException {
		if (!super.validate(paramMap)) {
			return false;
		}

		if (MapUtils.isEmpty(paramMap) || paramMap.containsKey("lottery_no")) {
			throw new AppException(10006, "缺少必选请求参数");
		}
		return true;
	}

}
