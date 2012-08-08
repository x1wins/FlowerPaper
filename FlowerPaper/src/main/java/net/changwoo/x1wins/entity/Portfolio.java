package net.changwoo.x1wins.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="portfolio")
public class Portfolio {

	public Portfolio() {
		
		this.status = 1;
		this.regdate = new Date();
//		this.file = new HashSet<File>(0);
		
	}
	
	@Id
    @Column(name="num", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int num;
     
    @Column(name="userid", nullable = false, length = 100)
    private String userid;
    
    @Column(name = "status", nullable = false, insertable = false, updatable = true, columnDefinition = "int default 1")
    private int status;
     
    @Temporal(TemporalType.TIMESTAMP) 
    private Date regdate;

    @NotEmpty
    @Column(name = "subject", nullable = false, length = 9999)
    private String subject;
    
    @NotEmpty
    @Column(name = "content", nullable = false, length = 9999)
    private String content;
    
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinTable(name = "portfolio_file", joinColumns = { @JoinColumn(name = "num") }, inverseJoinColumns = { @JoinColumn(name = "filenum") })
//    private Set<File> file = new HashSet<File>(0);


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


//	public Set<File> getFile() {
//		return file;
//	}
//
//
//	public void setFile(Set<File> file) {
//		this.file = file;
//	}
  
    
	
}
