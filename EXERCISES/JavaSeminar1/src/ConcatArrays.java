public class ConcatArrays {
    public static void main(String[] args) {
        int[] arr={2,5,4,3};
        int[] arr2={1,7,4,8};
        int[] c=new int[arr.length+arr2.length];

        for(int i=0;i<arr.length;i++){
            c[i]=arr[i];
        }
        for(int i=0;i<arr2.length;i++){
            c[i]=arr2[i];
        }
        for(int i=0;i<c.length;i++) {
            System.out.print(c[i] + " ");
        }
    System.out.println();
    }
}
