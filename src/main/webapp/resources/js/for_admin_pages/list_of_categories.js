new Vue ({
    el: '#main_page_content',
    data: {

    },
    mounted() {
        axios
            .get('../rest/category/all')
            .then(response => {
                categories = response.data;

                var node = document.getElementById('tree');
                node.innerHTML = '';

                if (categories.length) {
                    var ul = document.createElement('ul');
                    ul.setAttribute("id", "category");
                    var tree = fetchChildElement(ul);
                    node.appendChild(tree);
                }

                function fetchChildElement(container, left, right) {
                    categories.filter(filterItems);
                    return container;

                    function filterItems(item) {
                        if (item.left === (left || 1)) {
                            var element = document.createElement('li');
                            element.setAttribute("id", "category");
                            element.innerHTML = item.title;
                            if (item.left + 1 < item.right) {
                                var childContainer = document.createElement('ul');
                                childContainer.setAttribute("id", "category");
                                var child = fetchChildElement(childContainer, item.left + 1, item.right - 1);
                                element.appendChild(child);
                            }

                            container.appendChild(element);

                            if (right && item.right < right) {
                                fetchChildElement(container, item.right + 1, right);
                            }
                        }
                    }
                }
            })
    },
});