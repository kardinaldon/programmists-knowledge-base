new Vue({
    el: '#editor_content',
    data: {
        title: '',
        smallDescription: '',
        description: '',
        categories: null,
        category_state: 'Выберите категорию',
        selected_category: null,
        category_keyword: '',
        info: null,
        loading: false,
        errored: false,
    },
    methods: {
        submitEntry() {
            axios
                .post('../rest/article/new_article',{ title: this.title, smallDescription: this.smallDescription, description: this.description, category: this.selected_category })
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
        getCategoryList() {
            axios
                .post('../rest/category/find_by_keyword',{key: this.category_keyword})
                .then(response => {
                    this.categories = response.data;
                    this.category_state = 'Категория выбрана';
                })
        }
    },
});
