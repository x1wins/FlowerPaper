package net.changwoo.x1wins.entity;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name="file")
public class File {
	
	public File(){
		this.regdate = new Date();
	}

	@Id
    @Column(name="num")
	@GeneratedValue(strategy=GenerationType.AUTO)
    private Integer num;

//	@NotNull
	@Column(name="classname")
	private String classname;
	
//	@NotNull
	@Column(name="snum")
	private int snum;
	
//	@NotNull
    @Column(name="filename")
    private String filename;
 
//	@NotNull
    @Column(name="content")
    @Lob
//    private Blob content;
	private Blob content;
 
//	@NotNull
    @Column(name="content_type")
    private String contentType;
 
//	@NotNull
    @Column(name="regdate")
    private Date regdate;

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getClassname() {
		return classname;
	}

	public void setClassname(String classname) {
		this.classname = classname;
	}

	public int getSnum() {
		return snum;
	}

	public void setSnum(int snum) {
		this.snum = snum;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Blob getContent() {
		return content;
	}

	public void setContent(Blob content) {
		this.content = content;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

}
