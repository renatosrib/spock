package entidades

/**
 * Representa uma compra
 */
class Compra {
    List<ItemDaCompra> itens = []

    boolean adicionarItem(ItemDaCompra itemDaCompra) {
        itens.add(itemDaCompra)
    }

    BigDecimal subTotal() {
        BigDecimal subTotal = 0
        itens.each {
            subTotal += it.produto.preco * it.quantidade
        }
        return subTotal
    }
}
