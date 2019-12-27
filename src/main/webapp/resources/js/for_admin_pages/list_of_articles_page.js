new Vue ({
    el: '#main_page_content',
    data: {
        articles: null,

    },
    mounted() {
        axios
            .get('../rest/article/all')
            .then(response => {
                this.articles = response.data;
            })
    },
    methods: {
        get_articles() {
            axios
                .get('../rest/article/all')
                .then(response => {
                    this.articles = response.data;
                })
        },
        delete_article(n) {
            axios.delete('../rest/article/' + n)
                .then(response => {
                    this.get_articles();
                    alert("Статья удалена");
                })
                .catch(error => {
                    alert("Статья не может быть удалена");
                });
        }
    }
});