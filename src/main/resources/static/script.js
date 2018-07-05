

var title = React.createElement('h1', null, 'Привет, гость!');
var subHello = React.createElement('p', null, 'Зачетная книжка МАГУ');
//контейнер для обертки
var container = React.createElement('div', { className: 'container'},title, subHello);

ReactDOM.render(container, document.getElementById('root'));
