<template>
    <main>
        <Navbar2 />

        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h1 class="text-center">購買商品</h1>
                    <form @submit.prevent="submitPurchase">
                        <div class="form-group">
                            <label for="productId">商品編號</label>
                            <input type="text" v-model="productId" class="form-control" id="productId" readonly>
                        </div>
                        <div class="form-group">
                            <label for="count">購買數量</label>
                            <select v-model="count" class="form-control" id="count" required>
                                <option v-for="num in availableQuantities" :key="num" :value="num">{{ num }}</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="itemPrice">單價</label>
                            <input type="text" v-model="itemPrice" class="form-control" id="itemPrice" readonly>
                        </div>
                        <div class="form-group">
                            <label for="totalPrice">總價</label>
                            <input type="text" v-model="standPrice" class="form-control" id="totalPrice" readonly>
                        </div>
                        <button type="submit" class="btn btn-primary mt-2" >提交</button>
                    </form>
                </div>
            </div>
        </div>
    </main>
</template>

<script>
import Navbar2 from '../components/Navbar2.vue';

export default {
    name: 'Order',
    components: {
        Navbar2
    },
    data() {
        return {
            productId: '',
            itemPrice: 0,
            count: 1,
            standPrice: 0,
            availableQuantities: [],
            currentUser: null,
        }
    },
    watch: {
        count(newCount) {
            this.calculateStandPrice();
        }
    },
    methods: {
        submitPurchase() {
            const orderData = {
                pid: this.productId,
                itemprice: this.itemPrice,
                standprice: this.standPrice,
                count: this.count,
                mid: this.currentUser // 使用 this.currentUser 作為 mid 的值
            };

            fetch('http://localhost:8080/addOrder', {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(orderData)
            })
            .then(response => {
                if (!response.ok) {
                    throw new Error('Network response was not ok');
                }
                return response.json();
            })
            .then(data => {
                alert('此商品已成功加入購物車！');
                this.$router.push({ path: '/shop' });
            })
            .catch(error => {
                console.error('There was an error!', error);
            });
        },
        fetchProductInfo(productId) {
            fetch(`http://localhost:8080/getProduct/${productId}`)
                .then(response => response.json())
                .then(data => {
                    this.itemPrice = data.price;
                    this.productId = data.pid;
                    this.availableQuantities = Array.from({ length: data.quantity }, (_, i) => i + 1);
                    this.calculateStandPrice();
                })
                .catch(error => {
                    console.error('Error fetching product information:', error);
                });
        },
        calculateStandPrice() {
            this.standPrice = this.itemPrice * this.count;
        }
    },
    mounted() {
        this.productId = this.$route.query.productId;
        this.fetchProductInfo(this.productId);
        this.currentUser = localStorage.getItem('currentUser'); // 從 localStorage 獲取 currentUser
    }
}
</script>