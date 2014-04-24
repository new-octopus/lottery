package com.mediacross.lottery.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="lottery_type")
public class LotteryType {
	private long id;
	private String lotteryType;
	private String lotteryTime;
	private Date created;
	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the lotteryType
	 */
	@Column(name="lottery_type")
	public String getLotteryType() {
		return lotteryType;
	}
	/**
	 * @param lotteryType the lotteryType to set
	 */
	public void setLotteryType(String lotteryType) {
		this.lotteryType = lotteryType;
	}
	/**
	 * @return the lotteryTime
	 */
	@Column(name="lottery_time")
	public String getLotteryTime() {
		return lotteryTime;
	}
	/**
	 * @param lotteryTime the lotteryTime to set
	 */
	public void setLotteryTime(String lotteryTime) {
		this.lotteryTime = lotteryTime;
	}
	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}
	/**
	 * @param created the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}
	
}
