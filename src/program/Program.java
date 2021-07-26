package program;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import entites.Produto;

public class Program {
	public static void main(String[] args) {
		Locale.setDefault(Locale.US);
		Scanner tx = new Scanner(System.in);
		List<Produto> product = new ArrayList<>();	
		
		
		// lendo onde o arquivo esta
		System.out.printf("Diga o caminho do arquivo:");
		File arquivo = new File(tx.nextLine());

		// lendo o arquivo
		try (BufferedReader file = new BufferedReader(new FileReader(arquivo))) {
			String linha = file.readLine();
			while(linha!=null) {
				String[] dividirLinha = linha.split(";");
				String nome = dividirLinha[0];
				Double valor = Double.parseDouble(dividirLinha[1]);
				Integer quantidade = Integer.parseInt(dividirLinha[2]);
				product.add(new Produto(nome,valor,quantidade));
				linha=file.readLine();
			}
		} 
		catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		}
		finally {
			tx.close();
		}
		
		
		// criar o arquivo que vai armazenar o novo texto
		boolean criado = new File(arquivo.getParent() + "\\out").mkdir();
		if (criado)	System.out.println("Pasta de Respoição criada com sucesso");
		try (BufferedWriter arquivoNovo = new BufferedWriter(new FileWriter(arquivo.getParent() + "\\out\\summary.csv", true))){
			for(Produto x:product) {
				arquivoNovo.write("Nome: "+x.getNome()+";"+"Valor total: "+x.calculaValorTotal());
				arquivoNovo.newLine();
			}
		} 
		catch (IOException e) {
			System.out.println("Erro: " + e.getMessage());
			e.printStackTrace();
		} 
		finally {
			tx.close();
		}
		tx.close();
	}
}
