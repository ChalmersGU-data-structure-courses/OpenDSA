int range(Node root, int min, int max) {
  if (root == null)
    return 0;
  int result = 0;
  if (min <= root.key && root.key <= max)
    result = result + 1;
  if (min < root.key)
    result += range(root.left, min, max); 
  if (root.key < max)
    result += range(root.right, min, max);
  return result;
}
