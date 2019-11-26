new Vue ({
    el: '#main_page_content',
    data: {
        input_categories_list: [],
        output_categories_list: [{
            id: 1
        }, {
            id: 3,
            parent_id: 1
        },
            {
                id: 4,
                parent_id: 3
            }, {
                id: 5,
                parent_id: 3
            }, {
                id: 6,
                parent_id: 1
            }
        ]
    },
    mounted() {
        axios
            .get('../rest/article/all')
            .then(response => {
                this.input_categories_list = response.data;
            })
    },
    method: {
        getAllCategories (input_categories_list){
            let res = input_categories_list.reduce((acc, curr, index, orig) => {
                if (curr.categoryId) {
                    let parent = orig.find(item => {
                        return item.parentId === curr.categoryId;
                    });

                    (parent.children = parent.children || []).push(curr);
                    return acc;
                } else {
                    acc.push(curr);
                    return acc;
                }
            }, []);

            console.log(res);
        }
    }
});