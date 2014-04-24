package com.mediacross.lottery.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.mediacross.lottery.utils.DateUtil;
import com.mediacross.lottery.utils.DateUtil.DateFmts;

@Entity
@Table(name="lottery_awards")
public class LotteryAwards {
	private long id;
	private long lotteryId;
	private String awardsGrade;
	private long awardsAmount;
	private Date awardsTime;
	private String note;
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
	 * @return the lotteryId
	 */
	@Column(name="lottery_id")
	public long getLotteryId() {
		return lotteryId;
	}
	/**
	 * @param lotteryId the lotteryId to set
	 */
	public void setLotteryId(long lotteryId) {
		this.lotteryId = lotteryId;
	}
	/**
	 * @return the awardsGrade
	 */
	@Column(name="awards_grade")
	public String getAwardsGrade() {
		return awardsGrade;
	}
	/**
	 * @param awardsGrade the awardsGrade to set
	 */
	public void setAwardsGrade(String awardsGrade) {
		this.awardsGrade = awardsGrade;
	}
	/**
	 * @return the awardsAmount
	 */
	@Column(name="awards_amount")
	public long getAwardsAmount() {
		return awardsAmount;
	}
	/**
	 * @param awardsAmount the awardsAmount to set
	 */
	public void setAwardsAmount(long awardsAmount) {
		this.awardsAmount = awardsAmount;
	}
	/**
	 * @return the awardsTime
	 */
	@Column(name="awards_time")
	public Date getAwardsTime() {
		return awardsTime;
	}
	
	@Transient
	public String getAwardsTimeStr() {
		return DateUtil.getDate(DateFmts.YYYY_MM_DD_HHMMSS, awardsTime);
	}
	
	/**
	 * @param awardsTime the awardsTime to set
	 */
	public void setAwardsTime(Date awardsTime) {
		this.awardsTime = awardsTime;
	}
	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}
	/**
	 * @param note the note to set
	 */
	public void setNote(String note) {
		this.note = note;
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
