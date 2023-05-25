package javasourcelearn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class ArrayListSource {
    public static void main(String[] args) {
        //new
        ArrayList<String> list = new ArrayList<String>();
        //add
        list.add("wcy");
        list.add("这是什么?");
        //访问元素
        String one01 = list.get(1);
        //修改元素
        list.set(1,"wwwcccyyy");
        //删除元素
        list.remove(1);
        //计算大小
        int two01 = list.size();
        //排序
        Collections.sort(list);

        ArrayList<Integer> myNumbers = new ArrayList<Integer>();
        myNumbers.add(10);
        myNumbers.add(15);
        myNumbers.add(20);
        myNumbers.add(25);
        for (int i : myNumbers) {
            System.out.println(i);
        }


    }
}
