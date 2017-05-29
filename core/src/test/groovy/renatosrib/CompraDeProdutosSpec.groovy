package renatosrib

import entidades.Compra
import entidades.ItemDaCompra
import entidades.Produto
import spock.lang.Specification

import static org.junit.Assert.assertEquals


class CompraDeProdutosSpec extends Specification {
    Produto produto
    Compra compra

    def "Simula compra de produto cuja quantidade é inteira"() {
        given:
            this.produto = new Produto(nome: 'Chocolate em barra', preco: new BigDecimal(5.0));
            ItemDaCompra itemDaCompra = new ItemDaCompra(produto: produto, quantidade: 2);
            this.compra = new Compra();
        when:
            this.compra.adicionarItem(itemDaCompra);
        then:
            assertEquals(compra.subTotal(), 10, 0);
    }

    def "Simula compra de produto cuja quantidade é decimal"() {
        given:
            this.produto = new Produto(nome: 'Limão', preco: new BigDecimal(7.0));
            ItemDaCompra itemDaCompra = new ItemDaCompra(produto: produto, quantidade: 1.5);
            this.compra = new Compra();
        when:
            this.compra.adicionarItem(itemDaCompra);
        then:
            assertEquals(compra.subTotal(), 10.50, 0);
    }

    def "Simula compra de dois produtos em uma mesma compra"() {
        given:
            Produto limao = new Produto(nome: 'Limão', preco: new BigDecimal(7.0));
            Produto chocolate =  new Produto(nome: 'Chocolate em barra', preco: new BigDecimal(5.0));
            List<ItemDaCompra> items  = [
                    new ItemDaCompra(produto: limao, quantidade: 1.5),
                    new ItemDaCompra(produto: chocolate, quantidade: 2),
            ]
            this.compra = new Compra();

        when:
            //Adiciona itens à compra
            items.each { itemDaCompra ->
                this.compra.adicionarItem(itemDaCompra);

            }
        then:
            assertEquals(compra.subTotal(), 20.50, 0);
    }
}
