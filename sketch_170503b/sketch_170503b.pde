boolean edge[][] = new boolean[20][20];
Node nodes[] = new Node[20];

void setup() {
  size(750, 750);
  translate(25, 25);
  for (int i = 0; i < 20; i++) {
    nodes[i] = new Node(i, floor(i / 4), i % 4);
    for (int j = 0; j < i - 1; j++) {
      if (random(100) < 15 ) {
        edge[i][j] = true;
      }
    }
  }

  for (int i = 0; i < 20; i++) {
    nodes[i].show();
    for (int j = 0; j < i - 1; j++) {
      if (edge[i][j]) {
        line(nodes[i].x * 50, nodes[i].y * 50, nodes[j].x * 50, nodes[j].y * 50);
      }
    }
  }
}