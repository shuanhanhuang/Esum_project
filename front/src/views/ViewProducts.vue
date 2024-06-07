<template>
    <main>
        <Navbar />

        <!-- Table-->
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h1 class="text-center">管理商品</h1>
                    <!--Add button -->
                    <a href="/add" class="btn btn-primary">新增商品</a>
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
                          <tr v-for="product in products" :key="product.id">
                            <th scope="row">{{product.pid}}</th>
                            <td>{{product.name}}</td>
                            <td>{{product.price}}</td>
                            <td>{{product.quantity}}</td>
                            <td>
                              <button class="btn btn-danger mx-2" @click="deleteProduct(product.id)">Delete</button>
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
import Navbar from '../components/Navbar.vue'

    export default {
        name: 'ViewProducts',
        components: {
            Navbar
        },
        data() {
            return {
                products: []
            }
        },

        beforeMount(){
            this.getProducts()
        },

        methods: {
            getProducts(){
                fetch('http://localhost:8080/getProduct')
                .then(res => res.json())
                .then(data => {
                    this.products = data
                    console.log(data)
                })
            },
            deleteProduct(id){
                fetch(`http://localhost:8080/deleteProduct/${id}`, {
                    method: 'DELETE'
                })
                .then(data => {
                    console.log(data)
                    this.getProducts()
                })
            }
        }

    }

</script>