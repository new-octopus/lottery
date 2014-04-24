package com.mediacross.lottery.service;

import com.mediacross.lottery.common.Constants.LotteryStatus;
import com.mediacross.lottery.common.error.AppException;
import com.mediacross.lottery.vo.Lottery;

public interface LotteryService {
	/**
	 * 随机抽取一张彩票，并将该彩票设置为领取中。
	 * 
	 * @return {@link Lottery}
	 * @see LotteryStatus#GETTING
	 */
	Lottery getLottery() throws AppException;
	
	/**
	 * 更新彩票状态。
	 * 
	 * @param lotteryNo
	 * @param lotteryStatus
	 * @return 是否成功。
	 */
	boolean updateLottery(String lotteryNo, int lotteryStatus) throws AppException;
	
	/**
	 * 每隔5分钟重置彩票领取状态。
	 * <p/>
	 * 超过5分钟没有确认彩票被领取，则将状态重置为未领取。
	 * @see LotteryStatus
	 */
	void resetLotteryInLoop();
	
}
