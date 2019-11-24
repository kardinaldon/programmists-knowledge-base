new Vue ({
    el: '#main_page_content',
    data: {
        articles: null
    },
    mounted() {
        axios
            .get('../rest/article/all')
            .then(response => {
                this.articles = response.data;
            })
    },
    method: {
        getAllArticles(){
            axios
                .get('..rest/article/all')
                .then(response => {
                    this.articles = response.data;
                })
        },
    }
});