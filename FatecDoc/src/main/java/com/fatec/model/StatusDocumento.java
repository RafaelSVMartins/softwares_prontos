package com.fatec.model;

public enum StatusDocumento {
	PENDENTE("pendente"),
	RECEBIDO("recebido");
	
	private String descricao;
	
	StatusDocumento(String descricao) {
		this.descricao=descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
}
