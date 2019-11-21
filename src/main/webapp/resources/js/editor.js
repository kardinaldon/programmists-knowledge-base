new Vue({
    el: '#editor_content',
    data: {
        title: '',
        smallDescription: '',
        description: '',
        info: null,
        loading: false,
        errored: false
    },
    methods: {
        submitEntry() {
            axios
                .post('../rest/article/new_article',{ title: this.title, smallDescription: this.smallDescription, description: this.description })
                .then(response => {
                    this.info = response.data;
                })
                .catch(error => {
                    console.log(error);
                    this.errored = true;
                })
                .finally(() => (this.loading = true));
        },
        // update: _.debounce(function (e) {
        //     this.description = e.target.value
        // }, 300),
    },
});
