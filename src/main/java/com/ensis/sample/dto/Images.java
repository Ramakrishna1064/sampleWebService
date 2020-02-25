package com.ensis.sample.dto;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity  
@Table(name= "images")
public class Images {

		@Id		
		private int imgid;	
		@Column(name = "imagename")
		private String imagename;		
		@Column(name = "createdby")
		private String createdby;
		@Column(name = "createddate")
		private Date createddate;

		public int getImgid() {
			return imgid;
		}
		public void setImgid(int imgid) {
			this.imgid = imgid;
		}
		public String getImagename() {
			return imagename;
		}
		public void setImagename(String imagename) {
			this.imagename = imagename;
		}
		public String getCreatedby() {
			return createdby;
		}
		public void setCreatedby(String createdby) {
			this.createdby = createdby;
		}
		public Date getCreateddate() {
			return createddate;
		}
		public void setCreateddate(Date createddate) {
			this.createddate = createddate;
		}
}

