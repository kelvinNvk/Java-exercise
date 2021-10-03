public class Produtos extends ControleEstoque {
    private String nome;
    private double preco;
    private String unidade;
    private int quantidade;

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) { this.nome = nome; }

/**/

    public double getPreco() {
        return this.preco;
    }

    public void setPreco(double preco1) {
        if (preco1 < 1) {
            invalido();
            return;
        } else {
            this.preco = preco1;
        }
    }

/**/

    public String getUnidade() {
        return this.unidade;
    }

    public void setUnidade(String unidade) { this.unidade = unidade; }

/**/

    public int getQuantidade() {
        return this.quantidade;
    }

    public void setQuantidade(int quantidade) {
        if (quantidade < 0) {
            qtdinvalido();
            return;
        } else {
                this.quantidade = quantidade;
        }
    }
/**/

    @Override
    public String toString() {
        return "NOME: " + nome + "\n" +
                "PREÇO: " + preco + "\n" +
                "UNIDADE: " + unidade + "\n" +
                "QUANTIDADE: " + quantidade;
    }

    /*tratamentos*/
    public void invalido() {
        System.out.println("Preço digitado inválido, por favor digite um preço maior que 0" + "\n"
                + "\n");
    }

    public void qtdinvalido() {
        System.out.println("Quantidade inválida, por favor digita uma quantidade maior ou igual a 0" + "\n"
                + "\n");
    }

    public void setAdicionarQuantidade(int quantidade) {
        this.quantidade+=quantidade;
    }

    public void setDiminuirQuantidade(int quantidade) {
        this.quantidade-=quantidade;
    }

    public void setAumentarPreco(int preco) {
        this.preco+=preco;
    }

    public void setDiminuirPreco(int preco) {
        this.preco-=preco;
    }

}
