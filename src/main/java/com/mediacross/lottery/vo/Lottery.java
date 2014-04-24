package com.mediacross.lottery.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Lottery {
	private long id;
	private String lotteryNo;
	private LotteryType lotteryType;
	private int status;
	private String note;
	private Date modified;
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
	 * @param id
	 *            the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}

	/**
	 * @return the lotteryNo
	 */
	@Column(name = "lottery_no")
	public String getLotteryNo() {
		return lotteryNo;
	}

	/**
	 * @param lotteryNo
	 *            the lotteryNo to set
	 */
	public void setLotteryNo(String lotteryNo) {
		this.lotteryNo = lotteryNo;
	}

	/**
	 * @return the lotteryTypeId
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lottery_type_id")
	public LotteryType getLotteryType() {
		return lotteryType;
	}

	/**
	 * @param lotteryType
	 *            the lotteryType to set
	 */
	public void setLotteryType(LotteryType lotteryType) {
		this.lotteryType = lotteryType;
	}

	/**
	 * @return the status
	 */
	public int getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(int status) {
		this.status = status;
	}

	/**
	 * @return the note
	 */
	public String getNote() {
		return note;
	}

	/**
	 * @param note
	 *            the note to set
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * @return the modified
	 */
	public Date getModified() {
		return modified;
	}

	/**
	 * @param modified
	 *            the modified to set
	 */
	public void setModified(Date modified) {
		this.modified = modified;
	}

	/**
	 * @return the created
	 */
	public Date getCreated() {
		return created;
	}

	/**
	 * @param created
	 *            the created to set
	 */
	public void setCreated(Date created) {
		this.created = created;
	}

}