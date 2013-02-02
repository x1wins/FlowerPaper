package net.changwoo.chat.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="chat_room")
public class Room {
	public Room(){
		this.creationDate = new Date();
		this.openStatus = 1;
	}
	@Id
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Column(name="subject", nullable = false, length = 100)
	private String subject;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="creation_date")
	private Date creationDate;
	
	@Column(name="open_status", nullable = false)
	private int openStatus;
	
	@Column(name="master_userid", nullable = false, length = 100)
	private int masterUserid;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getOpenStatus() {
		return openStatus;
	}

	public void setOpenStatus(int openStatus) {
		this.openStatus = openStatus;
	}

	public int getMasterUserid() {
		return masterUserid;
	}

	public void setMasterUserid(int masterUserid) {
		this.masterUserid = masterUserid;
	}
	
}
