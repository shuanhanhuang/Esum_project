<template>
    <main>
        <Navbar2 />

        <!-- Table-->
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h1 class="text-center">商品總攬</h1>
                    <table class="table table-striped">
                        <thead>
                          <tr>
                            <th scope="col">商品編號</th>
                            <th scope="col">商品名稱</th>
                            <th scope="col">售價</th>
                            <th scope="col">庫存</th>
                          </tr>
                        </thead>
                        <tbody>
                          <tr v-for="product in filteredProducts" :key="product.id">
                            <th scope="row">{{product.pid}}</th>
                            <td>{{product.name}}</td>
                            <td>{{product.price}}</td>
                            <td>{{product.quantity}}</td>
                            <td>
                              <button class="btn btn-danger mx-2" @click="buyProduct(product.id)">購買</button>
                            </td>
                          </tr>
                        </tbody>
                      </table>
                </div>
            </div>
        </div>
        
    </main>
</template>

<script>
import Navbar2 from '../components/Navbar2.vue';

export default {
    name: 'Shop',
    components: {
        Navbar2
    },
    data() {
        return {
            products: []
        }
    },

    computed: {
        filteredProducts() {
            return this.products.filter(product => product.quantity > 0);
        }
    },

    beforeMount() {
        this.getProducts();
    },

    methods: {
        getProducts() {
                fetch('http://localhost:8080/getProduct')
                .then(res => res.json())
                .then(data => {
                    this.products = data
                })
            
        },
        buyProduct(id) {
            this.$router.push({ path: '/order', query: { productId: id }});
        }
    }
}
</script>

