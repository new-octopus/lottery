package com.mediacross.lottery.common;

public interface Constants {
	/**
	 * 彩票状态。
	 */
	public static interface LotteryStatus {
		/** 未领取 */
		int NOT_GET = 0;
		/** 领取中 */
		int GETTING = 1;
		/** 已经领取 */
		int HAS_GET = 2;
	}
	
	public static interface LotteryAwardsStatus {
		/** 未开奖 */
		int UNOPEN = 0;
		/** 未中奖 */
		int LOSE = 1;
		/** 中奖 */
		int WIN = 2;
	}
}
