package com.mediacross.lottery.service.impl;

import java.util.Date;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional(propagation=Propagation.REQUIRED)
	public Lottery getLottery() throws AppException {
		String hql = "from Lottery where status=?";
		Lottery lottery = (Lottery) hibernateDao
				.createQuery(hql, LotteryStatus.NOT_GET)
				.setFirstResult(0)
				.setMaxResults(1)
				.uniqueResult();
		if (lottery == null) {
			throw new AppException(10004, "彩票已经领光");
		} else {
			lottery.setStatus(LotteryStatus.GETTING);
			hibernateDao.save(lottery);
			return lottery;
		}
	}

	@Override
	public boolean updateLottery(String lotteryNo, int lotteryStatus) throws AppException {
		String hql = "from Lottery  where lotteryNo=? and status=?";
		Lottery lottery = (Lottery) hibernateDao
				.createQuery(hql, lotteryNo, LotteryStatus.GETTING)
				.setFirstResult(0)
				.setMaxResults(1)
				.uniqueResult();
		if (lottery == null) {
			throw new AppException(10001, "彩票确认超时，请重新领取！");
		} else {
			lottery.setStatus(LotteryStatus.HAS_GET);
			lottery.setModified(new Date());
			hibernateDao.save(lottery);
			return true;
		}
	}

	@Override
	public void resetLotteryInLoop() {
		String hql = "update Lottery set status=?, modified=? where modified <= ? and status=?";
		Date now = new Date();
		hibernateDao.createQuery(hql, LotteryStatus.NOT_GET, now,
				DateUtils.addMinutes(now, -5), LotteryStatus.GETTING)
				.executeUpdate();
	}
	
}
