package net.changwoo.x1wins.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="bbs")
public class Bbs {

	public Bbs() {
		
		this.status = 1;
		this.regdate = new Date();
		this.count = 0;
		
	}
	
	@Id
    @Column(name="num", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int num;
	
    @Column(name="userid", nullable = false, length = 100)
    private String userid;
    
    @Column(name = "status", nullable = false, insertable = false, updatable = true, columnDefinition = "int default 1")
    private int status;
    
    @Column(name = "count", nullable = false)
    private int count;
     
    @Temporal(TemporalType.TIMESTAMP) 
    private Date regdate;

    @NotEmpty
    @Column(name = "subject", nullable = false, length = 9999)
    private String subject;
    
    @NotEmpty
    @Column(name = "content", nullable = false, length = 9999)
    private String content;
    
    @Column(name = "ip", nullable = false, length = 255)
    private String ip;
    
	@ManyToOne//(cascade = CascadeType.ALL)
    @Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
    @JoinTable(name = "bbs_config", joinColumns = { @JoinColumn(name = "num") }, inverseJoinColumns = { @JoinColumn(name = "bbsnum") })
    private Config config;
	
//	@OneToMany(mappedBy ="bbs")
//	private List<Reply> replys;
//    
//	public List<Reply> getReplys() {
//		return replys;
//	}
//
//	public void setReplys(List<Reply> replys) {
//		this.replys = replys;
//	}


	public String getIp() {
		return ip;
	}
	
	
	public void setIp(String ip) {
		this.ip = ip;
	}


	public Config getConfig() {
		return config;
	}


	public int getCount() {
		return count;
	}


	public void setCount(int count) {
		this.count = count;
	}


	public void setConfig(Config config) {
		this.config = config;
	}


	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
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


	public String getSubject() {
		return subject;
	}


	public void setSubject(String subject) {
		this.subject = subject;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}

	
}
