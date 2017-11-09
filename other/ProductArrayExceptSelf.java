/*
  Product of Array Except Self

  write an algorithm that will give me an output array
  each element of the output array is product of everything in the array except for the element in the input at that index
    
  input = (6, 2, 4)
       (2*4, 6*4, 6*2)
  output = (8, 24, 12)
    
  array1 = [1, 6, 12]
  array2 = [8 , 4 , 1]
  output = [8, 24, 12]

  bounds on the input:
  array is at least size 2
  all elements are integers (zero, negative, positive)
    
  FB - phone interview
*/

public int[] maxProduct(int[] input) {
    int[] array1 = new int[input.length];
    int[] array2 = new int[input.length];
    for (int int i = 0; i < input.length; i++) {
         if (i == 0) {
            array1[i] = 1;
         } else {
            array1[i] = array[i-1] * input[i-1]; 
         }
    }
    for (int int i = input.length-1; i >= 0; i++) {
         if (i == input.length-1) {
            array1[i] = 1;
         } else {
            array1[i] = array[i+1] * input[i+1]; 
         }
    } 
    
    for (int int i = 0; i < input.length; i++) {
         array2[i] *= array1[i]
    } 
    return array2;
}