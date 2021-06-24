package com.esouza.cursomc.domain.enums;

public enum EstadoPagamento {
	
	PENDENTE(1, "Pendente"),
	QUITADO(2, "Quitado"),
	CANCELADO(2, "Cancelado");

	private String nome;
	private Integer id;

	EstadoPagamento(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
	}

	public String getNome() {
		return nome;
	}

	public Integer getId() {
		return id;
	}
	
	public static EstadoPagamento toEnum(Integer id) {
		if(id == null)
			return null;
		
		for (EstadoPagamento Ti : EstadoPagamento.values()) {
			if(Ti.getId() == id)
				return Ti;
		}
		
		throw new IllegalArgumentException("id TipoCliente invalido: " + id);
	}
}
