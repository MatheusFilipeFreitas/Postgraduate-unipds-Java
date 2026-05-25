package com.mathffreitas.basics;

import java.lang.IO;
public class Main {
    public static void main(String[] args) {
    String name = "Sanduiche de Presunto do Chaves";
    String descricao = "Sanduiche de presunto simples, mas feito com muito amor";
    boolean emPromocao = true;
    double preco = 3.50;
    double precoComDesconto = 2.99;
    long id = 3_000_000_000L;
    double porcentagemDesconto = preco - precoComDesconto / preco;
    IO.println("Porcentagem desconto: " + porcentagemDesconto);

    double valorDesconto = preco * precoComDesconto;
    IO.println("Valor desconto: " + valorDesconto);

    int x = 11;
    int y = 2;
    int z = x / y;
    IO.println(z);

    int w = x % y;
    IO.println(w);


}
}
