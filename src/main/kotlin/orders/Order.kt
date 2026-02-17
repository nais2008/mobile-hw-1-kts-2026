package orders

class Order(
    val id: Int
) : PriceCalculator {

    private val _products: MutableList<Product> = mutableListOf()

    /** Read-only view of products in the order. */
    val products: List<Product>
        get() = _products.toList()

    var status: OrderStatus = OrderStatus.Created
        private set

    fun addProduct(product: Product?) {
        if (product !is Product) {
            return
        }

        _products.add(product)
    }

    fun removeProductById(productId: Int) {
        for (i in 0 until _products.size) {
            if (_products[i].id == productId) {
                _products.removeAt(i)

                return
            }
        }
    }

    override fun calculateTotal(): Int {
        var sum = 0

        _products.forEach {
            sum += it.price
        }

        return sum
    }

    fun pay() {
        if (_products.isEmpty()) {
            throw IllegalStateException()
        }

        status = OrderStatus.Paid
    }
    
    fun cancel(reason: String?) {
        status = OrderStatus.Cancelled(reason ?: "Unknown reason")
    }
}
