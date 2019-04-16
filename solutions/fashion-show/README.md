# Fashion Show

You are about to host a fashion show to show off three new styles of clothing. The show will be held on a stage which is in the most fashionable of all shapes: an N-by-N grid of cells.

Each cell in the grid can be empty (which we represent with a . character) or can contain one fashion model. The models come in three types, depending on the clothing style they are wearing: +, x, and the super-trendy o. A cell with a + or x model in it adds 1 style point to the show. A cell with an o model in it adds 2 style points. Empty cells add no style points.

To achieve the maximum artistic effect, there are rules on how models can be placed relative to each other.

Whenever any two models share a row or column, at least one of the two must be a +.
Whenever any two models share a diagonal of the grid, at least one of the two must be an x.
Formally, a model located in row i0 and column j0 and a model located in row i1 and column j1 share a row if and only if i0 = i1, they share a column if and only if j0 = j1, and they share a diagonal if and only if i0 + j0 = i1 + j1 or i0 - j0 = i1 - j1.

For example, the following grid is not legal:

...
x+o
.+.
The middle row has a pair of models (x and o) that does not include a +. The diagonal starting at the + in the bottom row and running up to the o in the middle row has two models, and neither of them is an x.

However, the following grid is legal. No row, column, or diagonal violates the rules.

+.x
+x+
o..
Your artistic advisor has already placed M models in certain cells, following these rules. You are free to place any number (including zero) of additional models of whichever types you like. You may not remove existing models, but you may upgrade as many existing + and x models into o models as you wish, as long as the above rules are not violated.

Your task is to find a legal way of placing and/or upgrading models that earns the maximum possible number of style points.

More detais: https://code.google.com/codejam/contest/3264486/dashboard#s=p3