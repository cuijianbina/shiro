package com.trs.shiro.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import lombok.Data;

@Data
@Entity
@Table(name="user")
public class User {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@Expose
	@SerializedName("newname")
	private String name;
	
	private String password;
	
	private Integer status;

	@Override
	public String toString() {
		return "{\"id\":\""+id+"\",\"name\":\""+ name+"\"}";
	}
	
	
}
