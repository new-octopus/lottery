package com.mediacross.lottery.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mediacross.lottery.common.Constants.LotteryStatus;
import com.mediacross.lottery.common.error.AppException;
import com.mediacross.lottery.dao.HibernateDao;
import com.mediacross.lottery.service.LotteryService;
import com.mediacross.lottery.vo.Lottery;

@Service
public class LotteryServiceImpl implements LotteryService{
	@Autowired
	private HibernateDao<Lottery> hibernateDao;
	
	@Override
	public Lottery getLottery() throws AppException {
		String hql = "from Lottery where status=?";
		Lottery lottery = (Lottery) hibernateDao
				.createQuery(hql, LotteryStatus.NOT_GET)
				.setFirstResult(0)
				.setMaxResults(1)
				.uniqueResult();
		if (lottery == null) {
			// 彩票已经分配完了。
			throw new AppException(0, null);
		} else {
			lottery.setStatus(LotteryStatus.GETTING);
			hibernateDao.save(lottery);
			return lottery;
		}
	}

	@Override
	public boolean updateLottery(String lotteryNo, int lotteryStatus) {
		String hql = "from Lottery  where lotteryNo=? and status=?";
		Lottery lottery = (Lottery) hibernateDao.createQuery(hql, lotteryNo, LotteryStatus.GETTING)
				.setFirstResult(0)
				.setMaxResults(1)
				.uniqueResult();
		if (lottery == null) {
			// TODO
			return false;
		} else {
			lottery.setStatus(LotteryStatus.HAS_GET);
			lottery.setModified(new Date());
			hibernateDao.save(lottery);
			return true;
		}
	}
}
