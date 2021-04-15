package br.edu.ifsc.canoinhas.ads.clienterest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import br.edu.ifsc.canoinhas.ads.clienterest.controller.ControllerClient2;
import br.edu.ifsc.canoinhas.ads.clienterest.entidade.Dado;
import br.edu.ifsc.canoinhas.ads.clienterest.thread.ThreadRecuperarTodosDados;

public class Main2 {
	public static void main(String[] args) {
		ControllerClient2 controllerClient2 = new ControllerClient2();

		while (true) {

			System.out.println("Opções");
			System.out.println("1) Buscar última leitura");
			System.out.println("2) Buscar leitura por hora");
			System.out.println("3) Buscar leitura por id");
			System.out.println("4) Sair");
			System.out.print("Digite uma opção:");

			int op = new Scanner(System.in).nextInt();

			ThreadRecuperarTodosDados recuperarTodosDados = new ThreadRecuperarTodosDados(controllerClient2);
			recuperarTodosDados.run();

			switch (op) {

			case 1:
				controllerClient2.recuperarUltimoDado();
				Dado dado = controllerClient2.recuperarUltimoDadoLeitura();
				if (dado == null) {
					System.out.println("Dado não encontrado!");
				} else {
					System.out.println("Dado:" + dado);
				}
				break;
			case 2:
				System.out.println("Digite a hora:");
				String hora = new Scanner(System.in).next();
				controllerClient2.recuperarPorHora(hora);
				break;
			case 3:

				System.out.print("Digite o id:");
				String id = new Scanner(System.in).next();
				controllerClient2.recuperarPorIDDado(id);
				break;
			}
			if (op == 4) {
				System.out.println("Você saiu!");
				break;
			}
		}
	}
}
