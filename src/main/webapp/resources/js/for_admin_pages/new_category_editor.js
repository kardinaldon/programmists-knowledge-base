new Vue({
    el: '#editor_content',
    data: {
        title: '',
        description: '',
        categories: null,
        category_state: 'Выберите родительскую категорию',
        selected_category: null,
        category_keyword: '',
        info: null,
        loading: false,
        errored: false,
    },
    methods: {
        submitEntry() {
            axios
                .post('../rest/category/new',{ title: this.title, description: this.description, parentId: this.selected_category.categoryId })
                .then(response => {
                    this.info = response.data;
                })
                .catch(error => {
                    console.log(error);
                    this.errored = true;
                })
                .finally(() => (this.loading = true));
        },
        getCategoryList() {
            axios
                .get('../rest/category/key',{params: {key: this.category_keyword}})
                .then(response => {
                    this.categories = response.data;
                    this.category_state = 'Родительская категория выбрана';
                })
        }
    },
});
