package com.esouza.cursomc.domain.enums;

public enum TipoCliente {
	
	PESSOAFISICA(1, "Pessoa Fisica"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");

	private String nome;
	private Integer id;

	TipoCliente(Integer id, String nome) {
		this.id = id;
		this.nome = nome;
		
	}

	public String getNome() {
		return nome;
	}

	public Integer getId() {
		return id;
	}
	
	public static TipoCliente toEnum(Integer id) {
		if(id == null)
			return null;
		
		for (TipoCliente Ti : TipoCliente.values()) {
			if(Ti.getId() == id)
				return Ti;
		}
		
		throw new IllegalArgumentException("id TipoCliente invalido: " + id);
	}
}
