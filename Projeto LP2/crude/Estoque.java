package crude;

import java.util.ArrayList;
import java.util.List;

public class Estoque {
    private List<Produto> produtos; 
    
    public Estoque(){
        produtos = new ArrayList<>(); 
    }

    public void adicionarProduto(Produto produto){
        produtos.add(produto); // Adiciona o produto à lista
    }

    public Produto consultarProduto(String codigo){
        for (Produto produto : produtos) { // Itera sobre a lista de produtos
            if (produto.getCodigo().equals(codigo)) {
                return produto; // Retorna o produto se encontrado
            }
        }
        return null; // Retorna null se o produto não for encontrado
    }

    public void alterarProduto(String codigo, Produto novoProduto){
        for (int i = 0; i < produtos.size(); i++) {
            Produto produto = produtos.get(i);
            if (produto.getCodigo().equals(codigo)) {
                produtos.set(i, novoProduto); // Atualiza o produto na lista
                return;
            }
        }
    }

    public void excluirProduto(String codigo){
        produtos.removeIf(produto -> produto.getCodigo().equals(codigo)); // Remove o produto da lista se encontrado
    }

    public void listarProdutos(){
        if(produtos.isEmpty()){
            System.out.println("Nenhum produto cadastrado");
        } else {
            for (Produto produto : produtos) { // Itera sobre a lista de produtos
                System.out.println(produto); // Imprime cada produto
            }
        }
    }
}
