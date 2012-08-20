package net.changwoo.x1wins.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="reply")
public class Reply {
	
	public Reply(){
		this.status = 1;
		this.regdate = new Date();
	}

	@Id
    @Column(name="rnum", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int rnum;
	
    @Column(name = "status", nullable = false, insertable = false, updatable = true, columnDefinition = "int default 1")
    private int status;
    
    @Temporal(TemporalType.TIMESTAMP) 
    private Date regdate;
    
    @Column(name="userid", nullable = false, length = 100)
    private String userid;

    @NotEmpty
    @Column(name = "content", nullable = false, length = 9999)
    private String content;
    
    @Column(name = "ip", nullable = false, length = 255)
    private String ip;
    
//    @ManyToOne//(cascade = CascadeType.ALL)
//    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
//	@JoinColumn(name = "reply_user", nullable = false)
//    @JoinTable(name = "reply_user", joinColumns = { @JoinColumn(name = "rnum") }, inverseJoinColumns = { @JoinColumn(name = "num") })
//    private User user;
    
//    @ManyToOne
//	@JoinTable(name = "bbs_reply", joinColumns = { @JoinColumn(name = "rnum") }, inverseJoinColumns = { @JoinColumn(name = "num") })
    @ManyToOne
    @JoinColumn(name="num")
    private Bbs bbs;
    
	public Bbs getBbs() {
		return bbs;
	}

	public void setBbs(Bbs bbs) {
		this.bbs = bbs;
	}
    
//    @ManyToOne
//	@JoinColumn(name = "rnum", insertable = false, updatable = false, nullable = false)
//    private Bbs bbs;

	public int getRnum() {
		return rnum;
	}

	public void setRnum(int rnum) {
		this.rnum = rnum;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

//	public Bbs getBbs() {
//		return bbs;
//	}
//
//	public void setBbs(Bbs bbs) {
//		this.bbs = bbs;
//	}
      
}
