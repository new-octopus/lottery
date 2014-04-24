package com.mediacross.lottery.service;

import com.mediacross.lottery.common.error.AppException;
import com.mediacross.lottery.vo.LotteryAwards;

public interface LotteryAwardsService {
	/**
	 * 查询彩票中奖情况。
	 * 
	 * @param lotteryNo
	 * @return {@link LotteryAwards}
	 * @throws AppException
	 */
	LotteryAwards getLotteryAwards(String lotteryNo) throws AppException;
}
