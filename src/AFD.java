import java.security.InvalidParameterException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class AFD {
    private final int init_state;
    private final Set<Character> abc;
    private final Set<Integer> states;
    private final Set<Integer> final_states;
    private final HashMap<Character, int[]> func;

    public AFD(int init_state, Set<Character> abc, Set<Integer> states, Set<Integer> final_states,
               HashMap<Character, int[]> func){
        //if(init_state == null, abc ==null)
        if(!AFD.is_valid_func(abc, states, func)) throw new InvalidParameterException("invalid function");

        this.init_state = init_state;
        this.abc = abc;
        this.states = states;
        this.final_states = final_states;
        this.func = func;
    }

    public boolean process(String str) {
        return aux_process(str, init_state);
    }

    private boolean aux_process(String str, int state){
        if(str.length() == 0)
            return final_states.contains(state);

        if(str.length() == 1) {
            Character chr = str.charAt(0);
            if(!abc.contains(chr)) return false;
            int new_state = func.get(chr)[state];
            return final_states.contains(new_state);
        }
        Character chr = str.charAt(0);
        if(!abc.contains(chr))
            return false;
        int new_state = func.get(chr)[state];
        String sub_str = str.substring(1);
        return aux_process(sub_str, new_state);
    }

    static boolean is_valid_func(Set<Character> afd_abc, Set<Integer> afd_states, HashMap<Character,int[]> function){
        Set<Character> func_adc = new HashSet<>(function.keySet());
        Set<Character> aux_afd_abc = new HashSet<>(afd_abc);

        func_adc.removeAll(aux_afd_abc);
        aux_afd_abc.removeAll(function.keySet());

        if(!(func_adc.size() == 0 && aux_afd_abc.size() == 0)) return false;

        return function.values().stream()
                    .map(xs -> Arrays.stream(xs)
                                .mapToObj(afd_states::contains)
                                .reduce(true, (b1, b2)->{
                                    if(!(xs.length == afd_states.size())) return false;
                                    return b1 && b2;
                                }))
                    .reduce(true, (b1, b2)-> b1 && b2);
    }

    public static HashMap<Character, int []> make_function(
            Set<Character> index1,
            Set<Integer> index2,
            int[][] values){
        if(!(values.length == index1.size() && values[0].length == index2.size())) return null;

        HashMap<Character, int []> return_map = new HashMap<>();
        int index = 0;
        for(Character c: index1){
            return_map.put(c, values[index]);
            index++;
        }

        return return_map;
    }
    public static Set<Integer> setOf(Integer... values) {
        return new HashSet<>(Arrays.asList(values));
    }
    public static Set<Character> setOf(Character... values) {
        return new HashSet<>(Arrays.asList(values));
    }

    static AFD rut_test(){
        Set<Character> abc = AFD.setOf('-', '.','0','1', '2' ,'3', '4','5', '6','7', '8','9', 'k');
        Set<Integer> states = AFD.setOf(0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27);
        Set<Integer> final_states = AFD.setOf(10,18);
        HashMap<Character, int[]> function = new HashMap<>();

                                   //0  1  2  3  4  5  6  7  8  9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27
        function.put('k', new int[]{27,27,27,27,27,27,27,27,10,10,27,27,27,27,27,27,27,10,10,27,27,27,27,27,27,27,10,27});
        function.put('-', new int[]{27,27,27,27,27,27,27,27, 9,27,27,27,27,27,27,27,27, 9, 9,27,27,27,27,27,27,27, 9,27});
        function.put('.', new int[]{27,27,27,27,27,27,27,27,27,27,27,19,19,27,27,27,27,27,27,27,27,27,23,27,27,27,27,27});
        function.put('0', new int[]{ 1,27, 3, 4, 5, 6, 7, 8,10,10,27,12,13,14,15,16,17,18,10,20,21,22,27,24,25,26,10,27});
        function.put('1', new int[]{11, 2, 3, 4, 5, 6, 7, 8,10,10,27,12,13,14,15,16,17,18,10,20,21,22,27,24,25,26,10,27});
        function.put('2', new int[]{11, 2, 3, 4, 5, 6, 7, 8,10,10,27,12,13,14,15,16,17,18,10,20,21,22,27,24,25,26,10,27});
        function.put('3', new int[]{11, 2, 3, 4, 5, 6, 7, 8,10,10,27,12,13,14,15,16,17,18,10,20,21,22,27,24,25,26,10,27});
        function.put('4', new int[]{11, 2, 3, 4, 5, 6, 7, 8,10,10,27,12,13,14,15,16,17,18,10,20,21,22,27,24,25,26,10,27});
        function.put('5', new int[]{11, 2, 3, 4, 5, 6, 7, 8,10,10,27,12,13,14,15,16,17,18,10,20,21,22,27,24,25,26,10,27});
        function.put('6', new int[]{11, 2, 3, 4, 5, 6, 7, 8,10,10,27,12,13,14,15,16,17,18,10,20,21,22,27,24,25,26,10,27});
        function.put('7', new int[]{11, 2, 3, 4, 5, 6, 7, 8,10,10,27,12,13,14,15,16,17,18,10,20,21,22,27,24,25,26,10,27});
        function.put('8', new int[]{11, 2, 3, 4, 5, 6, 7, 8,10,10,27,12,13,14,15,16,17,18,10,20,21,22,27,24,25,26,10,27});
        function.put('9', new int[]{11, 2, 3, 4, 5, 6, 7, 8,10,10,27,12,13,14,15,16,17,18,10,20,21,22,27,24,25,26,10,27});

        return new AFD(0, abc, states, final_states, function);
    }

    static AFD nx3_test(){
        Set<Character> abc = AFD.setOf('a', 'b');
        Set<Integer> states = AFD.setOf(0,1,2,3);
        Set<Integer> final_states = AFD.setOf(3);

        HashMap<Character, int[]> function =new HashMap<>();

                                //   0  1  2  3
        function.put('a', new int[]{ 1, 2, 3, 1});
        function.put('b', new int[]{ 1, 2, 3, 1});

        return new AFD(0, abc, states, final_states, function);

    }
}
