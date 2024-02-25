var editingMode = { rect: 0, line: 1 };

function Pencil(ctx, drawing, canvas) {
	this.currEditingMode = editingMode.line;
	this.currLineWidth = 5;
	this.currColour = '#000000';
	this.currentShape = 0;

	document.getElementById('butRect').onclick = (_) => this.
	currEditingMode = editingMode.rect
	document.getElementById('butLine').onclick = (_) => this.
	currEditingMode = editingMode.line
	document.getElementById('spinnerWidth').onchange = (e) => this.
	currLineWidth = e.target.value
	document.getElementById('colour').onchange = (e) => this.
	currColour = e.target.value

	new DnD(canvas, this);

	this.onInteractionStart = function(dnd) {
	}.bind(this);

	this.onInteractionUpdate= function(dnd) {
		if(this.currEditingMode == editingMode.rect) {
			this.currentShape = new Rectangle(
			  dnd.initialX,
			  dnd.initialY,
			  this.currColour,
			  this.currLineWidth,
			  dnd.finalX - dnd.initialX,
			  dnd.finalY - dnd.initialY
		  );
		} else {
			this.currentShape = new Line(
			  dnd.initialX,
			  dnd.initialY,
			  this.currColour,
			  this.currLineWidth,
			  dnd.finalX,
			  dnd.finalY
		  );
    }
    drawing.paint(ctx, canvas);
    this.currentShape.paint(ctx);
	}.bind(this);

	this.onInteractionEnd = function(dnd) {
    var uuid = generateUUID();
    console.log(uuid);
    drawing.shapeArray.set(uuid, this.currentShape);
		drawing.paint(ctx, canvas);
    updateShapeList(uuid, this.currentShape);
    document.getElementById("remove" + uuid).onclick =
    (event) => remove(drawing, event.currentTarget.id.substring(6), ctx, canvas)
	}.bind(this);
};

function remove(drawing, index, ctx, canvas) {
  console.log(index);
  drawing.shapeArray.delete(index);
  document.getElementById("liRemove" + index).remove();
  drawing.paint(ctx, canvas);
}

function generateUUID() {
  var d = new Date().getTime();
  if (typeof performance !== 'undefined' && typeof performance.now === 'function'){
      d += performance.now();
  }
  return 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
      var r = (d + Math.random()*16)%16 | 0;
      d = Math.floor(d/16);
      return (c=='x' ? r : (r&0x3|0x8)).toString(16);
  });
}