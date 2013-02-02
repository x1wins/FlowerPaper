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
import javax.validation.constraints.Min;

@Entity
@Table(name="chat_join_room")
public class Join {
	public Join(){
		this.creationDate = new Date();
		this.joinStatus = 1;
	}
	@Id
    @Column(name="id", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="creation_date")
	private Date creationDate;
	
	@Min(1)
	@Column(name="roomid", nullable = false)
	private int roomid;
	
	@Min(1)
	@Column(name="userid", nullable = false)
	private int userid;
	
	@Column(name="join_status", nullable = false)
	private int joinStatus;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getRoomid() {
		return roomid;
	}

	public void setRoomid(int roomid) {
		this.roomid = roomid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getJoinStatus() {
		return joinStatus;
	}

	public void setJoinStatus(int joinStatus) {
		this.joinStatus = joinStatus;
	}
	
	
}
