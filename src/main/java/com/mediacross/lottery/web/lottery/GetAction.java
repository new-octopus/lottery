package com.mediacross.lottery.web.lottery;

import java.util.Map;

import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaClass;
import org.apache.commons.beanutils.DynaProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mediacross.lottery.common.error.AppException;
import com.mediacross.lottery.service.LotteryService;
import com.mediacross.lottery.utils.DateUtil;
import com.mediacross.lottery.utils.DateUtil.DateFmts;
import com.mediacross.lottery.utils.DesUtil;
import com.mediacross.lottery.utils.LangUtils;
import com.mediacross.lottery.vo.Lottery;
import com.mediacross.lottery.vo.LotteryType;
import com.mediacross.lottery.web.BaseAction;

@Repository
public class GetAction extends BaseAction {
	private static final Logger LOG = LoggerFactory.getLogger(GetAction.class);
	@Autowired
	private LotteryService lotteryService;

	private static final DynaClass resultClazz = new BasicDynaClass(null, null,
			new DynaProperty[] { new DynaProperty("lottery_type", String.class),
			new DynaProperty("lottery_time", String.class) ,
			new DynaProperty("lottery_no", String.class),
			new DynaProperty("lottery_open_date", String.class)});
	
	@Override
	public DynaBean execute(Map paramMap) throws AppException {
		DynaBean result = LangUtils.newInstance(resultClazz);
		Lottery lottery = lotteryService.getLottery();
		LotteryType lotteryType = lottery.getLotteryType();
		result.set("lottery_type", lotteryType.getLotteryType());
		result.set("lottery_time", lotteryType.getLotteryTime());
		result.set("lottery_no", DesUtil.encrypt(lottery.getLotteryNo(),
				config.getClientToken()));
		result.set(
				"lottery_open_date",
				DateUtil.getDate(DateFmts.YYYY_MM_DD_HHMMSS,
						lotteryType.getLotteryOpenDate()));
		return result;
	}

}
