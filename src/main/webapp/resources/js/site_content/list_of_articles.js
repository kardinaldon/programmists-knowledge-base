new Vue({
    el: '#main_page_content',
    data() {
        return {
            count: null,
            part_of_articles: null,
            start_article: 0, //first category number 0
            limit_of_articles_list: 10,
            loading: false,
            errored: false,
            selected: '10',
            options: [
                { text: '10', value: 10 },
                { text: '20', value: 20 },
                { text: '50', value: 50 },
                { text: '100', value: 100}
            ],

        };
    },
    // filters: {
    //     currencydecimal(value) {
    //         return value.toFixed(2);
    //     }
    // },
    mounted() {
        axios
            .get('../rest/article/count')
            .then(response => {
                this.count = response.data;
            })
            .catch(error => {
                console.log(error);
                this.errored = true;
            })
            .finally(() => (this.loading = false));
        axios
            .get('../rest/article/part',{params: {start: this.start_article, limit: this.limit_of_articles_list}})
            .then(response => {
                this.part_of_articles = response.data;
            })
            .catch(error => {
                console.log(error);
                this.errored = true;
            })
            .finally(() => (this.loading = false));
    }
});