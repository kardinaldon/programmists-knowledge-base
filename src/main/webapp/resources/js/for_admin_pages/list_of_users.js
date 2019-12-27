new Vue ({
    el: '#main_page_content',
    data: {
        users: null,

    },
    mounted() {
        axios
            .get('../rest/user/all')
            .then(response => {
                this.users = response.data;
            })
    },
    methods: {
        get_users() {
            axios
                .get('../rest/user/all')
                .then(response => {
                    this.users = response.data;
                })
        },
        delete_user(n) {
            axios.delete('../rest/user/' + n)
                .then(response => {
                    this.get_users();
                    alert("Пользователь удалён");
                })
                .catch(error => {
                    alert("Пользователь не может быть удалён");
                });
        }
    }
});