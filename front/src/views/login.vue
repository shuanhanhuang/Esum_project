<template>
  <div class="container">
    <h1>Login</h1>
    <form @submit.prevent="login" class="form">
      <div class="form-group">
        <label for="username" class="form-label">帳號:</label>
        <input type="text" id="username" v-model="username" class="form-control" required>
        <div class="form-bar"></div>
      </div>
      <div class="form-group">
        <label for="password" class="form-label">密碼:</label>
        <input type="password" id="password" v-model="password" class="form-control" required>
        <div class="form-bar"></div>
      </div>
      <button type="submit" class="btn btn-primary btn-block">Login</button>
    </form>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data() {
    return {
      username: '',
      password: ''
    };
  },
  methods: {
    login() {
      fetch('http://localhost:8080/login', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({
          username: this.username,
          password: this.password
        })
      })
      .then(response => {
        console.log(response);
        if (response.ok) {
          // 將用戶名稱存儲到 localStorage
          localStorage.setItem('currentUser', this.username);

          if (this.username === 'admin') {
            this.$router.push('/home');
          } else if (this.username != 'admin') {
            this.$router.push('/shop');
          } else {
            this.$router.push('/home');
          }
        } else {
          alert("登入失敗");
          this.username = '';
          this.password = '';
        }
      })
      .catch(error => {
        console.error('Error:', error);
      });
    }
  }
};
</script>
  
  <style scoped>
  .form {
    max-width: 400px;
    margin: 0 auto;
  }
  
  .form-group {
    position: relative;
    margin-bottom: 1.5rem;
  }
  
  .form-label {
    font-size: 0.875rem;
    font-weight: bold;
    color: #333;
  }
  
  .form-control {
    width: 100%;
    padding: 0.5rem;
    font-size: 1rem;
    border: none;
    border-bottom: 1px solid #ccc;
    outline: none;
    transition: border-color 0.3s ease;
  }
  
  .form-control:focus {
    border-color: #007bff;
  }
  
  .form-bar {
    position: absolute;
    bottom: 0;
    left: 0;
    width: 0;
    height: 2px;
    background-color: #007bff;
    transition: width 0.3s ease;
  }
  
  .form-control:focus + .form-bar {
    width: 100%;
  }
  </style>
  