<template>
    <main>
        <Navbar2 />

        <!-- Table-->
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h1 class="text-center">購物車商品</h1>
                    <table class="table table-striped">
                        <thead>
                          <tr>
                            <th scope="col">商品編號</th>
                            <th scope="col">數量</th>
                            <th scope="col">售價</th>
                            <th scope="col">總價</th>
                          </tr>
                        </thead>
                        <tbody>
                          <tr v-for="order in Orders" :key="order.id">
                            <th scope="row">{{order.pid}}</th>
                            <td>{{order.count}}</td>
                            <td>{{order.itemprice}}</td>
                            <td>{{order.standprice}}</td>
                          </tr>
                          <tr>
                            <td colspan="3" class="text-right">總價：</td>
                            <td>{{ totalStandPrice }}</td>
                          </tr>
                          <tr>
                            <button type="submit" class="btn btn-primary mt-2" @click="confirmPurchase">確定購買</button>
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
    name: 'Cart',
    components: {
        Navbar2
    },
    data() {
        return {
            Orders: [],
            currentUser: null
        }
    },
    computed: {
        totalStandPrice() {
            return this.Orders.reduce((total, order) => total + order.standprice, 0);
        }
    },
    beforeMount() {
        this.getCurrentUser();
        this.getOrders();
    },
    methods: {
        getCurrentUser() {
            this.currentUser = localStorage.getItem('currentUser');
        },
        getOrders() {
            fetch('http://localhost:8080/getOrder')
                .then(res => res.json())
                .then(data => {
                    this.Orders = data.filter(order => order.mid === this.currentUser);
                });
        },
        confirmPurchase() {
            const orderPids = this.Orders.map(order => order.pid);
            const oid = this.generateRandomOID();
            const price = this.totalStandPrice;

            Promise.all(
                orderPids.map(pid =>
                    fetch(`http://localhost:8080/getProductpid/${pid}`)
                        .then(res => res.json())
                        .then(product => {
                            const updatedStock = product.quantity - this.Orders.find(order => order.pid === pid).count;

                            if (updatedStock >= 0) {
                                // 更新 Orders 表中对应商品的 quantity 字段
                                return fetch(`http://localhost:8080/updateProduct/${pid}?quantity=${updatedStock}`, {
                                    method: 'PUT',
                                    headers: {
                                        'Content-Type': 'application/json'
                                    }
                                })
                                .then(() => {
                                    // 成功更新库存后，删除订单
                                    const order = this.Orders.find(order => order.pid === pid);
                                    this.deleteOrder(order.id);
                                });
                            } else {
                                console.error('Insufficient stock for product:', pid);
                                return Promise.resolve();
                            }
                        })
                        .catch(error => {
                            console.error('Error fetching product data:', error);
                            return Promise.resolve();
                        })
                )
            )
            .then(() => {
                const paystatus = 1;

                const orderManagerData = {
                    mid: this.currentUser, // 使用当前登录用户的用户名
                    oid: oid,
                    price: price,
                    paystatus: paystatus
                };

                fetch('http://localhost:8080/addOrdermanager', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(orderManagerData)
                })
                .then(() => {
                    alert('訂單已送出');
                    this.$router.push('/shop');
                })
                .catch(error => {
                    console.error('Error adding ordermanager:', error);
                });
            })
            .catch(error => {
                console.error('Error updating product stock:', error);
            });
        },
        deleteOrder(id) {
            fetch(`http://localhost:8080/deleteorder/${id}`, {
                method: 'DELETE'
            })
            .then(() => {
                this.Orders = this.Orders.filter(order => order.id !== id);
            })
            .catch(error => {
                console.error('Error deleting order:', error);
            });
        },
        generateRandomOID() {
            const oid = 'A' + Math.random().toString(36).substr(2, 9); // 生成 'A' 开头的随机字符串
            return oid;
        }
    }
}
</script>
