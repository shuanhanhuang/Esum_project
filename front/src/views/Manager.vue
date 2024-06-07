<template>
    <main>
        <Navbar />

        <!-- Table-->
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h1 class="text-center">訂單總攬</h1>
                    <table class="table table-striped">
                        <thead>
                          <tr>
                            <th scope="col">顧客帳號</th>
                            <th scope="col">訂單編號</th>
                            <th scope="col">訂單金額</th>
                            <th scope="col">付款狀態</th>
                          </tr>
                        </thead>
                        <tbody>
                          <tr v-for="order in orders" :key="order.id">
                            <th scope="row">{{order.mid}}</th>
                            <td>{{order.oid}}</td>
                            <td>{{order.price}}</td>
                            <td>{{order.paystatus}}</td>
                            
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
        name: 'Manager',
        components: {
            Navbar
        },
        data() {
            return {
                orders: []
            }
        },

        beforeMount(){
            this.getOrders()
        },

        methods: {
            getOrders(){
                fetch('http://localhost:8080/getOrdermanager')
                .then(res => res.json())
                .then(data => {
                    this.orders = data
                    console.log(data)
                })
            }
        }

    }

</script>