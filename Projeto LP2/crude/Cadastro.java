package crude;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Cadastro {

    private Estoque estoque;
    private Map< Integer,Categoria > categorias;
    private Scanner scanner;

    public Cadastro(){

        estoque = new Estoque();
        scanner = new Scanner(System.in);
        categorias = new HashMap<>();
        
    }

    public void mostrarMenu(){

        System.out.println("\n============Menu============");
        System.out.println("1. Incluir novo produto");
        System.out.println("2. Consultar um produto");
        System.out.println("3. Alterar dados de um produto");
        System.out.println("4. Excluir dados de um produto");
        System.out.println("5. Imprimir lista de produtos");
        System.out.println("6. Adicionar nova categoria");
        System.out.println("7. Sair");
    }

    public void incluirProduto(){
        double preco = 0;
        boolean precoValido = false;
        
        System.out.println("Codigo do produto: ");
        String codigo = scanner.nextLine();
        System.out.println("Nome do produto: ");
        String nome = scanner.nextLine();
        
        while (!precoValido) {
            try {
                System.out.print("Preço do produto: ");
                preco = Double.parseDouble(scanner.nextLine());
                precoValido = true;
            } catch (NumberFormatException e) {
                System.out.println("Preço inválido. Digite um número válido.");
            }
        }
        System.out.println("Categorias disponiveis: ");
        for(Map.Entry<Integer, Categoria> entry : categorias.entrySet()){
            System.err.println(entry.getKey() + "."+ entry.getValue().getNome());
        }

        System.out.println("Codigo da categoria: ");
        int codCategoria = Integer.parseInt(scanner.nextLine());
        Categoria categoria = categorias.get(codCategoria);

        if(categoria != null){
            Produto produto = new ProdutoSimples(codigo, nome, preco, categoria);
            estoque.adicionarProduto(produto);
            System.out.println("Produto adicionado com sucesso!");
        }
        else{
            System.out.println("Categoria inválida. Produto não adicionado. ");
        }
    }

    public void consultarProduto(){
        System.out.println("Codigo do produto: ");
        String codigo = scanner.nextLine();
        Produto produto = estoque.consultarProduto(codigo);

        if(produto != null){
            System.out.println(produto);
        }
        else{
            System.out.println("Produto não encontrado");
        }
    }

    public void alterarProduto(){
        System.out.println("Codigo do produto a ser alterado: ");
        String codigo = scanner.nextLine();
        Produto produtoExistente = estoque.consultarProduto(codigo);
        if (produtoExistente != null) {
            System.out.println("Novo nome (atual: "+produtoExistente.getNome() +"): ");
            String nome = scanner.nextLine();

            if(nome.isEmpty()){
                nome = produtoExistente.getNome();
            }
            System.out.println("Novo preco (atual: "+ produtoExistente.getPreco()+"): ");
            String precoInput = scanner.nextLine();
            double preco = precoInput.isEmpty() ? produtoExistente.getPreco() :  Double.parseDouble(precoInput);
            System.out.println("Categorias disponiveis: ");

            for(Map.Entry<Integer , Categoria> entry : categorias.entrySet()){
                System.out.println(entry.getKey() + "."+ entry.getValue().getNome());
            }
            System.out.println("Codigo da nova categoria: ");
            String categoriaInput = scanner.nextLine();
            Categoria categoria = categoriaInput.isEmpty()? produtoExistente.getCategoria() : categorias.get(Integer.parseInt(categoriaInput));
           
            Produto novoProduto = new ProdutoSimples(codigo, nome, preco, categoria);
            estoque.alterarProduto(codigo, novoProduto);
            System.out.println("Produto alterado com sucesso! ");
        }
        else{
            System.out.println("Produto não encontrado.");
        }
    }

    public void excluirProduto(){
        System.out.println("Código do produto a ser excluido: ");
        String codigo = scanner.nextLine();

        if(estoque.consultarProduto(codigo) != null){
            estoque.excluirProduto(codigo);
            System.out.println("Produto excluido com sucesso !");
        }
        else{
            System.out.println("Produto não encontrado.");
        }
    }

    public void imprimirListaProdutos(){
        estoque.listarProdutos();
    }

    public void adicionarCategoria(){
        System.out.print("Código da nova categoria: ");
        int codigo = Integer.parseInt(scanner.nextLine());
        System.out.print("Nome da nova categoria: ");
        String nome = scanner.nextLine();
        categorias.put(codigo, new Categoria(codigo, nome));
        System.out.println("Categoria adicionada com sucesso!");
    }

    public void executar(){
        while(true){
            mostrarMenu();
            String opcao = scanner.nextLine();

            switch(opcao){
                case "1":
                    incluirProduto();
                    break;
                case "2":
                    consultarProduto();
                    break;
                case "3":
                    alterarProduto();
                    break;
                case "4":
                    excluirProduto();
                    break;
                case "5":
                    imprimirListaProdutos();
                    break;
                case "6":
                    adicionarCategoria();
                    break;
                case "7":
                    System.out.println("Saindo...");
                    return;
                default:
                System.out.println("Opção invalida.Tente novamente. ");
            }
        }
    }
}
