import java.util.*;


public class CollectionsPractice{
    public static void main(String args[]){

        List<String> li = new ArrayList<String>();
        li.add("Babal");
        li.add("Preet");
        li.add("singh");

        for(int i=0;i<li.size();i++){
            System.out.println(li.get(i));
        }


        // frequency counter 
        List<Integer> li2 =  Arrays.asList(10,10,1,2,3,9,4,5,9,7,5,1,2,8,7);
        Map<Integer,Integer> map = new HashMap<>();
        for(int i:li2){
            if(map.containsKey(i)){
                map.put(i,map.get(i)+1);
            }
            else{
                map.put(i,1);
            }
            
        }
        System.out.println(map.getClass());
        for(Map.Entry<Integer, Integer> entry : map.entrySet() ){
            System.out.println(entry.getKey()+" : frequency --> "+entry.getValue());

        }


        System.out.println();
        List<Integer> li3 = Arrays.asList(30,20,10,70,10,40,100,300,100,10,80,70,60,20);
        Map<Integer,Integer> map2 = new HashMap<>();
        for(int i:li3){
            if(map2.containsKey(i)){
                map2.put(i,map2.get(i)+1);
            }
            else{
                map2.put(i,1);
            }
            
        }
        System.out.println(map2.getClass());
        for(Map.Entry<Integer, Integer> entry : map2.entrySet() ){
            System.out.println(entry.getKey()+" : frequency --> "+entry.getValue());

        }

    }
}
