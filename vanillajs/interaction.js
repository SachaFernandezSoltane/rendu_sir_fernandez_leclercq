function DnD (canvas, interactor) {
  this.mousePressed = false;
  this.initialX = 0;
  this.initialY = 0;
  this.finalX = 0;
  this.finalY = 0;
  this.interactor = interactor;

  this.handleMouseDown = function(evt) {
    console.log("testCLICKED");
    this.mousePressed = true;
    var mousePos = this.getMousePosition(canvas, evt);
    this.finalX = mousePos.x;
    this.finalY = mousePos.y;
    this.interactor.onInteractionStart(this);
  }.bind(this);

  this.handleMouseMove = function(evt) {
    console.log("testMOVE");
    if (this.mousePressed) {
      var mousePos = this.getMousePosition(canvas, evt);
      this.finalX = mousePos.x;
      this.finalY = mousePos.y;
      interactor.onInteractionUpdate(this);
    }
  }.bind(this);

  this.handleMouseUp = function(evt) {
    console.log("testLACHER");
    const mousePos = this.getMousePosition(canvas, evt);
      this.mousePressed = false;
      this.finalX = mousePos.x;
      this.finalY = mousePos.y;
      interactor.onInteractionEnd(this);
  }.bind(this);

  this.getMousePosition = function(evt) {
    var rect = canvas.getBoundingClientRect();
    return {
      x: evt.clientX - rect.left,
      y: evt.clientY - rect.top
    };
  }.bind(this);
}