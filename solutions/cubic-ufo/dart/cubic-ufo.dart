import 'dart:io';
import 'dart:math'; // (This Dart library only uses radians.)
const double toRad = 0.0174532925199; // A constant for degree-to-radian conversion.
const double toDeg = 57.2957795130823; // A constant for radian-to-degree conversion.

main() {
 int numberOfCases; // The number of test cases that will be calculated.
 double area; // The target area of the cube's shadow.
 // Getting the number of cases:
 numberOfCases = int.parse(stdin.readLineSync());
 for (var i = 0; i < numberOfCases; i++) {
   // Getting the target area:
   area = double.parse(stdin.readLineSync());
   print('Case #${i + 1}:');
   calculateRotation(area);
 }
}
/**
* Receives the desired area of the cube's shadow.
* Calculates the necessary rotation of the cube.
* Performs the rotation on the cube.
* Outputs the positions of the cube's three non-opposing faces after the rotation.
*/
void calculateRotation(double area) {
 double theta; // The angle of the needed rotation for the cube.
 // The three points corresponding to the centers of three non-pairwise-opposing faces of the cube:
 List<double> p1 = new List(3); // Each point is comprised of 3 values (its coordinates in x, y and z).
 List<double> p2 = new List(3);
 List<double> p3 = new List(3);
 // The cube is initially axis-aligned and centered at the origin,
 //  so the center of each face points towards each of the three axis:
 p1 = [0.5, 0, 0];
 p2 = [0, 0.5, 0];
 p3 = [0, 0, 0.5];
 // The first step is to rotate the cube 45° along the y axis:
 p1 = rotY(p1, 45);
 p2 = rotY(p2, 45);
 p3 = rotY(p3, 45);
 // Now we need a second rotation about the x axis.
 // We can calculate the necessary angle to achieve the target area with the following formula:
 theta = (asin(area / sqrt(3)) * toDeg) - (asin(1 / sqrt(3)) * toDeg);
 // Now that we know the value of theta, we can perform the second rotation:
 p1 = rotX(p1, theta);
 p2 = rotX(p2, theta);
 p3 = rotX(p3, theta);
 // Printing the output:
 print('${p1[0]} ${p1[1]} ${p1[2]}');
 print('${p2[0]} ${p2[1]} ${p2[2]}');
 print('${p3[0]} ${p3[1]} ${p3[2]}');
}
/**
* Receives a point and an angle value.
* Rotates the point about the y axis.
* Returns the rotated point.
*/
List<double> rotY(List<double> p, double theta) {
 List<List<double>> rMat; // The rotation matrix for y.
 List<double> newPoint = new List(3); // The resulting point after the rotation.
 // Definition of the rotation matrix:
 rMat = new List<List<double>>(3);
 rMat[0] = [cos(theta * toRad), 0, sin(theta * toRad)];
 rMat[1] = [0, 1, 0];
 rMat[2] = [-sin(theta * toRad), 0, cos(theta * toRad)];
 // Multiplication of the point by the rotation matrix:
 for (var i = 0; i < 3; i++) {
   double sum = 0;
   for (var j = 0; j < 3; j++) {
     sum += rMat[i][j] * p[j];
   }
   newPoint[i] = sum;
 }
 return newPoint;
}
/**
* Receives a point and an angle value.
* Rotates the point about the x axis.
* Returns the rotated point.
*/
List<double> rotX(List<double> p, double theta) {
 List<List<double>> rMat; // The rotation matrix for x.
 List<double> newPoint = new List(3); // The resulting point after the rotation.
 // Definition of the rotation matrix:
 rMat = new List<List<double>>(3);
 rMat[0] = [1, 0, 0];
 rMat[1] = [0, cos(theta * toRad), -sin(theta * toRad)];
 rMat[2] = [0, sin(theta * toRad), cos(theta * toRad)];
 // Multiplication of the point by the rotation matrix:
 for (var i = 0; i < 3; i++) {
   double sum = 0;
   for (var j = 0; j < 3; j++) {
sum += rMat[i][j] * p[j];
   }
   newPoint[i] = sum;
 }
 return newPoint;
}
