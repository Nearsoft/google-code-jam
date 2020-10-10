import java.util.Scanner;


class Solution {
  public static Scanner sc= new Scanner(System.in);
  public static int count = 0;

  public static void printArray(int arr[]){
    int size = arr.length;
    for (int i = 0; i < size; i++){
        System.out.print(arr[i]);
    }
    System.out.print("\n");
  }

  public static void fillArray(int arr[]){
    int size = arr.length;
    for (int i = 0; i < size; i++){
        arr[i] = 0;
    }
  }

  public static void reverse(int arr[]){
    int size = arr.length;
    int tmp = 0; 
    for (int i = 0; i < Math.floor(size/2); i++){
        tmp = arr[i];
        arr[i] = arr[size-1-i];
        arr[size-1-i] = tmp;
    }
  }

  public static void flipArray(int arr[]){
    int size = arr.length;
    for (int i = 0; i < size; i++){
        arr[i] ^= 1;
    }
  }

  public static int query(int i){
    
    if(i != -1){
      System.out.print(i+1);
    }
    else{
      System.out.print("1");
    }
    System.out.print("\n");
    count++;
    int input = sc.nextInt();
    sc.nextLine();
    return input;
  }

  public static void check(int arr[]){
    printArray(arr);
    //sc.nextLine();
    String input = sc.nextLine(); 
    
    if (!input.equals("Y"))
      System.exit(0);
      
  }

  public static void esab_atad(int B){
    int result[] = new int[B];
    int flip_i = -1;
    int reverse_i = -1;
    int flip_res = 0;
    int reverse_res = 0;
    count = 0;
    fillArray(result);
    for (int i = 0; i < Math.floor(B / 2); i++){
      if(count != 0 && count%10 == 0){
        flip_res = query(flip_i);
        reverse_res = query(reverse_i);

        if ( flip_i != -1 && (result[flip_i]^flip_res) == 1)
          flipArray(result);

        if ( reverse_i != -1 && (result[reverse_i]^reverse_res) == 1)
          reverse(result);
      }

      result[i] = query(i);

      result[B-i-1] = query(B-i-1);

      if( result[i] == result[B-i-1])
        flip_i = i;
      else 
        reverse_i = i;
    }
    check(result);
  }

  public static void main(String[] args) {
    int T;
    int B;
    String input;
    String[] inputSplit;
    input = sc.nextLine();
    inputSplit = input.split(" ");

    T = Integer.parseInt(inputSplit[0]);
    B = Integer.parseInt(inputSplit[1]);

    for (int i = 0; i < T; i++){
      esab_atad(B);
    }
  }
}

