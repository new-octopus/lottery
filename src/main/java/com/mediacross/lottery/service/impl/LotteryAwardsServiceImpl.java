package com.mediacross.lottery.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediacross.lottery.common.error.AppException;
import com.mediacross.lottery.dao.HibernateDao;
import com.mediacross.lottery.service.LotteryAwardsService;
import com.mediacross.lottery.vo.Lottery;
import com.mediacross.lottery.vo.LotteryAwards;

@Service
public class LotteryAwardsServiceImpl implements LotteryAwardsService {

	@Autowired
	private HibernateDao hibernateDao;
	
	@Override
	public LotteryAwards getLotteryAwards(String lotteryNo) throws AppException {
		// 查询彩票信息
		String hql = "from Lottery where lotteryNo=?";
		Lottery lottery = (Lottery) hibernateDao
				.createQuery(hql, lotteryNo)
				.setFirstResult(0)
				.setMaxResults(1)
				.uniqueResult();
		// 未中奖。
		if (lottery==null) {
			return null;
		} else {
			hql = "from LotteryAwards where lotteryId=?";
			LotteryAwards lotteryAwards = (LotteryAwards) hibernateDao
					.createQuery(hql, lottery.getId())
					.setFirstResult(0)
					.setMaxResults(1)
					.uniqueResult();
			return lotteryAwards;
		}
	}

}
