package egovframework.let.btCRUD.service;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;

import egovframework.com.cmm.ComDefaultVO;


public class BtCrudVO extends ComDefaultVO implements Serializable {

	private String crudId;
	private String crudSj;
	private String crudCn;
	private String crudNm;
	private Date crudPnttm;

	
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
	public String getCrudNm() {
		return crudNm;
	}
	public void setCrudNm(String crudNm) {
		this.crudNm = crudNm;
	}
	public Date getCrudPnttm() {
		return crudPnttm;
	}
	public void setCrudPnttm(Date crudPnttm) {
		this.crudPnttm = crudPnttm;
	}
	
	
}
