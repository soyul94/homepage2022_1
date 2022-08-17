package egovframework.let.crud2.service;

import java.io.Serializable;
import java.util.Date;

import egovframework.com.cmm.ComDefaultVO;

public class Crud2VO extends ComDefaultVO implements Serializable{
	
	private String crudId;
	private String crudSj;
	private String crudCn;
	private String userNm;
	private Date frstRegistPnttm;
	
	
	public String getCrudId() {
		return crudId;
	}
	public void setCrudId(String crudId) {
		this.crudId = crudId;
	}
	public String getCrudSj() {
		return crudSj;
	}
	public void setCrudSj(String crudSj) {
		this.crudSj = crudSj;
	}
	public String getCrudCn() {
		return crudCn;
	}
	public void setCrudCn(String crudCn) {
		this.crudCn = crudCn;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public Date getFrstRegistPnttm() {
		return frstRegistPnttm;
	}
	public void setFrstRegistPnttm(Date frstRegistPnttm) {
		this.frstRegistPnttm = frstRegistPnttm;
	}

}
