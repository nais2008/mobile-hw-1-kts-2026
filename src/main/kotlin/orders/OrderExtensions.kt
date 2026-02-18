package orders

fun Order.applyDiscount(
    discountPercent: Int,
    logger: ((String) -> Unit)? = null
) {
    val currentProducts = products.toList()

    currentProducts.forEach { product ->
        val discountedPrice = product.price * (100 - discountPercent) / 100

        val discountedProduct = product.copy(price = discountedPrice)

        removeProductById(product.id)
        addProduct(discountedProduct)

        logger?.invoke("Product ${product.id} price changed from ${product.price} to $discountedPrice")
    }
}
