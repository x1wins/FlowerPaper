package net.changwoo.app.bbs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bbs_config")
public class BbsConfig {
	
	public BbsConfig(){
		listTypeNum = 1;
		readFrom = 0;
		readUntil = 0;
		writeFrom = 0;
		writeUntil = 0;
		publicYn = 1;
		replyYn = 1;
	}

	@Id
    @Column(name="bbsnum", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int bbsnum;
	
	@Column(name="userid", nullable = false, length = 100)
    private String userid;
	
	@Column(name="bbsname", nullable = false, length = 100)
	private String bbsname;

	@Column(name="list_type_num", nullable = false , columnDefinition = "int default 1")
    private int listTypeNum;
	
	@Column(name="read_from", nullable = false, columnDefinition = "int default 0")
	private int readFrom;
	
	@Column(name="read_until", nullable = false, columnDefinition = "int default 0")
	private int readUntil;
	
	@Column(name="write_from", nullable = false, columnDefinition = "int default 0")
	private int writeFrom;
	
	@Column(name="write_until", nullable = false, columnDefinition = "int default 0")
	private int writeUntil;
	
	@Column(name="public_yn", nullable = false, columnDefinition = "int default 1")
	private int publicYn;
	
	@Column(name="reply_yn", nullable = false, columnDefinition = "int default 1")
	private int replyYn;
	
	public int getReplyYn() {
		return replyYn;
	}

	public void setReplyYn(int replyYn) {
		this.replyYn = replyYn;
	}

	public int getReadFrom() {
		return readFrom;
	}

	public void setReadFrom(int readFrom) {
		this.readFrom = readFrom;
	}

	public int getReadUntil() {
		return readUntil;
	}

	public void setReadUntil(int readUntil) {
		this.readUntil = readUntil;
	}

	public int getWriteFrom() {
		return writeFrom;
	}

	public void setWriteFrom(int writeFrom) {
		this.writeFrom = writeFrom;
	}

	public int getWriteUntil() {
		return writeUntil;
	}

	public void setWriteUntil(int writeUntil) {
		this.writeUntil = writeUntil;
	}

	public int getPublicYn() {
		return publicYn;
	}

	public void setPublicYn(int publicYn) {
		this.publicYn = publicYn;
	}

	public int getListTypeNum() {
		return listTypeNum;
	}

	public void setListTypeNum(int listTypeNum) {
		this.listTypeNum = listTypeNum;
	}

	public int getBbsnum() {
		return bbsnum;
	}

	public void setBbsnum(int bbsnum) {
		this.bbsnum = bbsnum;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getBbsname() {
		return bbsname;
	}

	public void setBbsname(String bbsname) {
		this.bbsname = bbsname;
	}
	
}
