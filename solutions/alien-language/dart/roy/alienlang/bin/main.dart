// Copyright (c) 2015, <your name>. All rights reserved. Use of this source code
// is governed by a BSD-style license that can be found in the LICENSE file.

import 'package:alienlang/alienlang.dart' as alienlang;
import 'dart:io';

var parts;

var i, j, L, D, N;

void readFile() {
  new File('/home/roy/projects/short.txt').readAsLines().then((List<String> lines) {
    parts = lines[0].split(' ');
    for (i=0; i<3; i++) {
      parts[i]=int.parse(parts[i]);
      print(parts[i]);
    }
    D=parts[1];
    var dict = new List(D);
    for (i=0;i<D;i++) {
        dict[i]=lines[i+1];
      }
    L=parts[2];
    var cases = new List(L);
    for (i=0;i<L;i++){
      cases[i]=lines[i+1+D];
      cases[i]=cases[i].toString();
      cases[i]=cases[i].replaceAll('(','[');
      cases[i]=cases[i].replaceAll(')',']');
    }
    for (i=0;i<L;i++) {
      var count=0;
      for (j=0;j<D;j++) {
        RegExp test = new RegExp(cases[i]);
        String d = dict[j];
        var matches = test.hasMatch(d);
        if (matches) {
          count++;
        }
      }
      print("Case #$i: $count");
    }
  });
  
}

void main() {
  print('Hello world: ${alienlang.calculate()}!');
  readFile();
}
