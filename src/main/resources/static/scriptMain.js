var ttileMain = React.createElement('h1', null, 'Зачетная книжка');
var subMain = React.createElement('p', null, 'Это приложение для студентов');

var containerMain = React.createElement('div', { className: 'containerMain'},ttileMain, subMain);

ReactDOM.render(containerMain, document.getElementById('rootMain'));