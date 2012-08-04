package net.changwoo.app.user.entity;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
 
@Entity
@Table(name="user")
public class User {
	
	public User(){
		this.status = 1;
		this.regdate = new Date();
		this.level = 1;
	}
 
	
    @Id
    @Column(name="num", nullable = false)
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int num;
    
    @NotEmpty
    @Size(min = 1, max = 20)
    @Column(name="userid", unique = true, nullable = false, length = 100)
    private String userid;
    
    @NotEmpty(message = "Password must not be blank.")
    @Size(min = 1, max = 10, message = "Password must between 1 to 10 Characters.")
    @Column(name="password", nullable = false, length = 100)
    private String password;
     
    @NotEmpty
    @Size(min = 1, max = 20)
    @Column(name="name", nullable = false, length = 100)
    private String name;
    
    @Column(name="level", nullable = false, insertable = false, updatable = true, columnDefinition = "int default 1")
    private int level;
    
    @NotEmpty
    @Size(min = 1, max = 20)
    @Column(name="phone", nullable = false, length = 100)
    private String phone;
     
    @NotEmpty
    @Size(min = 1, max = 20)
    @Column(name="email", nullable = false, length = 100)
    private String email;
    
    
     
//    @Column(name="picture", nullable = false, length = 100)
//    private String picture;
     
    @Column(name = "status", nullable = false, insertable = false, updatable = true, columnDefinition = "int default 1")
    private int status;
     
    @Temporal(TemporalType.TIMESTAMP) 
    private Date regdate;
    
//    @Lob
//    @Fetch(FetchMode.SELECT)
//    @Type(type="org.hibernate.type.PrimitiveByteArrayBlobType")
//    private InputStream image;
//    @Column(name="image")
//    @Lob
//    private MultipartFile image;
    
//    @Column(name="image")
//    @Lob
//    private Blob image;
    
    @Column(name="filenum")
    private int filenum;
    
    @Column(name="filename")
    private String filename;
 
    @Column(name="content")
    @Lob
    private Blob content;
 
    @Column(name="content_type")
    private String contentType;
    
    
    
//    @Column(name="file")
//    @Lob
//    private Blob file;
    
 
//    public InputStream getImage() {
//		return image;
//	}
//
//	public void setImage(InputStream image) {
//		this.image = image;
//	}

//	public MultipartFile getImage() {
//		return image;
//	}
//
//	public void setImage(MultipartFile image) {
//		this.image = image;
//	}
    
    
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

	public int getFilenum() {
		return filenum;
	}

	public void setFilenum(int filenum) {
		this.filenum = filenum;
	}

//	public Blob getImage() {
//    	return image;
//    }
//    
//    public void setImage(Blob image) {
//    	this.image = image;
//    }
	
//    public Blob getFile() {
//    	return file;
//    }
//    
//    
//    public void setFile(Blob file) {
//    	this.file = file;
//    }

	public Date getRegdate() {
        return this.regdate;
    }
 



	public void setRegdate(Date regdate) {
        this.regdate = regdate;
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
 
    public String getPassword() {
        return password;
    }
 
    public void setPassword(String password) {
        this.password = password;
    }
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public int getLevel() {
        return level;
    }
 
    public void setLevel(int level) {
        this.level = level;
    }
 
    public String getPhone() {
        return phone;
    }
 
    public void setPhone(String phone) {
        this.phone = phone;
    }
 
    public String getEmail() {
        return email;
    }
 
    public void setEmail(String email) {
        this.email = email;
    }
 
//    public String getPicture() {
//        return picture;
//    }
// 
//    public void setPicture(String picture) {
//        this.picture = picture;
//    }
 
    public int getStatus() {
        return status;
    }
 
    public void setStatus(int status) {
        this.status = status;
    }
 
//  public Date getRegDate() {
//      return regDate;
//  }
//
//  public void setRegDate(Date regDate) {
//      this.regDate = regDate;
//  }
     
}