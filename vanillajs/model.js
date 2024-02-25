function Drawing(){
  this.shapeArray = new Map()
}

function Shape(startX, startY, color, lineWidth){
  this.startX = startX;
  this.startY = startY;
  this.color = color;
  this.lineWidth = lineWidth;
}

function Rectangle(startX, startY, color, lineWidth, width, height){
  Shape.call(this, startX, startY, color, lineWidth)
    this.width = width;
    this.height = height;
}

function Line(startX, startY, color, lineWidth, endX, endY){
  Shape.call(this, startX, startY, color, lineWidth)
    this.endX = endX;
    this.endY = endY;
}