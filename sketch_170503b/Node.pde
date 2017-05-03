class Node{
  
  int name; 
  int x, y;
  int r, g, b;
  
  public Node (int _name, int _x, int _y){
    name = _name;
    x = _x;
    y = _y;
    r = (int)random(255);
    g = (int)random(255);
    b = (int)random(255);
  }
  
  public void show(){
    fill(r, g, b);
    ellipse(x * 50, y * 50, 20, 20);
  }
}