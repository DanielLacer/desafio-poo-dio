package br.com.dio.desafio.dominio;

import java.util.*;

public class Dev {
    private String nome;
    private Set<Conteudo> conteudosInscritos = new LinkedHashSet<>();
    private Set<Conteudo> conteudosConcluidos = new LinkedHashSet<>();

    public void inscreverBootcamp(Bootcamp bootcamp){
        this.conteudosInscritos.addAll(bootcamp.getConteudos());
        bootcamp.getDevsInscritos().add(this);
    }

    public void progredir() {
        Optional<Conteudo> conteudo = this.conteudosInscritos.stream().findFirst();
        if(conteudo.isPresent()) {
            this.conteudosConcluidos.add(conteudo.get());
            this.conteudosInscritos.remove(conteudo.get());
        } else {
            System.err.println("Você não está matriculado em nenhum conteúdo!");
        }
    }

    public double calcularTotalXp() {
        return this.conteudosConcluidos
            .stream()
            .mapToDouble(Conteudo::calcularXp)
            .sum();
    }


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Set<Conteudo> getConteudosInscritos() {
        return conteudosInscritos;
    }

    public void setConteudosInscritos(Set<Conteudo> conteudosInscritos) {
        this.conteudosInscritos = conteudosInscritos;
    }

    public Set<Conteudo> getConteudosConcluidos() {
        return conteudosConcluidos;
    }

    public void setConteudosConcluidos(Set<Conteudo> conteudosConcluidos) {
        this.conteudosConcluidos = conteudosConcluidos;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dev dev = (Dev) o;
        return Objects.equals(nome, dev.nome) && Objects.equals(conteudosInscritos, dev.conteudosInscritos) && Objects.equals(conteudosConcluidos, dev.conteudosConcluidos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, conteudosInscritos, conteudosConcluidos);
    }
}

/*
 
    Conceitos de `hashCode` e `equals` em Java.

    1. `hashCode` e `equals`:
    
        - Ambos são métodos importantes em Java para trabalhar com coleções (como `HashSet`, `HashMap`, etc.) e garantir o correto funcionamento de estruturas de dados baseadas em hash.
        - Vamos entender cada um deles:

    2. `hashCode`:

        - O método `hashCode()` retorna um valor inteiro (um código hash) que representa o objeto.
        - Esse código é usado para otimizar a busca em estruturas de dados baseadas em hash, como tabelas de dispersão (hash tables).
        - A ideia é que objetos iguais (de acordo com o método `equals()`) devem ter o mesmo código hash.
        - Portanto, se você sobrescrever o método `equals()`, também é recomendado sobrescrever o método `hashCode()` para manter a consistência.
        - O contrato entre `equals()` e `hashCode()` é: se dois objetos são iguais (segundo `equals()`), seus códigos hash devem ser iguais.

    3. `equals`:

        - O método `equals(Object obj)` compara se dois objetos são semanticamente iguais.
        - Por padrão, ele verifica se as referências apontam para o mesmo objeto na memória (ou seja, se são o mesmo objeto).
        - No entanto, muitas vezes queremos comparar objetos com base em seus atributos (conteúdo) e não apenas na referência.
        - Portanto, é comum sobrescrever o método `equals()` nas classes que criamos.
        - O contrato para `equals()` é: se dois objetos têm o mesmo conteúdo (segundo a lógica definida na sobrescrita do método), eles devem ser considerados iguais.

    4. Por que precisamos de ambos?

        - O método `hashCode()` é usado para indexar objetos em estruturas de dados baseadas em hash (como tabelas de dispersão).
        - Quando você insere um objeto em um `HashSet`, por exemplo, o código hash é usado para determinar em qual "slot" da tabela o objeto será armazenado.
        - Quando você faz uma busca em um `HashSet`, o código hash é usado para encontrar rapidamente o slot correto.
        - O método `equals()` é usado para verificar se um objeto já está presente na estrutura (para evitar duplicatas).
        - Portanto, ambos são essenciais para o funcionamento correto dessas estruturas.
  
*/