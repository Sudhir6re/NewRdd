package com.mahait.gov.in.common;

import java.util.ArrayList;

public class JsonResponseBean {

	private int totalRows;
	private ArrayList<Object> resultData;
	private String message;
	private String status;
	private Object data;
	private String resultCode;
	private ArrayList<Object> charges;
	private String dataValue;
	private String srnId;
	private Long id;
	public String getDataValue() {
		return dataValue;
	}
	public void setDataValue(String dataValue) {
		this.dataValue = dataValue;
	}
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public ArrayList<Object> getResultData() {
		return resultData;
	}
	public void setResultData(ArrayList<Object> resultData) {
		this.resultData = resultData;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getResultCode() {
		return resultCode;
	}
	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}
	public ArrayList<Object> getCharges() {
		return charges;
	}
	public void setCharges(ArrayList<Object> charges) {
		this.charges = charges;
	}
	public String getSrnId() {
		return srnId;
	}
	public void setSrnId(String srnId) {
		this.srnId = srnId;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
}
