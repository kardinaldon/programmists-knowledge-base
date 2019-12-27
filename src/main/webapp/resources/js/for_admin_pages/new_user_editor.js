new Vue({
    el: '#editor_content',
    data: {
        url_list_of_users: '../administrator/list_of_users.html',
        email: '',
        password: '',
        role: '',
        statusEnum: ''
    },
    methods: {
        create_user() {
            axios
                .post('../rest/user/new',{ email: this.email, password: this.password, role: this.role, statusEnum: this.statusEnum})
                .then(response => {
                    alert("Пользователь создан");
                    window.location.href = this.url_list_of_users;
                })
                .catch(error => {
                    alert("Пользователь не создан");
                })
        }
    },
});
