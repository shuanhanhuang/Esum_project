import { createRouter, createWebHistory } from 'vue-router'
import ViewProducts from '../views/ViewProducts.vue'
import order from '../views/Order.vue'
import Shop from '../views/Shop.vue'
import Cart from '../views/cart.vue'
import login from '../views/login.vue'
import Manager from '../views/Manager.vue'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'login',
      component: login
    },
    {
      path: '/shop',
      name: 'shop',
      component: Shop
    },
    {
      path: '/add',
      name: 'add',
      component: () => import('../views/AddProduct.vue')
    },
    {
      path: '/home',
      name: 'home',
      component: ViewProducts
    },
    {
      path: '/order',
      name: 'order',
      component: order
    },
    {
      path: '/cart',
      name: 'cart',
      component: Cart
    },
    {
      path: '/Manager',
      name: 'Manager',
      component: Manager
    },
    
  ]
})

export default router
