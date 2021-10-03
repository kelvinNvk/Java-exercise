import java.util.ArrayList;
import java.util.Scanner;
import java.util.Locale;


public class ControleEstoque {

    private Produtos produtosList[] = new Produtos[5];
    private int posicaoAtual = 0;
    private String nomeConsultaAlterar = "";
    public static void main(String[] args) {

        ControleEstoque app = new ControleEstoque();
        app.tituloMenu();
        app.telaPrincipal();
    }

    private void telaPrincipal() {
        int opcao = 0;
        do {
            opcao = opcaoMenuPadrao();
            switch (opcao) {
                case 1:
                    menuCadastroProdutos();
                    break;
                case 2:
                    menuMovimentacao();
                    break;
                case 3:
                    menuReajustePrecos();
                    break;
                case 4:
                    relatorioProdutos();
                    break;
                case 0:
                    System.out.println("Saindo do sistema");
                    break;
                default:
                    opcaoInvalida();
                    break;
            }
        } while (opcao != 0);
    }

    private void menuMovimentacao() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("MOVIMENTAÇÃO");
        System.out.println("1 - ENTRADA\n" +
                "2 - SAÍDA\n" +
                "0 - RETORNAR\n" +
                "OPÇÃO  : \n");
        int opcaoMovimentacao = scanner.nextInt();
        switch (opcaoMovimentacao) {
            case 1:
                entradaProduto();
                break;
            case 2:
                saidaProduto();
                break;
            case 0:
                System.out.println("Retornando para o menu");
                break;
            default:
                opcaoInvalida();
                break;
        }
    }

    /**/

    private void menuReajustePrecos() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("MENU REAJUSTE DE PREÇOS");
        System.out.println("1 - Aumentar preço\n" +
                "2 - Diminuir preço\n" +
                "0 - RETORNAR\n" +
                "OPÇÃO  : \n");
        int opcaoMovimentacao = scanner.nextInt();
        switch (opcaoMovimentacao) {
            case 1:
                aumentarPreco();
                break;
            case 2:
                diminuirPreco();
                break;
            case 0:
                System.out.println("Retornando para o menu");
                break;
            default:
                opcaoInvalida();
                break;
        }
    }

    /**/

    private void aumentarPreco() {
        String escolha;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("REAJUSTE DE PREÇOS");
            System.out.println("Nome do produto para aumentar o preço");
            String nomeProduto = scanner.nextLine();
            Produtos produtosMovimentacao;
            boolean controle=true;
            for (int i = 0; i < posicaoAtual; i++) {
                if (nomeProduto.equalsIgnoreCase(produtosList[i].getNome())) {
                    controle=false;
                    produtosMovimentacao = produtosList[i];
                    System.out.println("PREÇO ATUAL : " + produtosMovimentacao.getPreco());
                    System.out.println("AUMENTO: ");
                    int quantidadeEntrada = scanner.nextInt();
                    System.out.println("PREÇOFINAL : " + (produtosMovimentacao.getPreco() + quantidadeEntrada));
                    escolha = confirmaOperacao();
                    if (escolha.equalsIgnoreCase("S")) {
                        produtosMovimentacao.setAumentarPreco(quantidadeEntrada);
                        produtosList[i] = produtosMovimentacao;
                    }
                    break;
                }
            }
            mensagemConsultaNaoEncontrada(controle);

            escolha = getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
    }
    /**/

    private void diminuirPreco() {
        String escolha;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("CONSUMO DOS PRODUTOS");
            System.out.println("Nome do produto");
            String nomeProduto = scanner.nextLine();

            boolean controle = true;
            for (int i = 0; i < posicaoAtual; i++) {
                if (nomeProduto.equalsIgnoreCase(produtosList[i].getNome())) {
                    controle=false;
                    Produtos produtosMovimentacao = produtosList[i];
                    System.out.println("QTDE ATUAL : " + produtosMovimentacao.getPreco());
                    System.out.println("QTDE SAÍDA : ");
                    int quantidadeSaida = scanner.nextInt();
                    System.out.println("QTDE FINAL : " + (produtosMovimentacao.getPreco() - quantidadeSaida));
                    if (produtosMovimentacao.getPreco() < quantidadeSaida) {
                        System.out.println("Quantidade indevida");
                        break;
                    }
                    escolha = confirmaOperacao();
                    if (escolha.equalsIgnoreCase("S")) {
                        produtosMovimentacao.setDiminuirPreco(quantidadeSaida);
                        produtosList[i] = produtosMovimentacao;
                    }
                    break;
                }
            }
            mensagemConsultaNaoEncontrada(controle);
            escolha = getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
    }

/**/

    private void relatorioProdutos() {
        this.tituloMenu();
        System.out.println("RELATÓRIO");
        for (int i = 0; i < posicaoAtual; i++) {
            System.out.println("\n");
            System.out.println("Produtos: \n" +
                    "CÓDIGO: " + i + "\n" +
                    produtosList[i]);

        }
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n\n");
        System.out.println("APERTE QUALQUER LETRA + ENTER PARA CONTINUAR");
        scanner.next();
    }

    /**/

    private void saidaProduto() {
        String escolha;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("CONSUMO DOS PRODUTOS");
            System.out.println("Nome do produto");
            String nomeMantimento = scanner.nextLine();

            boolean controle = true;
            for (int i = 0; i < posicaoAtual; i++) {
                if (nomeMantimento.equalsIgnoreCase(produtosList[i].getNome())) {
                    controle=false;
                    Produtos produtosMovimentacao = produtosList[i];
                    System.out.println("QTDE ATUAL : " + produtosMovimentacao.getQuantidade());
                    System.out.println("QTDE SAÍDA : ");
                    int quantidadeSaida = scanner.nextInt();
                    System.out.println("QTDE FINAL : " + (produtosMovimentacao.getQuantidade() - quantidadeSaida));
                    if (produtosMovimentacao.getQuantidade() < quantidadeSaida) {
                        System.out.println("Quantidade maior que no estoque, saída não é possível");
                        break;
                    }
                    escolha = confirmaOperacao();
                    if (escolha.equalsIgnoreCase("S")) {
                        produtosMovimentacao.setDiminuirQuantidade(quantidadeSaida);
                        produtosList[i] = produtosMovimentacao;
                    }
                    break;
                }
            }
            mensagemConsultaNaoEncontrada(controle);
            escolha = getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
    }

    private void entradaProduto() {
        String escolha;
        do {
            Scanner scanner = new Scanner(System.in);
            System.out.println("COMPRA DE PRODUTOS");
            System.out.println("Nome do produto");
            String nomeMantimento = scanner.nextLine();
            Produtos produtosMovimentacao;
            boolean controle=true;
            for (int i = 0; i < posicaoAtual; i++) {
                if (nomeMantimento.equalsIgnoreCase(produtosList[i].getNome())) {
                    controle=false;
                    produtosMovimentacao = produtosList[i];
                    System.out.println("QTDE ATUAL : " + produtosMovimentacao.getQuantidade());
                    System.out.println("QTDE ENTRADA : ");
                    int quantidadeEntrada = scanner.nextInt();
                    System.out.println("QTDE FINAL : " + (produtosMovimentacao.getQuantidade() + quantidadeEntrada));
                    escolha = confirmaOperacao();
                    if (escolha.equalsIgnoreCase("S")) {
                        produtosMovimentacao.setAdicionarQuantidade(quantidadeEntrada);
                        produtosList[i] = produtosMovimentacao;
                    }
                    break;
                }
            }
            mensagemConsultaNaoEncontrada(controle);

            escolha = getRepetirOperacao();

        } while (escolha.equalsIgnoreCase("S"));
        menuMovimentacao();
    }



    /**
     * Cadastrar Produto menu
     **/
    private void menuCadastroProdutos() {
        int opcao;
        this.tituloMenu();
        System.out.println("CADASTRO DE PRODUTOS");
        System.out.println("1 - INCLUSÃO\n" +
                "2 - ALTERAÇÃO\n" +
                "3 - CONSULTA\n" +
                "4 - EXCLUSÃO\n" +
                "0 - RETORNAR\n");
        opcao = getEscolhaMenu();
        switch (opcao) {
            case 1:
                cadastrarProduto();
                break;
            case 2:
                alterarProduto();
                break;
            case 3:
                consultaProduto();
                break;
            case 4:
                excluirProduto();
            case 0:
                System.out.println("Retornando para o menu principal");
                break;
            default:
                opcaoInvalida();
                break;
        }
    }

    /**/

    /**
     * Inclusão de Produto
     **/

    private void cadastrarProduto() {
        String escolha;
        do {
            this.tituloMenu();
            System.out.println("CADASTRO DE PRODUTOS");
            Produtos produtos = setDadosDoMantimento();
            if (produtos.getPreco() < 1) {
                cadastrarProduto();
            } else
            {
                escolha = confirmaOperacao();
                if (escolha.equalsIgnoreCase("S")) {
                    produtosList[posicaoAtual] = produtos;
                    posicaoAtual++;
                }
            }
            System.out.println("Produto cadastrado com sucesso!" + "\n");
            escolha = getRepetirOperacao();
        }   while (escolha.equalsIgnoreCase("S"));
        menuCadastroProdutos();
}
/**/
    private Produtos setDadosDoMantimento() {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.ENGLISH);
        System.out.println("Informe o nome do produto");
        String nome = scanner.nextLine();
        System.out.println("Informe o preco do produto");
        double preco1 = scanner.nextDouble();
        System.out.println("Informe a unidade de medida");
        scanner = new Scanner(System.in);
        String unidade = scanner.nextLine();
        System.out.println("Informe a quantidade");
        int quantidade = scanner.nextInt();

        Produtos produtos = new Produtos();
        produtos.setNome(nome);
        produtos.setPreco(preco1);
        produtos.setUnidade(unidade);
        produtos.setQuantidade(quantidade);
        return produtos;
    }
/***** inclusão finalizado *****/

/**/

/*** Alteração de produto ****/
private void alterarProduto() {
    String escolha;
    do {
        Scanner scanner = new Scanner(System.in);
        this.tituloMenu();
        System.out.println("ALTERAÇÃO DE PRODUTO");
        System.out.println("Informe o nome do produto para alterar");
        nomeConsultaAlterar = scanner.nextLine();
        boolean controle = true;
        for (int i = 0; i < posicaoAtual; i++) {

            if (nomeConsultaAlterar.equalsIgnoreCase(produtosList[i].getNome())) {
                controle = false;
                System.out.println("PRODUTO ENCONTRADO\n");
                System.out.println("Não é possível alterar o nome de um produto, somente os atributos.\n" +
                        "Caso deseje alterar o nome do produto é necessário criar um novo produto!\n");
                System.out.println(produtosList[i].toString()+"\n");
                Produtos produtos = setAlterarDados();
                escolha = confirmaOperacao();
                if (escolha.equalsIgnoreCase("S")) {
                    produtosList[i] = produtos;
                    System.out.println(produtosList[i].toString()+"\n");
                }
                break;
            }
        }
        mensagemConsultaNaoEncontrada(controle);
        escolha = getRepetirOperacao();

    } while (escolha.equalsIgnoreCase("S"));
    menuCadastroProdutos();
}
    private Produtos setAlterarDados() {
    double preco1 = 0.0;
        String nome = nomeConsultaAlterar;
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.ENGLISH);
        System.out.println("Informe o preço do produto");
        preco1 = Double.parseDouble(scanner.nextLine());
        System.out.println("Informe a unidade de medida");
        String unidade = scanner.nextLine();
        System.out.println("Informe a quantidade");
        int quantidade = scanner.nextInt();

        Produtos produtos = new Produtos();
        produtos.setNome(nome);
        produtos.setPreco(preco1);
        produtos.setUnidade(unidade);
        produtos.setQuantidade(quantidade);
        return produtos;

    }

/*** Alteração de produto finalizada ****/

/**/

/*** Consulta de Produto ***/
private void consultaProduto() {

    String escolha;
    do {
        Scanner scanner = new Scanner(System.in);
        this.tituloMenu();
        System.out.println("CONSULTA DE PRODUTOS");
        System.out.println("Informe o nome do produto para pesquisa");
        String nomeConsulta = scanner.nextLine();
        boolean controle=true;
        for (int i = 0; i < posicaoAtual; i++) {
            if (nomeConsulta.equalsIgnoreCase(produtosList[i].getNome())) {
                controle=false;
                System.out.println(produtosList[i].toString());
                break;
            }
        }
        mensagemConsultaNaoEncontrada(controle);
        escolha = getRepetirOperacao();

    } while (escolha.equalsIgnoreCase("S"));
    menuCadastroProdutos();
}
/*** consulta de produto finalizada **/

/**/

/*** excluir produto ***/
private void excluirProduto() {

    String escolha;
    do {
        Scanner scanner = new Scanner(System.in);
        this.tituloMenu();
        System.out.println("EXCLUSÃO DE PRODUTOS");
        System.out.println("Informe o nome do produto para pesquisa");
        String nomeConsulta = scanner.nextLine();
        boolean controle = true;
        for (int i = 0; i < posicaoAtual; i++) {
            scanner = new Scanner(System.in);
            if (nomeConsulta.equalsIgnoreCase(produtosList[i].getNome())) {
                controle=false;
                System.out.println(produtosList[i].toString());
                System.out.println("CONFIRMA EXCLUSÃO ( S/N ) ?");
                escolha = scanner.next();
                if (escolha.equalsIgnoreCase("S")) {
                    for (int j = i; j < posicaoAtual; j++) {
                        produtosList[j] = produtosList[j + 1];
                        posicaoAtual--;
                    }
                }
                break;
            }
        }
        mensagemConsultaNaoEncontrada(controle);
        System.out.println(("Produto excluido com sucesso!" + "\n"));
        escolha = getRepetirOperacao();

    } while (escolha.equalsIgnoreCase("S"));
    menuCadastroProdutos();
}
/*** exclusão de produto finalizada ***/




/****************METODOS PADRAO***************************/
private int opcaoMenuPadrao() {
    int opcao;
    System.out.println("MENU PRINCIPAL\n" +
            "1 - CADASTRO DE PRODUTOS\n" +
            "2 - MOVIMENTAÇÃO\n" +
            "3 - REAJUSTE DE PREÇOS\n" +
            "4 - RELATÓRIOS\n" +
            "0 - FINALIZAR\n" +
            "OPÇÃO  : _\n");
    opcao = getEscolhaMenu();
    return opcao;
}

    /**/

    private void mensagemConsultaNaoEncontrada(boolean controle) {
        if (controle) {
            System.out.println("Produto não encontrado, por favor digite um produto cadastrado ou cadastre um produto.");
        }
    }

    /**/

    private void opcaoInvalida() {
        System.out.println("Opção inválida");
    }

    /**/

    private String getRepetirOperacao() {
        Scanner scanner = new Scanner(System.in);
        String escolha;
        System.out.println("REPETIR OPERAÇÃO ( S/N ) ? ");
        escolha = scanner.next();
        return escolha;
    }

    /**/

    private String confirmaOperacao() {
        Scanner scanner = new Scanner(System.in);
        String escolha;
        System.out.println("CONFIRMA OPERAÇÃO ( S/N ) ?");
        escolha = scanner.next();
        return escolha;
    }

    /**/

    private int getEscolhaMenu() {
        Scanner scanner = new Scanner(System.in);
        return Integer.parseInt(scanner.next());
    }


    /**/

    private void tituloMenu() {
        System.out.println("EMPRESA DE IMPORTAÇÃO DE PRODUTOS LTDA.\n" +
                "SISTEMA DE CONTROLE DE ESTOQUE \n");
    }

}
