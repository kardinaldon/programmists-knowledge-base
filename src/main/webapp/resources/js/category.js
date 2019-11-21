
new Vue({
    el: '#category',
    data: {
        title: '',
        description: '',
        info: null,
        loading: false,
        errored: false
    },
    methods: {
        getCategoryList() {
            axios
                .get('../rest/category/get_all')
                .then(response => {
                    this.info = response.data;
                })
                .catch(error => {
                    console.log(error);
                    this.errored = true;
                })
                .finally(() => (this.loading = true));
        },
    },
});