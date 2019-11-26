// Отключим ненужные для примера
// сообщения в консоли.
Vue.config.productionTip = false
Vue.config.devtools = false

const App = {
    template: `
<a-layout-content style="padding: 50px">
  <a-tree class="draggable-tree" draggable @drop="onDrop" @select="edit" :treeData="gData" />
  <a-button type="primary" @click="printResult">Результат</a-button><hr />
  <table>
    <thead>
      <tr><th>ID</th><th>Name</th><th>Parent</th></tr>
    </thead>
    <tbody>
      <tr v-for="item in inData.elements">
        <td>{{ item.id }}</td>
        <td>{{ item.name }}</td>
        <td>{{ item.parent }}</td>
      </tr>
    </tbody>
  </table>
</a-layout-content>`,
    data() {
        return {
            gData: [],
            inData: {
                "id": 3,
                "name": "Основной заголовок",
                "description": "Основное описание",
                "company_id": 1,
                "owner_id": 1,
                "elements": [
                    {"id":8,"name":"Заголовок 1","position":0,"description":"","parent":3,"type_change":1,"answer_from":0,"answer_to":10},
                    {"id":7,"name":"Заголовок 2","position":0,"description":"","parent":6,"type_change":1,"answer_from":0,"answer_to":10},
                    {"id":1,"name":"Заголовок 3","position":0,"description":"","parent":0,"type_change":1,"answer_from":0,"answer_to":10,"answer_list":["Да","Нет"],"need_photo":true,"need_video":false},
                    {"id":2,"name":"Заголовок 4","position":0,"description":"","parent":1,"type_change":1,"answer_from":0,"answer_to":10},
                    {"id":3,"name":"Заголовок 5","position":0,"description":"","parent":2,"type_change":1,"answer_from":0,"answer_to":10},
                    {"id":4,"name":"Заголовок 6","position":1,"description":"","parent":1,"type_change":1,"answer_list":["Да","Нет"]},
                    {"id":5,"name":"Заголовок 7","position":1,"description":"","parent":1,"type_change":1,"answer_list":["Да","Нет"]},
                    {"id":6,"name":"Заголовок 8","position":0,"description":"","parent":0,"type_change":1,"answer_from":0,"answer_to":10}
                ]
            }
        }
    },

    mounted() {
        // Отсортируем данные для визуализации снипета.
        this.inData.elements.sort((a, b) => a.id - b.id);

        // Создадим дубликат массива объектов только с необходимым набором свойств.
        // Исходные данные НЕ меняем!
        let nested = this.inData.elements.map(function(item) {
            return {
                key: item.id,
                title: 'ID: '+item.id+'. Name: '+item.name,
                parent: item.parent,
                position: item.position
            }
        });

        // Добавим дочерние элементы.
        nested.forEach(function(item, index, array) {
            item.children = array.filter(subItem => item.key === subItem.parent);
        });

        // Оставим только корневые элементы.
        this.gData = nested.filter(item => item.parent === 0);
    },

    methods: {
        edit(key, event) {
            key = parseInt(key)
            if (key > 0) {
                let element = this.inData.elements.find(i => i.id === key);
                console.log(element)
            }
            console.log({event})
        },
        printResult() {
            // Используем стрелочные функции для поднятия контекста.
            const setParent = (children, key) => {
                // Перебираем всех потомков.
                children.forEach((item) => {
                    let element = this.inData.elements.find(i => i.id === item.key);
                    // Меняем исходные данные!
                    element.parent = key;

                    // Отправляем потомков текущего объекта на перебор.
                    setParent(item.children, item.key);
                })
            }

            // Всем корневым элементам ставим `parent = 0`.
            setParent(this.gData, 0);
        },
        onDrop(info) {
            // Дальше идет метод из документации.
            // Скопируйте его повторно из документации.
            const dropKey = info.node.eventKey
            const dragKey = info.dragNode.eventKey
            const dropPos = info.node.pos.split('-')
            const dropPosition = info.dropPosition - Number(dropPos[dropPos.length - 1])
            const loop = (data, key, callback) => {
                data.forEach((item, index, arr) => {
                    if (item.key === key) return callback(item, index, arr)
                    if (item.children) return loop(item.children, key, callback)
                })
            }
            const data = [...this.gData]
            let dragObj
            loop(data, dragKey, (item, index, arr) => {
                arr.splice(index, 1)
                dragObj = item
            })
            if (!info.dropToGap) {
                loop(data, dropKey, (item) => {
                    item.children = item.children || [];
                    item.children.push(dragObj);
                });
            } else if ((info.node.children || []).length > 0 && info.node.expanded && dropPosition === 1) {
                loop(data, dropKey, (item) => {
                    item.children = item.children || [];
                    item.children.unshift(dragObj);
                });
            } else {
                let ar, i;
                loop(data, dropKey, (item, index, arr) => {
                    ar = arr;
                    i = index;
                });
                dropPosition === -1 ? ar.splice(i, 0, dragObj) : ar.splice(i + 1, 0, dragObj);
            }
            this.gData = data;
        },
    }
}

new Vue({
    el: '#main_page_content',
    components: {
        'app': App
    },
    template: '<app></app>'
});