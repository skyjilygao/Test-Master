import java.lang.reflect.Array;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by SKYJILYGAO on 2017/6/9.
 */
public class test {
    public static  void main(String[] args){
        int[] array={1,2,3,77,4,7,2,34,2,88,9,4,77,4,3};
        Map<Integer,Integer> map=new HashMap<Integer, Integer>();
        int count=0;
        for(int i=0;i<array.length;i++){

            if(!map.containsKey(array[i])){
                count=1;
                map.put(array[i],count);
            }else{
                count=1+map.get(array[i]);
                map.put(array[i],count);
            }
        }
        compare c=new compare();

        System.out.println(map);
    }
}
class compare implements Comparator<Map.Entry<Integer,Integer>>{


    public int compare(Map.Entry<Integer, Integer> o1, Map.Entry<Integer, Integer> o2) {
        if(o1.getValue()==o2.getValue()){
            if(o1.getKey()<o2.getKey()){
                return -1;
            }else{
                return 1;
            }
        }else{
            return 1;
        }
    }
}