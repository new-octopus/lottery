package com.mediacross.lottery.web.lottery;

import java.util.Map;

import org.apache.commons.beanutils.BasicDynaClass;
import org.apache.commons.beanutils.DynaBean;
import org.apache.commons.beanutils.DynaClass;
import org.apache.commons.beanutils.DynaProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mediacross.lottery.common.Constants.LotteryAwardsStatus;
import com.mediacross.lottery.common.error.AppException;
import com.mediacross.lottery.common.error.SystemException;
import com.mediacross.lottery.service.LotteryAwardsService;
import com.mediacross.lottery.utils.DesUtil;
import com.mediacross.lottery.vo.LotteryAwards;
import com.mediacross.lottery.web.BaseAction;

@Repository
public class ExchangeAction extends BaseAction {
	@Autowired
	private LotteryAwardsService lotteryAwardsService;
	private static final DynaClass resultClazz = new BasicDynaClass(null, null,
			new DynaProperty[] {
					new DynaProperty("lottery_state", Integer.class),
					new DynaProperty("lottery_awards", LotteryAwards.class) });
	
	@Override
	public DynaBean execute(Map paramMap) throws AppException {
		String lotteryNo = null;
		try {
			lotteryNo = DesUtil.decrypt((String) paramMap.get("lottery_no"),
					config.getClientToken());
			LotteryAwards lotteryAwards = lotteryAwardsService
					.getLotteryAwards(lotteryNo);
			DynaBean result = null;
			try {
				result = resultClazz.newInstance();
				// 未中奖
				if (lotteryAwards == null) {
					result.set("lottery_state", LotteryAwardsStatus.LOSE);
					// 中奖
				} else {
					result.set("lottery_state", LotteryAwardsStatus.WIN);
					result.set("lottery_awards", lotteryAwards);
				}
				return result;
			} catch (IllegalAccessException e) {
				// LOG.error("创建对象失败！", e);
				throw new SystemException("创建对象失败！");
			} catch (InstantiationException e) {
				// LOG.error("创建对象失败！", e);
				throw new SystemException("创建对象失败！");
			}
		} catch (Exception e) {
			// TODO
		}
		return null;
	}

}
