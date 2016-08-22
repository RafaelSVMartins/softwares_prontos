package com.fatec.service;

import java.util.Calendar;

public class ChecaData {
	
	private Calendar datateste;
	private Calendar data1;
	private String resposta;
	public ChecaData(Calendar datateste) {
		this.datateste = datateste;
	}
	
	public String testeCalendar() {
		data1 = Calendar.getInstance();
		data1.add(Calendar.MONTH,-2);
		
		if(data1.after(datateste)) {
			return this.resposta = "Data anterior a dois meses!";
		} else if(data1.before(datateste)) {
			return this.resposta = "";
		} else{
			return resposta="";
		}
	}
}
