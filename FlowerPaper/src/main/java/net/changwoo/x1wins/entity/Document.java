package net.changwoo.x1wins.entity;

import java.sql.Blob;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
 
@Entity
@Table(name="documents")
public class Document {
 
    @Id
    @GeneratedValue
    @Column(name="id")
    private Integer id;
 
    @NotEmpty
    @Column(name="name")
    private String name;

    @NotEmpty
    @Column(name="description")
    private String description;
 

    @Column(name="filename")
    private String filename;
 
    @Column(name="content")
    @Lob
    private Blob content;
 
    @Column(name="content_type")
    private String contentType;
 
    @Column(name="created")
    private Date created;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}
 
    //Getter and Setter methods
    
    
}