Rectangle.prototype.paint = function(ctx) {
  ctx.strokeStyle = this.color;
  ctx.lineWidth = this.thickness;
  ctx.strokeRect(this.startX, this.startX, this.width, this.height);
};
  
Line.prototype.paint = function(ctx) {
  ctx.strokeStyle = this.color;
  ctx.lineWidth = this.thickness;
  ctx.beginPath();
  ctx.moveTo(this.startX, this.startY);
  ctx.lineTo(this.endX, this.endY);
  ctx.stroke();
};
  
Drawing.prototype.paint = function(ctx) {
  ctx.fillStyle = '#F0F0F0';
  ctx.fillRect(0, 0, canvas.width, canvas.height);
  this.shapeArray.forEach(element => element.paint(ctx));
};
  
function updateShapeList(index, shape) {
  document.getElementById('shapeList').insertAdjacentHTML('beforeend', toDOM(shape, index))
}

function toDOM(shape, index) {
  var shapeList = document.getElementById('shapeList');
  var listItem = document.createElement('li');

  var span = document.createElement('span');
  span.textContent = shape.type + ' ' + index;

  var deleteButton = document.createElement('button');
  deleteButton.setAttribute('type', 'button');
  deleteButton.setAttribute('class', 'btn btn-default');

  var icon = document.createElement('span');
  icon.setAttribute('class', 'glyphicon glyphicon-remove-sign');

  deleteButton.appendChild(icon);

  deleteButton.addEventListener('click', function() {
      shapeList.removeChild(listItem);
  });

  listItem.appendChild(span);
  listItem.appendChild(deleteButton);

  shapeList.appendChild(listItem);
}