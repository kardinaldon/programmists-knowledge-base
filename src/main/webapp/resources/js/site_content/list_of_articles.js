new Vue({
    el: '#main_page_content',
    data() {
        return {
            count: null,
            part_of_articles: null,
            start_article: 0, //first category number 0
            limit_of_articles_list: 10,
            selected: 10,
            options: [
                { text: '10', value: 10 },
                { text: '20', value: 20 },
                { text: '50', value: 50 },
                { text: '100', value: 100}
            ],

        };
    },
    mounted() {
        axios
            .get('../rest/article/count')
            .then(response => {
                this.count = response.data;
                axios
                    .get('../rest/article/part',{params: {start: this.start_article, limit: this.selected}})
                    .then(response => {
                        this.part_of_articles = response.data;
                    });
            });

    },
    methods: {
        onChange(event) {
            axios
                .get('../rest/article/part',{params: {start: this.start_article, limit: event.target.value}})
                .then(response => {
                    this.part_of_articles = response.data;
                });
        }
    }

});