package com.esouza.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.esouza.cursomc.domain.Categoria;
import com.esouza.cursomc.domain.Cidade;
import com.esouza.cursomc.domain.Cliente;
import com.esouza.cursomc.domain.Endereco;
import com.esouza.cursomc.domain.Estado;
import com.esouza.cursomc.domain.ItemPedido;
import com.esouza.cursomc.domain.Pagamento;
import com.esouza.cursomc.domain.PagamentoBoleto;
import com.esouza.cursomc.domain.PagamentoCartao;
import com.esouza.cursomc.domain.Pedido;
import com.esouza.cursomc.domain.Produto;
import com.esouza.cursomc.domain.enums.EstadoPagamento;
import com.esouza.cursomc.domain.enums.TipoCliente;
import com.esouza.cursomc.repositories.CategoriaRepository;
import com.esouza.cursomc.repositories.CidadeRepository;
import com.esouza.cursomc.repositories.ClienteRepository;
import com.esouza.cursomc.repositories.EnderecoRepository;
import com.esouza.cursomc.repositories.EstadoRepository;
import com.esouza.cursomc.repositories.ItemPedidoRepository;
import com.esouza.cursomc.repositories.PagamentoRepository;
import com.esouza.cursomc.repositories.PedidoRepository;
import com.esouza.cursomc.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Categoria cat1 = new Categoria(null, "Informatica");
		Categoria cat2 = new Categoria(null, "Escritorio");
		
		Produto p1 = new Produto(null, "computador", 2000.0);
		Produto p2 = new Produto(null, "Impressora", 200.0);
		Produto p3 = new Produto(null, "Mouse", 80.0);
		
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().add(p2);
		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		Estado e1= new Estado(null, "Minas gerais");
		Estado e2= new Estado(null, "São Paulo");
		
		Cidade c1 = new Cidade(null, "Uberlandia", e1);
		Cidade c2 = new Cidade(null, "São paulo cap", e2);
		Cidade c3 = new Cidade(null, "Campinas", e2);
		
		Cliente cli1 = new Cliente(null, "Maria", "mario@gmail.com", "123123123", TipoCliente.PESSOAFISICA);
		cli1.getTelefones().addAll(Arrays.asList("321", "12312332"));
		
		Endereco end1 = new Endereco(null, "rua flores", "300", "apt 203", "jardim", "12332123", cli1, c1);
		Endereco end2 = new Endereco(null, "rua matos", "105", "sala 800", "Centro", "12344123", cli1, c2);
		cli1.getEnderecos().addAll(Arrays.asList(end1,end2));
		
		e1.getCidades().addAll(Arrays.asList(c1));
		e2.getCidades().addAll(Arrays.asList(c2,c3));
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cli1, end1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), cli1, end2);
		
		Pagamento pag1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pag1);
		
		Pagamento pag2 = new PagamentoBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pag2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 200.0, 1);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 80.0, 2);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 800.0, 1);
		
		ped1.getItens().addAll(Arrays.asList(ip1, ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		estadoRepository.saveAll(Arrays.asList(e1,e2));
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		clienteRepository.saveAll(Arrays.asList(cli1));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
		
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		pagamentoRepository.saveAll(Arrays.asList(pag1,pag2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
	}

}
