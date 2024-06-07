<template>
    <main>
        <Navbar />
        <div class="my-5">
            <div class="mx-auto w-25 " style="max-width:100%;">
              <h2 class="text-center mb-3">Add Product</h2>
              <form @submit.prevent="addProduct">
                <!--id-->
                <div class="row">
                  <div class="col-md-12 form-group mb-3">
                    <label for="id" class="form-label">商品編號</label>
                    <input id="id"  type="text" name="id" class="form-control" placeholder="商品編號" required v-model="product.pid">
                  </div>
                </div>

                
                <!--name-->
                <div class="row">
                    <div class="col-md-12 form-group mb-3">
                      <label for="name" class="form-label">商品名稱</label>
                      <input id="name" type="name"  name="name" class="form-control" placeholder="商品名稱" required v-model="product.name" >
                    </div>
                  </div>

                <!--price-->
                  <div class="row">
                    <div class="col-md-12 form-group mb-3">
                      <label for="price" class="form-label">售價</label>
                      <input id="price" type="text"  name="price" class="form-control" placeholder="售價" required v-model="product.price" >
                    </div>
                  </div>
      
                <!--quantity-->
                  <div class="row">
                    <div class="col-md-12 form-group mb-3">
                      <label for="quantity" class="form-label">庫存</label>
                      <input id="quantity" type="text"  name="quantity" class="form-control" placeholder="庫存" required v-model="product.quantity" >
                    </div>
                  </div>
               
                
               
                <div class="row">
                  <div class="col-md-12 form-group">
                    <input class="btn btn-primary w-100" type="submit" value="Submit">
                  </div>
                </div>
      
                <div>
                  
                </div>
              </form>
        
            </div>
          </div>
    </main>
</template>


<script>
import Navbar from '../components/Navbar.vue';

    export default {
        name: 'AddProduct',
        components: {
            Navbar
        },

        data() {
            return {
              product : {
                pid:'',
                name: '',
                price: '',
                quantity: ''
              }
            }
        },

        methods: {
            addProduct(){
                fetch('http://localhost:8080/addProduct', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json'
                    },
                    body: JSON.stringify(this.product)
                })
                .then(data => {
                    this.$router.push("/home");
                })

            }
        },
            
    }


</script>