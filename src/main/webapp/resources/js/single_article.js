new Vue({
    el: '#single_page_app',
    data() {
        return {
            info: null,
            loading: false,
            errored: false
        };
    },
    // filters: {
    //     currencydecimal(value) {
    //         return value.toFixed(2);
    //     }
    // },
    mounted() {
        axios
            .get('../rest/article/getwitid?id=')
            .then(response => {
                this.info = response.data;
            })
            .catch(error => {
                console.log(error);
                this.errored = false;
            })
            .finally(() => (this.loading = false));
    }
});